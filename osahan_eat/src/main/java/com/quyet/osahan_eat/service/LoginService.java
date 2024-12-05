package com.quyet.osahan_eat.service;

import com.quyet.osahan_eat.dto.response.UserResponseDTO;
import com.quyet.osahan_eat.entity.Users;
import com.quyet.osahan_eat.respository.UserInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LoginService {
    @Autowired
    private UserInterface userInterface;

    public List<UserResponseDTO> getAllUsers() {
        List<UserResponseDTO> list = new ArrayList<>();
        List<Users> listUserDb = userInterface.findAll();
        for (Users user : listUserDb) {
            UserResponseDTO userResponseDTO = new UserResponseDTO();
            userResponseDTO.setId(user.getId());
            userResponseDTO.setUserName(user.getUserName());
            userResponseDTO.setPassword(user.getPassword());
            userResponseDTO.setFullName(user.getFullName());
            userResponseDTO.setCreateDate(user.getCreateDate());
            list.add(userResponseDTO);
        }
        return list;
    }
}
