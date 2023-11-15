package com.accton.newframework.core.application.controller;

import com.accton.newframework.core.application.common.CommonResult;
import com.accton.newframework.core.application.dto.request.FrListAddRequest;
import com.accton.newframework.core.application.dto.request.FrListGetRequest;
import com.accton.newframework.core.application.dto.response.FrListDetailResponse;
import com.accton.newframework.core.application.dto.response.FrListResponse;
import com.accton.newframework.core.domain.frlist.FrListService;
import com.accton.newframework.core.domain.frlist.event.FrListAdd;
import com.accton.newframework.core.domain.frlist.event.FrListGet;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/fr-lists")
public class FrListController {

    private final FrListService frListService;

    public FrListController(FrListService frListService) {
        this.frListService = frListService;
    }

    @PostMapping("/list")
    public CommonResult<List<FrListResponse>> get(@RequestBody @Valid FrListGetRequest request) throws Exception {
        FrListGet event = new FrListGet(request.getMatchIf(),request.getFieldType(),request.getContent(),false);
        return CommonResult.success(frListService.getByFilter(event));
    }

    @PostMapping("/add")
    public CommonResult<FrListResponse> add(@RequestBody @Valid FrListAddRequest request){
        FrListAdd event = new FrListAdd(request.getName(),request.getCategory(),request.getDescription(),request.getStatus());
        return CommonResult.success(frListService.add(event));
    }

    @PostMapping("{id}/detail")
    public CommonResult<List<FrListDetailResponse>> detail(@PathVariable("id") Long id){
        return CommonResult.success(frListService.detail(id));
    }

}
