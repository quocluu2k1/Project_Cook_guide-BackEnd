package com.project.cookguide.Cook.guide.project.controllers;

import com.project.cookguide.Cook.guide.project.common.JwtUtils;
import com.project.cookguide.Cook.guide.project.dto.FoodDto;
import com.project.cookguide.Cook.guide.project.dto.UserDto;
import com.project.cookguide.Cook.guide.project.repositories.UserRepository;
import com.project.cookguide.Cook.guide.project.services.MyEmailService;
import com.project.cookguide.Cook.guide.project.services.OtpService;
import com.project.cookguide.Cook.guide.project.services.UserService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.List;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    public JavaMailSender emailSender;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public OtpService otpService;

    @Autowired
    public MyEmailService myEmailService;

    public static String getBearerTokenHeader() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getHeader("Authorization");
    }
    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    UserRepository userRepository;

    private ModelMapper modelMapper;

    @Autowired
    UserService userService;

    @GetMapping("/profile")
    @PreAuthorize("hasRole('ADMIN')")
    public UserDto getProfile(){
        return userService.getProfile();
    }

    @PutMapping("/profile/update")
    @PreAuthorize("hasRole('ADMIN')")
    public String updateProfile(@RequestBody UserDto userDto){
        if (userService.updateProfile(userDto)){
            return "Updated successfully!";
        }
        return "Updated fail!";
    }

    @GetMapping("/generateotp")
    @ResponseBody
    public String generateOtp(@RequestParam(required = true) String email){
        if(!userService.checkExistEmail(email)){
            return "Account does not exist!";
        }
        String username = userService.getUsernameByEmail(email);
        int otp = otpService.generateOTP(username);

        String message = "Your OTP Number is "+otp;

        myEmailService.sendOtpMessage(email, "OTP - Verify", message);

        return "OTP has been sent to your email!";
    }

    @GetMapping("/validateotp")
    @ResponseBody
    public String validateOtp(@RequestParam("otpnum") int otpnum, @RequestParam("email") String email){

        final String SUCCESS = "Entered OTP is valid";

        final String FAIL = "Entered OTP is NOT valid. Please Retry!";

        String username = userService.getUsernameByEmail(email);

        if(otpnum >= 0){
            int serverOtp = otpService.getOtp(username);

            if(serverOtp > 0){
                if(otpnum == serverOtp){
                    otpService.clearOTP(username);
                    return ("Entered OTP is valid");
                }else{
                    return SUCCESS;
                }
            }else {
                return FAIL;
            }
        }else {
            return FAIL;
        }
    }

    @PostMapping("/forgotpassword")
    @ResponseBody
    public String forgotPassword(@RequestParam("newpassword") String newPassword, @RequestParam("email") String email){
//        String username = userService.getUsernameByEmail(email);
        userService.forgotPassword(newPassword, email);
        return "Change password successfully!";
    }


    @PostMapping("/changepassword")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    @ResponseBody
    public String changeNewPassword(@RequestParam("oldpassword") String oldPassword,@RequestParam("newpassword") String newPassword){
        if(userService.checkPassword(oldPassword)){
            userService.changeNewPassword(newPassword);
            return "Change password successfully!";
        }
        return "Entered wrong password!";
    }

    @GetMapping("/getbookmark")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    @ResponseBody
    public List<FoodDto> getBookmark(){
        return userService.getBookmark();
    }
}
