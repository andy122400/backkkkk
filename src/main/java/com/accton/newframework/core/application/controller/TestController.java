package com.accton.newframework.core.application.controller;

import com.accton.newframework.utility.ApiException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tests")
public class TestController {

    @GetMapping("/list")
    public Object get() throws Exception {
       return "STrin";
    }

    @GetMapping("/list1")
    public Object get2() throws Exception {
        throw new Exception("Demo4");
    }


}
