package com.project.cookguide.Cook.guide.project.services;

import com.project.cookguide.Cook.guide.project.common.JwtUtils;
import com.project.cookguide.Cook.guide.project.dto.CommentDetailDto;
import com.project.cookguide.Cook.guide.project.dto.CommentDto;
import com.project.cookguide.Cook.guide.project.dto.HistoryCommentDetailDto;
import com.project.cookguide.Cook.guide.project.dto.HistoryCommentDto;
import com.project.cookguide.Cook.guide.project.entities.Comment;
import com.project.cookguide.Cook.guide.project.entities.Food;
import com.project.cookguide.Cook.guide.project.entities.User;
import com.project.cookguide.Cook.guide.project.repositories.CommentRepository;
import com.project.cookguide.Cook.guide.project.repositories.FoodRepository;
import com.project.cookguide.Cook.guide.project.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {
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

    @Autowired ImageCommentStorageService imageCommentStorageService;

    @Autowired
    private ModelMapper modelMapper;

    public Boolean addComment(Long foodId, String content, MultipartFile file){
        String authToken = getBearerTokenHeader();
        final String token = authToken.substring(7);
        String username = jwtUtils.getUsernameFromToken(token);
        User user = userRepository.findByUsername(username).get();

        Food food = foodRepository.getById(foodId);
        if(food==null){
            return false;
        }
        String image;
        if((file==null)||(file.isEmpty())){
            image = null;
        }else{
            image = "/images/comment/"+imageCommentStorageService.storeFile(file);
        }
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Comment comment = new Comment(content,image,timestamp,user,food);
        commentRepository.save(comment);
        return true;
    }

    public Boolean addCommentNormal(Long foodId, String content) {
        String authToken = getBearerTokenHeader();
        final String token = authToken.substring(7);
        String username = jwtUtils.getUsernameFromToken(token);
        User user = userRepository.findByUsername(username).get();

        Food food = foodRepository.getById(foodId);
        if(food==null){
            return false;
        }
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Comment comment = new Comment(content,null,timestamp,user,food);
        commentRepository.save(comment);

        return true;
    }

    public Boolean deleteCommentByUser(Long cmtId){
        String authToken = getBearerTokenHeader();
        final String token = authToken.substring(7);
        String username = jwtUtils.getUsernameFromToken(token);
        User user = userRepository.findByUsername(username).get();

        if(!commentRepository.existsById(cmtId)){
            return false;
        }
        Comment comment = commentRepository.getById(cmtId);
        if(comment.getUser()!=user){
            return false;
        }else {
            imageCommentStorageService.deleteFile(comment.getImage());
            commentRepository.delete(comment);
            return true;
        }
    }

    public List<CommentDetailDto> getByFoodId(Long foodId, int nPage){
        String authToken = getBearerTokenHeader();
        final String token = authToken.substring(7);
        String username = jwtUtils.getUsernameFromToken(token);
        User user = userRepository.findByUsername(username).get();

        List<CommentDetailDto> commentDetailDtoList = new ArrayList<>();
        if(nPage<1){
            nPage=1;
        }
        List<Comment> commentList = commentRepository.findAllByFoodId(foodId,nPage*10-10);

        for(Comment comment : commentList){
            CommentDetailDto commentDetailDto = modelMapper.map(comment,CommentDetailDto.class);
            if(comment.getUser()==user){
                commentDetailDto.setStatusUser(true);
            }else {
                commentDetailDto.setStatusUser(false);
            }
            commentDetailDto.setUserId(user.getId());
            commentDetailDto.setFullName(user.getFullName());
            commentDetailDto.setAvatar(user.getAvatar());
            commentDetailDtoList.add(commentDetailDto);
        }
        return commentDetailDtoList;
    }

    public int getNumberComment(Long foodId) {
        return commentRepository.getNumberCommentByFoodId(foodId);
    }

    public List<HistoryCommentDto> getHistoryCommentOfUser() {
        String authToken = getBearerTokenHeader();
        final String token = authToken.substring(7);
        String username = jwtUtils.getUsernameFromToken(token);
        User user = userRepository.findByUsername(username).get();

        List<HistoryCommentDto> historyCommentDtoList = new ArrayList<>();

        List<Date> dateCmtList = commentRepository.getDateCommentByUserId(user.getId());
        for(Date date : dateCmtList){
            HistoryCommentDto historyCommentDto = new HistoryCommentDto();
            historyCommentDto.setDate(date);
            List<Comment> comments = commentRepository.getCommentByDate(date);
            List<HistoryCommentDetailDto> historyCommentDetailDtoList = new ArrayList<>();
            for(Comment comment : comments){
                HistoryCommentDetailDto historyCommentDetailDto = new HistoryCommentDetailDto();
                historyCommentDetailDto.setCmtId(comment.getCmtId());
                historyCommentDetailDto.setFoodId(comment.getFood().getFoodId());
                historyCommentDetailDto.setNameFood(comment.getFood().getName());
                historyCommentDetailDto.setContentCmt(comment.getContent());
                historyCommentDetailDto.setImageCmt(comment.getImage());
                historyCommentDetailDto.setTimeCmt(comment.getTime());
                historyCommentDetailDtoList.add(historyCommentDetailDto);
            }
            historyCommentDto.setList(historyCommentDetailDtoList);
            historyCommentDtoList.add(historyCommentDto);
        }

        return historyCommentDtoList;
    }


}
