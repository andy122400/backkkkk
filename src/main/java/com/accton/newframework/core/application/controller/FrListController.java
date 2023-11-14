package com.accton.newframework.core.application.controller;

import com.accton.newframework.core.application.common.CommonResult;
import com.accton.newframework.core.application.dto.FrListResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/fr-lists")
public class FrListController {


    @GetMapping("/list")
    public Object get(){
        List<FrListResponse> dataRes = new ArrayList<>();
        return CommonResult.success(dataRes);
    }

}
