package com.project.cookguide.Cook.guide.project.controllers;

import com.project.cookguide.Cook.guide.project.dto.FoodDto;
import com.project.cookguide.Cook.guide.project.dto.ImplementationDto;
import com.project.cookguide.Cook.guide.project.dto.IngredientDto;
import com.project.cookguide.Cook.guide.project.entities.Food;
import com.project.cookguide.Cook.guide.project.entities.Implementation;
import com.project.cookguide.Cook.guide.project.services.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/cookguide")
public class DetailCookGuideController {
    public int rClap = 1;
    public int rHeart = 2;
    public int rSavoring = 3;
    @Autowired
    FoodService foodService;

    @GetMapping("/detail/{id}")
    public FoodDto getDetailCookGuide(@PathVariable Long id){
        return foodService.getDetailCookGuide(id);
    }

    @GetMapping("/getingredient/{id}")
    public List<IngredientDto> getIngredientByFood(@PathVariable Long id){
        return foodService.getIngredientByFood(id);
    }

    @GetMapping("/getimplementation/{id}")
    public List<ImplementationDto> getImplementationByFood(@PathVariable Long id){
        return foodService.getImplementationByFood(id);
    }


    @GetMapping("/getstatusbookmarkuser/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public Boolean getStatusBookmarkFoodOfUser(@PathVariable Long id){
        return foodService.getStatusBookmarkFoodOfUser(id);
    }

    @GetMapping("/addbookmark/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public Boolean addBookmarkForUser(@PathVariable Long id){
        return foodService.addBookmarkForUser(id);
    }

    @GetMapping("/deletebookmark/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public Boolean deleteBookmarkForUser(@PathVariable Long id){
        return foodService.deleteBookmarkForUser(id);
    }


    @GetMapping("/getstatusreactclap/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public Boolean getStatusReactClap(@PathVariable Long id){
        return foodService.getStatusReact(rClap,id);
    }

    @GetMapping("/getstatusreactheart/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public Boolean getStatusReactHeart(@PathVariable Long id){
        return foodService.getStatusReact(rHeart,id);
    }

    @GetMapping("/getstatusreactsavoring/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public Boolean getStatusReactSavoring(@PathVariable Long id){
        return foodService.getStatusReact(rSavoring,id);
    }

    @GetMapping("/addreactclap/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public Boolean addReactClap(@PathVariable Long id){
        return foodService.addReaction(rClap,id);
    }

    @GetMapping("/addreactheart/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public Boolean addReactHeart(@PathVariable Long id){
        return foodService.addReaction(rHeart,id);
    }

    @GetMapping("/addreactsavoring/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public Boolean addReactSavoring(@PathVariable Long id){
        return foodService.addReaction(rSavoring,id);
    }

    @GetMapping("/deletereactclap/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public Boolean deleteReactClap(@PathVariable Long id){
        return foodService.deleteReaction(rClap,id);
    }

    @GetMapping("/deletereactheart/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public Boolean deleteReactHeart(@PathVariable Long id){
        return foodService.deleteReaction(rHeart,id);
    }

    @GetMapping("/deletereactsavoring/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public Boolean deleteReactSavoring(@PathVariable Long id){
        return foodService.deleteReaction(rSavoring,id);
    }
}
