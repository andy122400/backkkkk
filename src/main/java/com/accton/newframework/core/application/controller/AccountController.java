package com.accton.newframework.core.application.controller;


import com.accton.newframework.core.application.common.CommonResult;
import com.accton.newframework.core.application.dto.LoginDto;
import com.accton.newframework.core.domain.identity.UserService;
import com.accton.newframework.core.domain.identity.model.UserModel;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private final UserService userService;

    public AccountController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public Object login(@RequestBody @Valid LoginDto loginDto) {
        UserModel userModel = userService.findUserByUserNamePassword(loginDto.getUserName(),
                loginDto.getPassword(),
                loginDto.getIsLoginAd());
        if (userModel == null) {
            return CommonResult.failed("Username or Password is invalid!");
        }
        return CommonResult.success(userModel);
    }
}
