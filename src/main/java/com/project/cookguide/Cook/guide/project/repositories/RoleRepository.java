package com.project.cookguide.Cook.guide.project.repositories;

import com.project.cookguide.Cook.guide.project.common.ERole;
import com.project.cookguide.Cook.guide.project.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
