package com.project.cookguide.Cook.guide.project.controllers;

import com.project.cookguide.Cook.guide.project.dto.FoodDto;
import com.project.cookguide.Cook.guide.project.dto.HistorySearchDto;
import com.project.cookguide.Cook.guide.project.services.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/search")
public class SearchController {
    @Autowired
    SearchService searchService;

    @GetMapping("/namefood")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public List<FoodDto> searchByNameFood(@RequestParam("new") Boolean statusNew, @RequestParam("q") String nameFood){
        return searchService.searchByNameFood(statusNew,nameFood);
    }

    @GetMapping("/namematerial")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public List<FoodDto> searchByNameMaterial(@RequestParam("new") Boolean statusNew,@RequestParam("q") String nameMaterial){
        return searchService.searchByNameMaterial(statusNew,nameMaterial);
    }

    @GetMapping("/description")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public List<FoodDto> searchByDescription(@RequestParam("new") Boolean statusNew,@RequestParam("q") String description){
        return searchService.searchByDescription(statusNew,description);
    }

    @GetMapping("/recent")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public List<HistorySearchDto> recentSearch(){
        return searchService.recentSearch();
    }

    @GetMapping("/recent/delete/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public Boolean deleteRecentSearch(@PathVariable Long id){
        return searchService.deleteRecentSearch(id);
    }

    @GetMapping("/featuredkeywords")
    public List<HistorySearchDto> featuredSearchKeywords(){
        return searchService.featuredSearchKeywords();
    }

    @GetMapping("suggestion")
    public List<FoodDto> suggestionForYou(){
        return searchService.suggestionForYou();
    }
}
