package com.malgn.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Getter
public class Users {
    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", unique = true, nullable = false, updatable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRole role;

    @Column(name = "created_date", nullable = false, updatable = false)
    private LocalDateTime createdDate =  LocalDateTime.now();

    @Column(name = "last_modified_date")
    private LocalDateTime lastModifiedDate;

}
