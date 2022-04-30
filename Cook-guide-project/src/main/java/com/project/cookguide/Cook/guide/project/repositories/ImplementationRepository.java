package com.project.cookguide.Cook.guide.project.repositories;

import com.project.cookguide.Cook.guide.project.entities.Implementation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImplementationRepository extends JpaRepository<Implementation, Long> {
}
