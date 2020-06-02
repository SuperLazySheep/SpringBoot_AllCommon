package com.kiki.quartz3.repository;

import com.kiki.quartz3.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Integer> {

    User findById(int id);
}
