package com.accton.newframework.core.application.controller;

import com.accton.newframework.core.application.common.CommonResult;
import com.accton.newframework.core.application.dto.request.FrListAddRequest;
import com.accton.newframework.core.application.dto.request.FrListDetailAddRequest;
import com.accton.newframework.core.application.dto.request.FrListGetRequest;
import com.accton.newframework.core.application.dto.response.FrListDetailResponse;
import com.accton.newframework.core.application.dto.response.FrListResponse;
import com.accton.newframework.core.domain.frlist.FrListService;
import com.accton.newframework.core.domain.frlist.event.FrListAdd;
import com.accton.newframework.core.domain.frlist.event.FrListDetailAdd;
import com.accton.newframework.core.domain.frlist.event.FrListGet;
import com.accton.newframework.utility.ApiException;
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
        FrListAdd event = new FrListAdd(null,request.getName(),request.getCategory(),request.getDescription(),request.getStatus());
        FrListResponse res = frListService.save(event);
        if (res==null){
            throw new ApiException("Can not found this FrList");
        }
        return CommonResult.success(res);
    }

    @PostMapping("{id}/update")
    public CommonResult<FrListResponse> update(@PathVariable("id") Long id,@RequestBody @Valid FrListAddRequest request){
        FrListAdd event = new FrListAdd(id,request.getName(),request.getCategory(),request.getDescription(),request.getStatus());
        FrListResponse res = frListService.save(event);
        if (res==null){
            throw new ApiException("Can not found this FrList");
        }
        return CommonResult.success(res);
    }


    @PostMapping("{id}/detail")
    public CommonResult<List<FrListDetailResponse>> detail(@PathVariable("id") Long id){
        return CommonResult.success(frListService.detail(id));
    }

    @DeleteMapping("delete/{id}")
    public CommonResult<Object> delete(@PathVariable("id") Long id){
        frListService.deleteFrList(id);
        return CommonResult.success();
    }


    @PostMapping("{parent_id}/detail/add")
    public CommonResult<FrListDetailResponse> add(@PathVariable("parent_id") Long parentId, @RequestBody @Valid FrListDetailAddRequest request){
        FrListDetailAdd event =  FrListDetailAdd.builder()
                .parentId(parentId)
                .name(request.getName())
                .value(request.getValue())
                .sort(request.getSort())
                .parentEntry(request.getParentEntry())
                .description(request.getDescription())
                .status(request.getStatus())
                .build();
        return CommonResult.success(frListService.save(event));
    }

    @DeleteMapping("delete/detail/{id}")
    public CommonResult<Object> deleteDetail(@PathVariable("id") Long id){
        frListService.deleteFrListDetail(id);
        return CommonResult.success();
    }
    @PostMapping("/detail/{id}/update")
    public CommonResult<FrListDetailResponse> update(@PathVariable("id") Long id,
                                                     @RequestBody @Valid FrListDetailAddRequest request){
        FrListDetailAdd event =  FrListDetailAdd.builder()
                .id(id)
                .name(request.getName())
                .value(request.getValue())
                .sort(request.getSort())
                .parentEntry(request.getParentEntry())
                .description(request.getDescription())
                .status(request.getStatus())
                .build();
        return CommonResult.success(frListService.save(event));
    }
}
