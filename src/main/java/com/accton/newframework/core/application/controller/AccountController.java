package com.accton.newframework.core.application.controller;


import com.accton.newframework.core.application.common.CommonResult;
import com.accton.newframework.core.application.dto.LoginDto;
import com.accton.newframework.core.application.dto.LoginResponse;
import com.accton.newframework.core.application.logging.FrLoggable;
import com.accton.newframework.core.domain.identity.model.RoleModel;
import com.accton.newframework.core.domain.identity.service.EncryptService;
import com.accton.newframework.core.domain.identity.service.UserService;
import com.accton.newframework.core.domain.identity.model.UserModel;
import com.accton.newframework.utility.AppUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private final UserService userService;
    private final EncryptService encryptService;

    public AccountController(UserService userService,
                             EncryptService encryptService) {
        this.userService = userService;
        this.encryptService = encryptService;
    }

    @FrLoggable
    @PostMapping("/login")
    public Object login(@RequestBody @Valid LoginDto loginDto) {
        UserModel userModel = userService.findUser(loginDto.getUserName(),
                AppUtils.decodeBase64(loginDto.getPassword()),
                loginDto.getCompany());
        if (userModel == null) {
            return CommonResult.failed("Username or Password is invalid!");
        }
        LoginResponse response = new LoginResponse();
        String roles = userModel.getRoles().stream().map(RoleModel::getCode).collect(Collectors.joining(","));
        response.setAccessToken(encryptService.generateAccessToken(userModel.getUserName(), roles));
        response.setUserName(userModel.getUserName());
        response.setDisplayName(userModel.getDisplayName());
        return CommonResult.success(response);
    }

}
