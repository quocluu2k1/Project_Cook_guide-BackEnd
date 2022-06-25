package com.project.cookguide.Cook.guide.project.repositories;

import com.project.cookguide.Cook.guide.project.entities.Implementation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImplementationRepository extends JpaRepository<Implementation, Long> {
    @Query(value="SELECT * FROM implementation WHERE food_id = ?1", nativeQuery = true)
    List<Implementation> findByFoodId(Long id);
}
