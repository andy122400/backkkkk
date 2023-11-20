package com.accton.newframework.core.application.controller;

import com.accton.newframework.core.application.common.CommonResult;
import com.accton.newframework.core.application.dto.request.AutoNumberAddRequest;
import com.accton.newframework.core.application.dto.response.AutoNumberResponse;
import com.accton.newframework.core.application.dto.response.FrListResponse;
import com.accton.newframework.core.domain.autonumber.AutoNumberService;
import com.accton.newframework.core.domain.autonumber.event.AutoNumberAdd;
import com.accton.newframework.core.domain.frlist.event.FrListAdd;
import com.accton.newframework.utility.ApiException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/auto-numbers")
public class AutoNumberController {

    private final AutoNumberService autoNumberService;

    public AutoNumberController(AutoNumberService autoNumberService) {
        this.autoNumberService = autoNumberService;
    }

    @PostMapping("/list")
    public CommonResult<List<AutoNumberResponse>> list() {
        return CommonResult.success(autoNumberService.list());
    }

    @PostMapping("/add")
    public CommonResult<AutoNumberResponse> add(@RequestBody @Valid AutoNumberAddRequest request){
        AutoNumberAdd event = new AutoNumberAdd(null,request.getName(),request.getApiName(),request.getDescription(),request.getStatus());
        AutoNumberResponse res = autoNumberService.save(event);
        return CommonResult.success(res);
    }

    @PostMapping("/{id}/update")
    public CommonResult<AutoNumberResponse> update(@PathVariable("id") Long id, @RequestBody @Valid AutoNumberAddRequest request){
        AutoNumberAdd event = new AutoNumberAdd(id,request.getName(),request.getApiName(),request.getDescription(),request.getStatus());
        AutoNumberResponse res = autoNumberService.save(event);
        if (res==null){
            throw new ApiException("Can not found this auto number");
        }
        return CommonResult.success(res);
    }


    @DeleteMapping("delete/{id}")
    public CommonResult<Object> delete(@PathVariable("id") Long id){
        autoNumberService.deleteAutoNumber(id);
        return CommonResult.success();
    }

}
