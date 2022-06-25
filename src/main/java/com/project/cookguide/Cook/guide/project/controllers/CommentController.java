package com.project.cookguide.Cook.guide.project.controllers;

import com.project.cookguide.Cook.guide.project.dto.CommentDetailDto;
import com.project.cookguide.Cook.guide.project.dto.CommentDto;
import com.project.cookguide.Cook.guide.project.dto.HistoryCommentDto;
import com.project.cookguide.Cook.guide.project.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/comment")
public class CommentController {
    @Autowired
    CommentService commentService;

    @PostMapping("/add")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public Boolean addComment(@RequestParam("foodId") Long foodId,@RequestParam("content") String content,@RequestParam("file") MultipartFile file){
        return commentService.addComment(foodId,content,file);
    }

    @PostMapping("/addnormal")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public Boolean addComment(@RequestParam("foodId") Long foodId,@RequestParam("content") String content){
        return commentService.addCommentNormal(foodId,content);
    }

    @GetMapping("/delete/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public Boolean deleteCommentByUser(@PathVariable Long id){
        return commentService.deleteCommentByUser(id);
    }

    @GetMapping("/get")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public List<CommentDetailDto> getByFoodId(@RequestParam("foodid") Long foodId, @RequestParam("npage") int nPage){
        return commentService.getByFoodId(foodId,nPage);
    }

    @GetMapping("/getnumbercomment/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public int getNumberComment(@PathVariable Long id){
        return commentService.getNumberComment(id);
    }

    @GetMapping("/history")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public List<HistoryCommentDto> getHistoryCommentOfUser(){
        return commentService.getHistoryCommentOfUser();
    }
}
