package com.project.cookguide.Cook.guide.project.controllers;

import com.project.cookguide.Cook.guide.project.dto.FoodDto;
import com.project.cookguide.Cook.guide.project.dto.UserDto;
import com.project.cookguide.Cook.guide.project.services.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/home")
public class HomeController {

    @Autowired
    FoodService foodService;

    @GetMapping("/getoutstandingfood")
    public List<FoodDto> getOutstandingFood(){
        return foodService.getOutstandingFood();
    }

    @GetMapping("/getoutstandingfood/{numberPage}")
    public List<FoodDto> getOutstandingFoodForNumberPage(@PathVariable int numberPage){
        return foodService.getOutstandingFoodForNumberPage(numberPage);
    }

    @GetMapping("/getnewfood")
    public List<FoodDto> getNewFood(){
        return foodService.getNewFood();
    }

    @GetMapping("/getnewfood/{numberPage}")
    public List<FoodDto> getNewFoodForNumberPage(@PathVariable int numberPage){
        return foodService.getNewFoodForNumberPage(numberPage);
    }

    @GetMapping("/getfavoritefood")
    public List<FoodDto> getFavoriteFood(){
        return foodService.getFavoriteFood();
    }

    @GetMapping("/getfavoritefood/{numberPage}")
    public List<FoodDto> getFavoriteFoodForNumberPage(@PathVariable int numberPage){
        return foodService.getFavoriteFoodForNumberPage(numberPage);
    }
}
