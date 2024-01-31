package com.HotelBooking.MVPHotelBooking.Controller;

import com.HotelBooking.MVPHotelBooking.Entity.User;
import com.HotelBooking.MVPHotelBooking.Service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("")
    public void saveUser(@RequestBody User user){
        userService.addUser(user);
    }

}
