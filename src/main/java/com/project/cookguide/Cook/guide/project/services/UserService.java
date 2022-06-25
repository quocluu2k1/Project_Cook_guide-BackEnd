package com.project.cookguide.Cook.guide.project.services;

import com.project.cookguide.Cook.guide.project.common.JwtUtils;
import com.project.cookguide.Cook.guide.project.common.ObjectMapperUtils;
import com.project.cookguide.Cook.guide.project.dto.FoodDto;
import com.project.cookguide.Cook.guide.project.dto.UserDto;
import com.project.cookguide.Cook.guide.project.entities.Bookmark;
import com.project.cookguide.Cook.guide.project.entities.Food;
import com.project.cookguide.Cook.guide.project.entities.User;
import com.project.cookguide.Cook.guide.project.repositories.BookmarkRepository;
import com.project.cookguide.Cook.guide.project.repositories.FoodRepository;
import com.project.cookguide.Cook.guide.project.repositories.ReactionRepository;
import com.project.cookguide.Cook.guide.project.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    public static String getBearerTokenHeader() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getHeader("Authorization");
    }
    @Autowired
    IStorageService storageService;
    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    UserRepository userRepository;
    @Autowired
    FoodRepository foodRepository;
    @Autowired
    BookmarkRepository bookmarkRepository;
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    ReactionRepository reactionRepository;

    @Autowired
    private ModelMapper modelMapper;

    public UserDto getProfile(){
        String authToken = getBearerTokenHeader();
        final String token = authToken.substring(7);
        String username = jwtUtils.getUsernameFromToken(token);
        User user = userRepository.findByUsername(username).get();
        UserDto userDto = modelMapper.map(user, UserDto.class);
        return userDto;
    }

    public boolean updateProfile(UserDto userDto){
        String authToken = getBearerTokenHeader();
        final String token = authToken.substring(7);
        String username = jwtUtils.getUsernameFromToken(token);
        User user = userRepository.findByUsername(username).get();
        if(user==null){
            return false;
        }
        user.setFullName(userDto.getFullName());
        user.setPhone(userDto.getPhone());
        userRepository.save(user);
        return true;
    }

    public boolean checkExistEmail(String email){
        return userRepository.existsByEmail(email);
    }

    public String getUsernameByEmail(String email){
        User user = userRepository.findByEmail(email).get();
        return user.getUsername();
    }

    public boolean checkPassword(String oldPassword){
        String authToken = getBearerTokenHeader();
        final String token = authToken.substring(7);
        String username = jwtUtils.getUsernameFromToken(token);

        User user = userRepository.findByUsername(username).get();

        if(encoder.matches(oldPassword,user.getPassword())){
            return true;
        }
        return false;
    }
    public void forgotPassword(String newPassword, String email){
        User user = userRepository.findByEmail(email).get();
        String newPasswordEncode = encoder.encode(newPassword);
        user.setPassword(newPasswordEncode);
        userRepository.save(user);
    }

    public void changeNewPassword(String newPassword){
        String authToken = getBearerTokenHeader();
        final String token = authToken.substring(7);
        String username = jwtUtils.getUsernameFromToken(token);

        User user = userRepository.findByUsername(username).get();
        String newPasswordEncode = encoder.encode(newPassword);
        user.setPassword(newPasswordEncode);
        userRepository.save(user);
    }

    public List<FoodDto> getBookmark(){
        String authToken = getBearerTokenHeader();
        final String token = authToken.substring(7);
        String username = jwtUtils.getUsernameFromToken(token);
        User user = userRepository.findByUsername(username).get();
        Long userId = user.getId();


        List<Bookmark> bookmarks = bookmarkRepository.findAllByUserId(userId);
        List<FoodDto> foodDtoList = new ArrayList<>();
        for(Bookmark bookmark : bookmarks){
            Food food = bookmark.getFood();
            Long numberOfHearts = reactionRepository.countReactHeart(food.getFoodId());
            Long numberOfClaps = reactionRepository.countReactClap(food.getFoodId());
            Long numberOFSavoring = reactionRepository.countReactSavoring(food.getFoodId());

            food.setnHearts(numberOfHearts);
            food.setnClaps(numberOfClaps);
            food.setnSavoring(numberOFSavoring);
            FoodDto foodDto = modelMapper.map(food, FoodDto.class);
            foodDtoList.add(foodDto);
        }
        return foodDtoList;

    }

    public Boolean changeAvatar(MultipartFile file){
        String authToken = getBearerTokenHeader();
        final String token = authToken.substring(7);
        String username = jwtUtils.getUsernameFromToken(token);
        User user = userRepository.findByUsername(username).get();
        try {
            String generatedFileName = storageService.storeFile(file);
            if(user.getAvatar()!="avatar-default.png"){
                storageService.deleteFile(user.getAvatar());
            }
            user.setAvatar("/images/avatar/"+generatedFileName);
            userRepository.save(user);
            return true;
        }catch (Exception e){
            throw new RuntimeException("Error: ",e);
        }
    }
}
