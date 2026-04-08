package com.gpglab.ossrefapi.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "project")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String owner;

    @Column(columnDefinition = "TEXT")
    private String githubDescription;

    @Column(columnDefinition = "TEXT")
    private String generatedDescription;

    private Integer stars;

    private LocalDateTime lastCommittedAt;

    @OneToMany(mappedBy = "project")
    private List<ProjectFramework> projectFrameworks = new ArrayList<>();
}
