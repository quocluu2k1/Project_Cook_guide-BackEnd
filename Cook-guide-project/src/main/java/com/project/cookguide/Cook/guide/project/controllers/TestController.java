package com.project.cookguide.Cook.guide.project.controllers;

import com.project.cookguide.Cook.guide.project.common.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.ObjectInputStream;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {

    public static String getBearerTokenHeader() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getHeader("Authorization");
    }
    @Autowired
    JwtUtils jwtUtils;
    @GetMapping("/all")
    public String allAccess(){
        return "public content.";
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public String userAccess(){
        String authToken = getBearerTokenHeader();
        final String token = authToken.substring(7);
        String username = jwtUtils.getUsernameFromToken(token);
        return "User Content. "+username;
    }

    @GetMapping("/mod")
    @PreAuthorize("hasRole('MODERATOR')")
    public String moderatorAccess() {
        return "Moderator Board.";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminAccess() {
//        HttpServletRequest request

//        String authToken = request.getHeader("Authorization");
        String authToken = getBearerTokenHeader();
        final String token = authToken.substring(7);
        String username = jwtUtils.getUsernameFromToken(token);
        return "Admin Board."+username;
    }
}
