package com.quyet.osahan_eat.respository;

import com.quyet.osahan_eat.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRespository extends JpaRepository<Users, Integer> {
    public List<Users> findByUserNameAndPassword(String userName, String password);
    Users findByUserName(String userName);
}
