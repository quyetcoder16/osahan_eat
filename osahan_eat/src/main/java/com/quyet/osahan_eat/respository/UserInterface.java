package com.quyet.osahan_eat.respository;

import com.quyet.osahan_eat.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInterface extends JpaRepository<Users, Integer> {
}