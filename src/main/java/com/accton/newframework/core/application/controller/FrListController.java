package com.accton.newframework.core.application.controller;

import com.accton.newframework.core.application.common.CommonResult;
import com.accton.newframework.core.application.dto.request.FrListAddRequest;
import com.accton.newframework.core.application.dto.response.FrListResponse;
import com.accton.newframework.core.domain.frlist.FrListService;
import com.accton.newframework.core.domain.frlist.event.FrListAdd;
import com.accton.newframework.core.domain.frlist.event.FrListGet;
import com.accton.newframework.utility.contants.CriteriaEnum;
import com.accton.newframework.utility.contants.FieldTypeEnum;
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

    @GetMapping("/list")
    public CommonResult<List<FrListResponse>> get(@RequestParam("match_if") CriteriaEnum matchIf,
                                                  @RequestParam("field_type")  FieldTypeEnum fieldType,
                                                  @RequestParam("content") String content) {
        FrListGet event = new FrListGet(matchIf,fieldType,content,false);
        return CommonResult.success(frListService.getByFilter(event));
    }

    @PostMapping("/add")
    public CommonResult<FrListResponse> add(@RequestBody @Valid FrListAddRequest request){
        FrListAdd event = new FrListAdd(request.getName(),request.getCategory(),request.getDescription(),request.getStatus());
        return CommonResult.success(frListService.add(event));
    }
}
