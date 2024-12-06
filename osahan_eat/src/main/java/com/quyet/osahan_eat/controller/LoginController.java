package com.quyet.osahan_eat.controller;

import com.quyet.osahan_eat.dto.request.UserSignUpRequestDTO;
import com.quyet.osahan_eat.payload.ResponseData;
import com.quyet.osahan_eat.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/signin")
    public ResponseEntity<?> signIn(@RequestParam(name = "username") String username, @RequestParam(name = "password") String password) {
        ResponseData responseData = new ResponseData();
        if (loginService.login(username, password)) {
            responseData.setData(true);
        } else {
            responseData.setData(false);
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
