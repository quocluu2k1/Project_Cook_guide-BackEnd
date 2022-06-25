package com.project.cookguide.Cook.guide.project.repositories;

import com.project.cookguide.Cook.guide.project.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query(value="SELECT * FROM comment WHERE food_id = ?1 ORDER BY time DESC LIMIT ?2,10", nativeQuery = true)
    List<Comment> findAllByFoodId(Long foodId, int nPage);

    @Query(value="SELECT count(*) FROM comment WHERE food_id = ?1", nativeQuery = true)
    int getNumberCommentByFoodId(Long foodId);

    @Query(value = "SELECT DISTINCT DATE(time) FROM comment WHERE id = ?1 ORDER BY time DESC", nativeQuery = true)
    List<Date> getDateCommentByUserId(Long id);

    @Query(value = "SELECT * from comment WHERE DATE(time) = ?1 ORDER BY time DESC", nativeQuery = true)
    List<Comment> getCommentByDate(Date date);
}
