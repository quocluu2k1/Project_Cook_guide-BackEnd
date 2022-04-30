package com.project.cookguide.Cook.guide.project.repositories;

import com.project.cookguide.Cook.guide.project.entities.Bookmark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.SqlResultSetMapping;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {
    @Query(value="Select * From bookmark where id = ?1 ORDER BY date DESC", nativeQuery = true)
    List<Bookmark> findAllByUserId(Long Id);

    @Query(value="Select * From bookmark where id = ?1 and food_id = ?2", nativeQuery = true)
    Bookmark findByUserIdAndFoodId(Long UserId, Long FoodId);

    @Query(value="SELECT count(*) FROM bookmark WHERE id=?1 and food_id=?2", nativeQuery = true)
    Long getStatusBookmarkFoodOfUser(Long userId, Long foodId);
}
