package com.gpglab.ossrefapi.repository;

import com.gpglab.ossrefapi.domain.ProjectFramework;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProjectFrameworkRepository extends JpaRepository<ProjectFramework, Long> {

    @Query("SELECT pf FROM ProjectFramework pf " +
            "JOIN FETCH pf.project " +
            "JOIN FETCH pf.framework " +
            "WHERE pf.framework.id = :frameworkId")
    List<ProjectFramework> findByFrameworkIdWithProject(@Param("frameworkId") Long frameworkId);

    @Query("SELECT pf FROM ProjectFramework pf " +
            "JOIN FETCH pf.framework " +
            "WHERE pf.project.id = :projectId")
    List<ProjectFramework> findByProjectIdWithFramework(@Param("projectId") Long projectId);
}
