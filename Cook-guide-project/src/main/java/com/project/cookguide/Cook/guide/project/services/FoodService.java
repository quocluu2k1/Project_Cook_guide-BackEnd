package com.project.cookguide.Cook.guide.project.services;

import com.project.cookguide.Cook.guide.project.common.JwtUtils;
import com.project.cookguide.Cook.guide.project.dto.FoodDto;
import com.project.cookguide.Cook.guide.project.dto.ImplementationDto;
import com.project.cookguide.Cook.guide.project.dto.IngredientDto;
import com.project.cookguide.Cook.guide.project.entities.*;
import com.project.cookguide.Cook.guide.project.repositories.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.persistence.criteria.CriteriaBuilder;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class FoodService {

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
    IngredientRepository ingredientRepository;

    @Autowired
    ImplementationRepository implementationRepository;

    @Autowired
    BookmarkRepository bookmarkRepository;

    @Autowired
    ReactionRepository reactionRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<FoodDto> getOutstandingFood(){
        List<FoodDto> foodDtoList = new ArrayList<>();
        List<Food> foods = foodRepository.getOutstandingFood();

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

    public List<FoodDto> getOutstandingFoodForNumberPage(int numberPage){
        List<FoodDto> foodDtoList = new ArrayList<>();
        if(numberPage<1){
            numberPage=1;
        }
        List<Food> foods = foodRepository.getOutstandingFoodForNumberPage(numberPage*5-5);

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
        if(foodDtoList==null){
            return null;
        }
        return foodDtoList;
    }

    public List<FoodDto> getNewFood(){
        List<FoodDto> foodDtoList = new ArrayList<>();
        List<Food> foods = foodRepository.getNewFood();

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

    public List<FoodDto> getNewFoodForNumberPage(int numberPage){
        List<FoodDto> foodDtoList = new ArrayList<>();
        if(numberPage<1){
            numberPage=1;
        }
        List<Food> foods = foodRepository.getNewFoodForNumberPage(numberPage*5-5);

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

    public List<FoodDto> getFavoriteFood(){
        List<FoodDto> foodDtoList = new ArrayList<>();
        List<Food> foods = foodRepository.getFavoriteFood();

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

    public List<FoodDto> getFavoriteFoodForNumberPage(int numberPage){
        List<FoodDto> foodDtoList = new ArrayList<>();
        if(numberPage<1){
            numberPage=1;
        }
        List<Food> foods = foodRepository.getFavoriteFoodForNumberPage(numberPage*5-5);

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

    public FoodDto getDetailCookGuide(Long id) {
        Food food = foodRepository.findById(id).get();
        Bookmark bookmark = bookmarkRepository.findByUserIdAndFoodId(2L,id);

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
        food.setComments(null);
        food.setBookmarks(null);
        food.setReactions(null);
        FoodDto foodDto = modelMapper.map(food, FoodDto.class);
        return foodDto;
    }

    public List<IngredientDto> getIngredientByFood(Long foodId){
        List<IngredientDto> ingredientDtoList = new ArrayList<>();
        List<Ingredient> ingredients = ingredientRepository.findByFoodId(foodId);
        for(Ingredient ingredient : ingredients){
            IngredientDto ingredientDto = modelMapper.map(ingredient, IngredientDto.class);
            ingredientDtoList.add(ingredientDto);
        }
        return ingredientDtoList;
    }

    public List<ImplementationDto> getImplementationByFood(Long foodId){
        List<ImplementationDto> implementationDtoList = new ArrayList<>();
        List<Implementation> implementations = implementationRepository.findByFoodId(foodId);
        for(Implementation implementation : implementations){
            ImplementationDto implementationDto = modelMapper.map(implementation, ImplementationDto.class);
            implementationDtoList.add(implementationDto);
        }
        return implementationDtoList;
    }

    public Boolean getStatusBookmarkFoodOfUser(Long foodId){
        String authToken = getBearerTokenHeader();
        final String token = authToken.substring(7);
        String username = jwtUtils.getUsernameFromToken(token);
        User user = userRepository.findByUsername(username).get();
        Long userId = user.getId();
        if(bookmarkRepository.getStatusBookmarkFoodOfUser(userId,foodId)>0){
            return true;
        }
        return false;
    }

    public Boolean addBookmarkForUser(Long foodId){
        String authToken = getBearerTokenHeader();
        final String token = authToken.substring(7);
        String username = jwtUtils.getUsernameFromToken(token);
        User user = userRepository.findByUsername(username).get();
        Long userId = user.getId();

        Bookmark bookmark = new Bookmark();
        if(bookmarkRepository.findByUserIdAndFoodId(userId,foodId)==null){
            bookmark.setFood(foodRepository.findById(foodId).get());
            bookmark.setUser(user);
            Date currentDate = new Date(System.currentTimeMillis());
            bookmark.setDate(currentDate);
            bookmarkRepository.save(bookmark);
            return true;
        }
        return false;
    }

    public Boolean deleteBookmarkForUser(Long foodId){
        String authToken = getBearerTokenHeader();
        final String token = authToken.substring(7);
        String username = jwtUtils.getUsernameFromToken(token);
        User user = userRepository.findByUsername(username).get();
        Long userId = user.getId();

        Bookmark bookmark = bookmarkRepository.findByUserIdAndFoodId(userId,foodId);
        if(bookmark!=null){
            bookmarkRepository.delete(bookmark);
            return true;
        }
        return false;
    }

    public Boolean getStatusReact(int nameReact,Long foodId){
        String authToken = getBearerTokenHeader();
        final String token = authToken.substring(7);
        String username = jwtUtils.getUsernameFromToken(token);
        User user = userRepository.findByUsername(username).get();
        Long userId = user.getId();

        if(nameReact==1){
            if(reactionRepository.getStatusReactClap(userId,foodId)>0){
                return true;
            }
        }else if(nameReact==2){
            if(reactionRepository.getStatusReactHeart(userId,foodId)>0){
                return true;
            }
        }else {
            if(reactionRepository.getStatusReactSavoring(userId,foodId)>0){
                return true;
            }
        }
        return false;
    }

    public Boolean addReaction(int nameReact, Long foodId){
        String authToken = getBearerTokenHeader();
        final String token = authToken.substring(7);
        String username = jwtUtils.getUsernameFromToken(token);
        User user = userRepository.findByUsername(username).get();
        Long userId = user.getId();

        Food food = foodRepository.getById(foodId);
        if(!foodRepository.existsById(foodId)){
            return false;
        }
        Reaction reaction = reactionRepository.findByUserIdAndFoodId(userId,foodId);

        if(reaction==null){
            reaction = new Reaction();
            reaction.setUser(user);
            reaction.setFood(food);
            if(nameReact==1){
                reaction.setrHearts(false);
                reaction.setrSavoring(false);
            }else if(nameReact==2){
                reaction.setrClaps(false);
                reaction.setrSavoring(false);
            }else {
                reaction.setrClaps(false);
                reaction.setrHearts(false);
            }
        }
        if(nameReact==1){
            reaction.setrClaps(true);
        }else if(nameReact==2){
            reaction.setrHearts(true);
        }else {
            reaction.setrSavoring(true);
        }
        reactionRepository.save(reaction);
        return true;
    }

    public Boolean deleteReaction(int nameReact, Long foodId){
        String authToken = getBearerTokenHeader();
        final String token = authToken.substring(7);
        String username = jwtUtils.getUsernameFromToken(token);
        User user = userRepository.findByUsername(username).get();
        Long userId = user.getId();

        if(!foodRepository.existsById(foodId)){
            return false;
        }
        Reaction reaction = reactionRepository.findByUserIdAndFoodId(userId,foodId);
        if(reaction==null){
            return true;
        }else{
            if(nameReact==1){
                reaction.setrClaps(false);
            }else if(nameReact==2){
                reaction.setrHearts(false);
            }else {
                reaction.setrSavoring(false);
            }
        }
        reactionRepository.save(reaction);
        return true;
    }
}
