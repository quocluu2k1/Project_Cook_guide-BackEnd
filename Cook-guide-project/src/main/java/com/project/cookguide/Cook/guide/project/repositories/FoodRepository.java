package com.project.cookguide.Cook.guide.project.repositories;

import com.project.cookguide.Cook.guide.project.dto.FoodDto;
import com.project.cookguide.Cook.guide.project.entities.Bookmark;
import com.project.cookguide.Cook.guide.project.entities.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodRepository extends JpaRepository<Food, Long> {
    @Query(value="SELECT * FROM food ORDER BY (SELECT sum(r_claps+r_hearts+r_savoring) from reaction) DESC LIMIT 5", nativeQuery = true)
    List<Food> getOutstandingFood();

    @Query(value="SELECT * FROM food ORDER BY (SELECT sum(r_claps+r_hearts+r_savoring) from reaction) DESC LIMIT ?1,5", nativeQuery = true)
    List<Food> getOutstandingFoodForNumberPage(int numberPage);

    @Query(value="SELECT * FROM food ORDER BY date DESC LIMIT 5", nativeQuery = true)
    List<Food> getNewFood();

    @Query(value="SELECT * FROM food ORDER BY date DESC LIMIT ?1,5", nativeQuery = true)
    List<Food> getNewFoodForNumberPage(int numberPage);

    @Query(value="SELECT * FROM food ORDER BY (SELECT sum(r_hearts) from reaction) DESC LIMIT 5", nativeQuery = true)
    List<Food> getFavoriteFood();

    @Query(value="SELECT * FROM food ORDER BY (SELECT sum(r_hearts) from reaction) DESC LIMIT ?1,5", nativeQuery = true)
    List<Food> getFavoriteFoodForNumberPage(int numberPage);
}
