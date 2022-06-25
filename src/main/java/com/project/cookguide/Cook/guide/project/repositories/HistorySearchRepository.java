package com.project.cookguide.Cook.guide.project.repositories;

import com.project.cookguide.Cook.guide.project.entities.HistorySearch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistorySearchRepository extends JpaRepository<HistorySearch, Long> {

    @Query(value = "SELECT * FROM history_search WHERE id = ?1 and is_activated = true ORDER BY time DESC LIMIT 5", nativeQuery = true)
    List<HistorySearch> findByUserId(Long id);

    @Query(value = "SELECT * FROM history_search GROUP BY content, type ORDER BY COUNT(his_id) DESC, type ASC LIMIT 5", nativeQuery = true)
    List<HistorySearch> getFeaturedSearchKeywords();
}
