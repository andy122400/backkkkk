package com.accton.newframework.core.application.controller;

import com.accton.newframework.core.application.logging.FrLoggable;
import com.accton.newframework.core.domain.identity.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService =userService;
    }
    @GetMapping("/list")
    @FrLoggable
    public Object get() throws Exception {
       return userService.findOneByUserName("marco1");
    }

    @GetMapping("/list1")
    public Object get1() throws Exception{
        throw new Exception("Demo2");
    }

}
