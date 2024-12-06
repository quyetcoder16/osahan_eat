package com.quyet.osahan_eat.service.impl;

import com.quyet.osahan_eat.dto.response.UserResponseDTO;
import com.quyet.osahan_eat.entity.Users;
import com.quyet.osahan_eat.respository.UserRespository;
import com.quyet.osahan_eat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRespository userRespository;

    @Override
    public List<UserResponseDTO> getAllUsers() {
        List<Users> users = userRespository.findAll();
        List<UserResponseDTO> userResponseDTOs = new ArrayList<>();
        for (Users user : users) {
            UserResponseDTO userResponseDTO = new UserResponseDTO();
            userResponseDTO.setId(user.getId());
            userResponseDTO.setUserName(user.getUserName());
            userResponseDTO.setFullName(user.getFullName());
            userResponseDTOs.add(userResponseDTO);
        }
        return userResponseDTOs;
    }
}
