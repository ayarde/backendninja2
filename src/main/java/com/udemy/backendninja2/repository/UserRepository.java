package com.udemy.backendninja2.repository;

import com.udemy.backendninja2.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository("userRepository")
public class UserRepository extends JpaRepository<User, Serializable>{

    public abstract User findByUser(String username);
}
