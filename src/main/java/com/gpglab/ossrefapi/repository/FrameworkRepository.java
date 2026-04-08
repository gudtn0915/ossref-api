package com.gpglab.ossrefapi.repository;


import com.gpglab.ossrefapi.domain.Framework;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface FrameworkRepository extends JpaRepository<Framework, Long> {

    Optional<Framework> findByName(String name);

    List<Framework> findByNameContainingIgnoreCase(String keyword);

    @Query("SELECT DISTINCT f FROM Framework f " +
            "JOIN FETCH f.projectFrameworks pf " +
            "JOIN FETCH pf.project " +
            "WHERE f.id = :id")
    Optional<Framework> findByIdWithProjects(@Param("id") Long id);

    @Query("SELECT f FROM Framework f " +
            "LEFT JOIN f.projectFrameworks pf " +
            "GROUP BY f " +
            "ORDER BY COUNT(pf) DESC")
    List<Framework> findAllOrderByProjectCount();

}
