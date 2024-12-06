package com.quyet.osahan_eat.service;


import com.quyet.osahan_eat.dto.response.UserResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LoginService {
    List<UserResponseDTO> getAllUsers();

    boolean login(String username, String password);

    boolean register(String username, String password, String fullName, int roleId);
}
