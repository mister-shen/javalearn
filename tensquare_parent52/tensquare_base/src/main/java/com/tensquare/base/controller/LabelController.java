package com.tensquare.base.controller;

import com.tensquare.base.pojo.Label;
import com.tensquare.base.service.LabelService;
import entity.PageResult;
import entity.Result;
import entity.StatusCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author shenrs
 * @Description
 * @create 2019-07-23 14:12
 **/
@RestController
@CrossOrigin/*解决跨域问题*/
@RequestMapping("/lable")
public class LabelController {
    @Autowired
    private LabelService labelService;

    @GetMapping()
    public Result findAll() {
        return Result.success(StatusCodeEnum.STATUS_SEARCH_OK, labelService.findAll());
    }

    @GetMapping("/{labelId}")
    public Result findById(@PathVariable("labelId") String labelId) {
        return Result.success(StatusCodeEnum.STATUS_SEARCH_OK, labelService.findById(labelId));
    }

    @PostMapping()
    public Result save(@RequestBody Label label) {
        labelService.save(label);
        return Result.success(StatusCodeEnum.STATUS_SAVE_OK);
    }

    @PutMapping("/{labelId}")
    public Result update(@PathVariable("labelId") String labelId, @RequestBody Label label) {
        labelService.update(label);
        return Result.success(StatusCodeEnum.STATUS_UPDATE_OK);
    }

    @DeleteMapping("/{labelId}")
    public Result deleteById(@PathVariable("labelId") String labelId) {
        labelService.deleteById(labelId);
        return Result.success(StatusCodeEnum.STATUS_DEL_OK);
    }

    @PostMapping("/search")
    public Result findSearch(@RequestBody Label label) {
        List<Label> list = labelService.findSearch(label);
        return Result.success(StatusCodeEnum.STATUS_SEARCH_OK, list);
    }
    @PostMapping("/search/{page}/{size}")
    public Result findQuery(@RequestBody Label label,
                            @PathVariable int page,
                            @PathVariable int size) {
        Page<Label> pageData = labelService.findQuery(label, page, size);
        return Result.success(StatusCodeEnum.STATUS_SEARCH_OK,
                new PageResult<Label>(pageData.getTotalElements(), pageData.getContent()));
    }
}
