package com.quyet.osahan_eat.controller;

import com.quyet.osahan_eat.dto.request.UserSignUpRequestDTO;
import com.quyet.osahan_eat.payload.ResponseData;
import com.quyet.osahan_eat.service.LoginService;
import com.quyet.osahan_eat.util.JwtUtilHelper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.crypto.SecretKey;

@CrossOrigin("*")
@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private JwtUtilHelper jwtUtil;

    @PostMapping("/signin")
    public ResponseEntity<?> signIn(@RequestParam(name = "username") String username, @RequestParam(name = "password") String password) {
        ResponseData responseData = new ResponseData();

//        SecretKey secretKey = Jwts.SIG.HS256.key().build();;
//        String secretString = Encoders.BASE64.encode(secretKey.getEncoded());
//
//        System.out.println(secretString);

        if (loginService.login(username, password)) {
            String token = jwtUtil.generateToken(username);
            responseData.setData(token);

        } else {
            responseData.setData("");
            responseData.setSuccess(Boolean.FALSE);
        }
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody UserSignUpRequestDTO loginRequestDTO) {
        ResponseData responseData = new ResponseData();

        if (loginService.register(loginRequestDTO.getEmail(), loginRequestDTO.getPassword(), loginRequestDTO.getFullName(),loginRequestDTO.getRoleId())) {
            responseData.setData(true);
        } else {
            responseData.setData(false);
        }

        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }
}
