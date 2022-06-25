package com.project.cookguide.Cook.guide.project.controllers;

import com.project.cookguide.Cook.guide.project.dto.CommentDto;
import com.project.cookguide.Cook.guide.project.dto.FoodDto;
import com.project.cookguide.Cook.guide.project.dto.ImplementationDto;
import com.project.cookguide.Cook.guide.project.dto.IngredientDto;
import com.project.cookguide.Cook.guide.project.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/admin")
public class AdminController {
    @Autowired
    AdminService adminService;

    //Food
    @GetMapping("/food/{numberPage}")
    @PreAuthorize("hasRole('ADMIN')")
    public List<FoodDto> getListFood(@PathVariable int numberPage){
        return adminService.getListFood(numberPage);
    }

    @PostMapping("/food/add")
    @PreAuthorize("hasRole('ADMIN')")
    public Long addFood(@RequestParam("name") String name, @RequestParam("description") String description, @RequestParam("level") int level, @RequestParam("totaltime") int totalTime, @RequestParam("file1") MultipartFile file1, @RequestParam("file2") MultipartFile file2, @RequestParam("file3") MultipartFile file3){
        return adminService.addFood(name,description,level,totalTime,file1,file2,file3);
    }

    @GetMapping("/food/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Boolean deleteFood(@PathVariable Long id){
        return adminService.deleteFood(id);
    }


    //Ingredient
    @GetMapping("/ingredient/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public List<IngredientDto> getIngredientByFoodId(@PathVariable Long id){
        return adminService.getIngredientByFoodId(id);
    }

    @PostMapping("/ingredient/add")
    @PreAuthorize("hasRole('ADMIN')")
    public Boolean addIngredient(@RequestParam("foodid") Long foodId, @RequestParam("name") String name, @RequestParam("amount") String amount){
        return adminService.addIngredient(foodId,name,amount);
    }

    @GetMapping("/ingredient/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Boolean deleteIngredient(@PathVariable Long id){
        return adminService.deleteIngredient(id);
    }


    //Implementation
    @GetMapping("/implementation/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public List<ImplementationDto> getImplementationByFoodId(@PathVariable Long id){
        return adminService.getImplementationByFoodId(id);
    }

    @PostMapping("/implementation/add")
    @PreAuthorize("hasRole('ADMIN')")
    public Boolean addImplementation(@RequestParam("foodid") Long foodId, @RequestParam("impdetail") String impDetail, @RequestParam("steporder") int stepOrder, @RequestParam("time") Long time, @RequestParam("file") MultipartFile file){
        return adminService.addImplementation(foodId,impDetail,stepOrder,time,file);
    }

    @GetMapping("/implementation/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Boolean deleteImplementation(@PathVariable Long id){
        return adminService.deleteImplementation(id);
    }

    //comment
    @GetMapping("/comment/{id}/{numberPage}")
    @PreAuthorize("hasRole('ADMIN')")
    public List<CommentDto> getCommentByFoodId(@PathVariable Long id, @PathVariable int numberPage){
        return adminService.getCommentByFoodId(id,numberPage);
    }

    @GetMapping("/comment/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Boolean deleteComment(@PathVariable Long id){
        return adminService.deleteComment(id);
    }
}
