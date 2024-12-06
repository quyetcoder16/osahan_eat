package com.quyet.osahan_eat.service.impl;

import com.quyet.osahan_eat.dto.response.UserResponseDTO;
import com.quyet.osahan_eat.entity.Roles;
import com.quyet.osahan_eat.entity.Users;
import com.quyet.osahan_eat.respository.UserRespository;
import com.quyet.osahan_eat.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private UserRespository userRespository;

    public List<UserResponseDTO> getAllUsers() {
        List<UserResponseDTO> list = new ArrayList<>();
        List<Users> listUserDb = userRespository.findAll();
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

    @Override
    public boolean login(String username, String password) {
        List<Users> listUserDb = userRespository.findByUserNameAndPassword(username, password);
        return listUserDb.size() > 0;
    }

    @Override
    public boolean register(String username, String password, String fullName,int roleId) {

        Roles role = new Roles();
        role.setId(roleId);

        Users user = new Users();
        user.setUserName(username);
        user.setPassword(password);
        user.setFullName(fullName);
        user.setRole(role);

        try {
            userRespository.save(user);
            return true;
        } catch (Exception e) {
            return false;
        }

    }
}
