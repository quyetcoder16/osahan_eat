package com.quyet.osahan_eat.security;

import com.quyet.osahan_eat.entity.Users;
import com.quyet.osahan_eat.respository.UserRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    UserRespository userRespository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = userRespository.findByUserName(username);
        if (user == null) {
            throw new UsernameNotFoundException("username not found!");
        }

        return new User(user.getUserName(),user.getPassword(),new ArrayList<>());
    }
}
