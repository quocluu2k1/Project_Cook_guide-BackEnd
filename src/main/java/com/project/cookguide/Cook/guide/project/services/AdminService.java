package com.project.cookguide.Cook.guide.project.services;

import com.project.cookguide.Cook.guide.project.common.JwtUtils;
import com.project.cookguide.Cook.guide.project.dto.CommentDto;
import com.project.cookguide.Cook.guide.project.dto.FoodDto;
import com.project.cookguide.Cook.guide.project.dto.ImplementationDto;
import com.project.cookguide.Cook.guide.project.dto.IngredientDto;
import com.project.cookguide.Cook.guide.project.entities.Comment;
import com.project.cookguide.Cook.guide.project.entities.Food;
import com.project.cookguide.Cook.guide.project.entities.Implementation;
import com.project.cookguide.Cook.guide.project.entities.Ingredient;
import com.project.cookguide.Cook.guide.project.repositories.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class AdminService {
    public static String getBearerTokenHeader() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getHeader("Authorization");
    }
    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    UserRepository userRepository;

    @Autowired
    FoodRepository foodRepository;

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    IngredientRepository ingredientRepository;

    @Autowired
    ImplementationRepository implementationRepository;

    @Autowired
    ReactionRepository reactionRepository;

    @Autowired
    ImageFoodStorageService imageFoodStorageService;

    @Autowired
    ImageImplementationStorageService imageImplementationStorageService;

    @Autowired
    ImageCommentStorageService imageCommentStorageService;

    @Autowired
    private ModelMapper modelMapper;

    public List<FoodDto> getListFood(int numberPage) {
        List<FoodDto> foodDtoList = new ArrayList<>();

        if(numberPage<1){
            numberPage=1;
        }
        Pageable pageable = PageRequest.of(numberPage-1,10);
        List<Food> foods = foodRepository.findAll(pageable).getContent();

        for(Food food : foods){
            Long numberOfHearts = reactionRepository.countReactHeart(food.getFoodId());
            Long numberOfClaps = reactionRepository.countReactClap(food.getFoodId());
            Long numberOFSavoring = reactionRepository.countReactSavoring(food.getFoodId());
            if(numberOfHearts==null){
                numberOfHearts=0L;
            }
            if(numberOfClaps==null){
                numberOfClaps=0L;
            }
            if(numberOFSavoring==null){
                numberOFSavoring=0L;
            }

            food.setnHearts(numberOfHearts);
            food.setnClaps(numberOfClaps);
            food.setnSavoring(numberOFSavoring);
            FoodDto foodDto = modelMapper.map(food, FoodDto.class);
            foodDtoList.add(foodDto);
        }
        return foodDtoList;
    }

    public Long addFood(String name, String description, int level, int totalTime, MultipartFile file1, MultipartFile file2, MultipartFile file3) {
        Food food = new Food();
        food.setName(name);
        food.setDescription(description);
        food.setLevel(level);
        food.setTotalTime(totalTime);
        Date currentDate = new Date(System.currentTimeMillis());
        food.setDate(currentDate);

        String image1 = "/images/food/"+imageFoodStorageService.storeFile(file1);
        food.setFoodImage1(image1);

        String image2 = "/images/food/"+imageFoodStorageService.storeFile(file2);
        food.setFoodImage2(image2);

        String image3 = "/images/food/"+imageFoodStorageService.storeFile(file3);
        food.setFoodImage3(image3);

        foodRepository.save(food);
        return food.getFoodId();
    }

    public Boolean deleteFood(Long id) {
        if(!foodRepository.existsById(id)){
            return false;
        }
        Food food = foodRepository.getById(id);
        imageFoodStorageService.deleteFile(food.getFoodImage1());
        imageFoodStorageService.deleteFile(food.getFoodImage2());
        imageFoodStorageService.deleteFile(food.getFoodImage3());
        foodRepository.delete(food);
        return true;
    }

    public List<IngredientDto> getIngredientByFoodId(Long id) {
        List<IngredientDto> ingredientDtoList = new ArrayList<>();
        List<Ingredient> ingredients = ingredientRepository.findByFoodId(id);
        for(Ingredient ingredient : ingredients){
            IngredientDto ingredientDto = modelMapper.map(ingredient, IngredientDto.class);
            ingredientDtoList.add(ingredientDto);
        }
        return ingredientDtoList;
    }

    public Boolean addIngredient(Long foodId, String name, String amount) {
        if(!foodRepository.existsById(foodId)){
            return false;
        }
        Food food = foodRepository.getById(foodId);
        Ingredient ingredient = new Ingredient();
        ingredient.setFood(food);
        ingredient.setName(name);
        ingredient.setAmount(amount);
        ingredientRepository.save(ingredient);

        return true;
    }

    public Boolean deleteIngredient(Long id) {
        if(!ingredientRepository.existsById(id)){
            return false;
        }
        Ingredient ingredient = ingredientRepository.getById(id);
        ingredientRepository.delete(ingredient);

        return true;
    }

    public List<ImplementationDto> getImplementationByFoodId(Long id) {
        List<ImplementationDto> implementationDtoList = new ArrayList<>();
        List<Implementation> implementations = implementationRepository.findByFoodId(id);
        for (Implementation implementation : implementations){
            ImplementationDto implementationDto = modelMapper.map(implementation, ImplementationDto.class);
            implementationDtoList.add(implementationDto);
        }
        return implementationDtoList;
    }

    public Boolean addImplementation(Long foodId, String impDetail, int stepOrder, Long time, MultipartFile file) {
        if(!foodRepository.existsById(foodId)){
            return false;
        }
        Food food = foodRepository.getById(foodId);
        Implementation implementation = new Implementation();
        implementation.setFood(food);
        implementation.setImpDetail(impDetail);
        implementation.setStepOrder(stepOrder);
        implementation.setTime(time);
        if((file.isEmpty())||(file==null)){
            implementation.setStepImage(null);
        }else{
            String image = "/images/implementation/"+imageImplementationStorageService.storeFile(file);
            implementation.setStepImage(image);
        }
        implementationRepository.save(implementation);
        return true;
    }

    public Boolean deleteImplementation(Long id) {
        if(!implementationRepository.existsById(id)){
            return false;
        }
        Implementation implementation = implementationRepository.getById(id);
        imageImplementationStorageService.deleteFile(implementation.getStepImage());
        implementationRepository.delete(implementation);

        return true;
    }

    public List<CommentDto> getCommentByFoodId(Long id, int numberPage) {
        if(!foodRepository.existsById(id)){
            return null;
        }
        List<CommentDto> commentDtoList = new ArrayList<>();
        List<Comment> comments = commentRepository.findAllByFoodId(id,numberPage);
        for(Comment comment : comments){
            CommentDto commentDto = modelMapper.map(comment, CommentDto.class);
            commentDtoList.add(commentDto);
        }

        return commentDtoList;
    }

    public Boolean deleteComment(Long id) {
        if(!commentRepository.existsById(id)){
            return false;
        }
        Comment comment = commentRepository.getById(id);
        imageCommentStorageService.deleteFile(comment.getImage());
        commentRepository.delete(comment);
        return true;
    }
}
