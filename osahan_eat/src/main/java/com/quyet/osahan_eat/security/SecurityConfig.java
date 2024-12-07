package com.quyet.osahan_eat.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    // Inject CustomUserDetailService để load thông tin người dùng từ cơ sở dữ liệu
    @Autowired
    private CustomUserDetailService customUserDetailService;

    @Autowired
    private CustomJwtFilter customJwtFilter;

    /**
     * Cấu hình AuthenticationManager:
     * - Sử dụng CustomUserDetailService để xác thực người dùng
     * - Sử dụng BCryptPasswordEncoder để mã hóa và so sánh mật khẩu
     *
     * @param httpSecurity HttpSecurity object để lấy AuthenticationManagerBuilder
     * @return AuthenticationManager
     * @throws Exception nếu có lỗi trong quá trình cấu hình
     */
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity httpSecurity) throws Exception {
        // Lấy AuthenticationManagerBuilder từ HttpSecurity
        AuthenticationManagerBuilder authenticationManagerBuilder = httpSecurity.getSharedObject(AuthenticationManagerBuilder.class);

        // Cấu hình AuthenticationManagerBuilder với customUserDetailService và PasswordEncoder
        authenticationManagerBuilder.userDetailsService(customUserDetailService).passwordEncoder(passwordEncoder());

        // Trả về AuthenticationManager đã được cấu hình
        return authenticationManagerBuilder.build();
    }

    /**
     * Cấu hình SecurityFilterChain:
     * - Tắt CORS và CSRF
     * - Cho phép các request đến "/login/**" không cần xác thực
     * - Các request khác phải được xác thực
     * - Sử dụng xác thực HTTP Basic
     *
     * @param http HttpSecurity object để cấu hình
     * @return SecurityFilterChain đã được cấu hình
     * @throws Exception nếu có lỗi trong quá trình cấu hình
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors().disable() // Tắt bảo vệ CORS (nếu cần dùng, nên cấu hình riêng)
                .csrf().disable() // Tắt bảo vệ CSRF (cẩn trọng khi tắt CSRF)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeHttpRequests() // Cấu hình quyền truy cập cho các request HTTP
                .requestMatchers("/login/**","/restaurant/files/**") // Các URL bắt đầu bằng "/login"
                .permitAll() // Cho phép truy cập mà không cần xác thực
                .anyRequest() // Mọi request khác
                .authenticated(); // Phải được xác thực
        http.addFilterBefore(customJwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build(); // Trả về SecurityFilterChain đã cấu hình
    }

    /**
     * Bean PasswordEncoder:
     * - Sử dụng BCryptPasswordEncoder để mã hóa mật khẩu
     * - Giúp bảo mật mật khẩu khi lưu trữ trong cơ sở dữ liệu
     *
     * @return PasswordEncoder
     */
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Sử dụng thuật toán BCrypt để mã hóa mật khẩu
    }
}
