package com.quyet.osahan_eat.service;

import com.quyet.osahan_eat.dto.response.UserResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    public List<UserResponseDTO> getAllUsers();
}
