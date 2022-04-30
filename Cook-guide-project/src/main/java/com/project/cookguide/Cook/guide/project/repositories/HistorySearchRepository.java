package com.project.cookguide.Cook.guide.project.repositories;

import com.project.cookguide.Cook.guide.project.entities.HistorySearch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistorySearchRepository extends JpaRepository<HistorySearch, Long> {
}
