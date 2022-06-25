package com.project.cookguide.Cook.guide.project.services;

import com.project.cookguide.Cook.guide.project.common.JwtUtils;
import com.project.cookguide.Cook.guide.project.dto.FoodDto;
import com.project.cookguide.Cook.guide.project.dto.HistorySearchDto;
import com.project.cookguide.Cook.guide.project.entities.Food;
import com.project.cookguide.Cook.guide.project.entities.HistorySearch;
import com.project.cookguide.Cook.guide.project.entities.User;
import com.project.cookguide.Cook.guide.project.repositories.FoodRepository;
import com.project.cookguide.Cook.guide.project.repositories.HistorySearchRepository;
import com.project.cookguide.Cook.guide.project.repositories.ReactionRepository;
import com.project.cookguide.Cook.guide.project.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class SearchService {
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
    private ModelMapper modelMapper;

    @Autowired
    ReactionRepository reactionRepository;

    @Autowired
    HistorySearchRepository historySearchRepository;

    public List<FoodDto> searchByNameFood(Boolean statusNew, String nameFood) {
        if(statusNew == true){
            String authToken = getBearerTokenHeader();
            final String token = authToken.substring(7);
            String username = jwtUtils.getUsernameFromToken(token);
            User user = userRepository.findByUsername(username).get();

            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            HistorySearch historySearch = new HistorySearch(nameFood,currentTime,1,true,user);
            historySearchRepository.save(historySearch);
        }

        List<FoodDto> foodDtoList = new ArrayList<>();
        List<Food> foods = foodRepository.searchFoodByNameFood(nameFood);

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

    public List<FoodDto> searchByDescription(Boolean statusNew, String description) {
        if(statusNew==true){
            String authToken = getBearerTokenHeader();
            final String token = authToken.substring(7);
            String username = jwtUtils.getUsernameFromToken(token);
            User user = userRepository.findByUsername(username).get();

            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            HistorySearch historySearch = new HistorySearch(description,currentTime,3,true,user);
            historySearchRepository.save(historySearch);
        }

        List<FoodDto> foodDtoList = new ArrayList<>();
        List<Food> foods = foodRepository.searchFoodByDescription(description);

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

    public List<FoodDto> searchByNameMaterial(Boolean statusNew, String nameMaterial) {
        if(statusNew==true){
            String authToken = getBearerTokenHeader();
            final String token = authToken.substring(7);
            String username = jwtUtils.getUsernameFromToken(token);
            User user = userRepository.findByUsername(username).get();

            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            HistorySearch historySearch = new HistorySearch(nameMaterial,currentTime,2,true,user);
            historySearchRepository.save(historySearch);
        }

        List<FoodDto> foodDtoList = new ArrayList<>();
        List<Food> foods = foodRepository.searchFoodByNameMaterial(nameMaterial);

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

    public List<HistorySearchDto> recentSearch() {
        String authToken = getBearerTokenHeader();
        final String token = authToken.substring(7);
        String username = jwtUtils.getUsernameFromToken(token);
        User user = userRepository.findByUsername(username).get();

        List<HistorySearchDto> historySearchDtoList = new ArrayList<>();
        List<HistorySearch> historySearchList = historySearchRepository.findByUserId(user.getId());
        for(HistorySearch historySearch : historySearchList){
            HistorySearchDto historySearchDto = modelMapper.map(historySearch, HistorySearchDto.class);
            historySearchDtoList.add(historySearchDto);
        }

        return historySearchDtoList;
    }

    public Boolean deleteRecentSearch(Long id) {
        String authToken = getBearerTokenHeader();
        final String token = authToken.substring(7);
        String username = jwtUtils.getUsernameFromToken(token);
        User user = userRepository.findByUsername(username).get();

        if(!historySearchRepository.existsById(id)){
            return false;
        }
        HistorySearch historySearch = historySearchRepository.getById(id);
        if(historySearch.getUser()!=user){
            return false;
        }
        historySearch.setActivated(false);
        historySearchRepository.save(historySearch);

        return true;
    }

    public List<HistorySearchDto> featuredSearchKeywords() {
        List<HistorySearchDto> historySearchDtoList = new ArrayList<>();

        List<HistorySearch> historySearches = historySearchRepository.getFeaturedSearchKeywords();
        for(HistorySearch historySearch : historySearches){
            HistorySearchDto historySearchDto = modelMapper.map(historySearch, HistorySearchDto.class);
            historySearchDtoList.add(historySearchDto);
        }
        return historySearchDtoList;
    }

    public List<FoodDto> suggestionForYou() {
        List<FoodDto> foodDtoList = new ArrayList<>();
        List<Food> foods = foodRepository.searchSuggestion();
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
}
