package com.malgn.repository;

import com.malgn.entity.User;
import com.malgn.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    boolean existsByUsername(String username);
}
