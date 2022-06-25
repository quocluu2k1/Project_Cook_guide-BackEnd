package com.project.cookguide.Cook.guide.project.repositories;

import com.project.cookguide.Cook.guide.project.dto.FoodDto;
import com.project.cookguide.Cook.guide.project.entities.Bookmark;
import com.project.cookguide.Cook.guide.project.entities.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodRepository extends JpaRepository<Food, Long> {
    @Query(value="SELECT * FROM food WHERE food_id IN(SELECT food_id from reaction GROUP BY food_id ORDER BY sum(r_claps+r_hearts+r_savoring) DESC, food_id ASC) LIMIT 5", nativeQuery = true)
    List<Food> getOutstandingFood();

    @Query(value="SELECT * FROM food WHERE food_id IN(SELECT food_id from reaction GROUP BY food_id ORDER BY sum(r_claps+r_hearts+r_savoring) DESC, food_id ASC) LIMIT ?1,5", nativeQuery = true)
    List<Food> getOutstandingFoodForNumberPage(int numberPage);

    @Query(value="SELECT * FROM food ORDER BY date DESC LIMIT 5", nativeQuery = true)
    List<Food> getNewFood();

    @Query(value="SELECT * FROM food ORDER BY date DESC LIMIT ?1,5", nativeQuery = true)
    List<Food> getNewFoodForNumberPage(int numberPage);

    @Query(value="SELECT * FROM food WHERE food_id IN(SELECT food_id from reaction GROUP BY food_id ORDER BY sum(r_hearts) DESC, food_id ASC) LIMIT 5", nativeQuery = true)
    List<Food> getFavoriteFood();

    @Query(value="SELECT * FROM food WHERE food_id IN(SELECT food_id from reaction GROUP BY food_id ORDER BY sum(r_hearts) DESC, food_id ASC) LIMIT ?1,5", nativeQuery = true)
    List<Food> getFavoriteFoodForNumberPage(int numberPage);

    @Query(value = "SELECT * FROM food WHERE name LIKE %:name% ORDER BY date DESC LIMIT 10", nativeQuery = true)
    List<Food> searchFoodByNameFood(@Param("name") String nameFood);

    @Query(value = "SELECT * FROM food WHERE description LIKE %:description% ORDER BY date DESC LIMIT 10", nativeQuery = true)
    List<Food> searchFoodByDescription(@Param("description") String description);

    @Query(value = "SELECT * FROM food WHERE food_id in (SELECT food_id FROM ingredient WHERE name LIKE %:name%) ORDER BY date DESC LIMIT 10", nativeQuery = true)
    List<Food> searchFoodByNameMaterial(@Param("name") String nameMaterial);

    @Query(value = "SELECT * FROM food WHERE food_id in (SELECT food_id FROM bookmark GROUP BY food_id ORDER BY count(food_id) DESC, food_id ASC) LIMIT 10", nativeQuery = true)
    List<Food> searchSuggestion();
}
