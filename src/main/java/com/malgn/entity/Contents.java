package com.malgn.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Table(name = "contents")
@Getter
public class Contents {
    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "view_count", nullable = false)
    private Long viewCount;

    @Column(name = "created_date", nullable = false, updatable = false)
    private LocalDateTime createdDate =  LocalDateTime.now();

    @Column(name = "created_by", nullable = false, updatable = false)
    private String createdBy;

    @Column(name = "last_modified_date")
    private LocalDateTime lastModifiedDate;

    @Column(name = "last_modified_by")
    private String lastModifiedBy;
}
