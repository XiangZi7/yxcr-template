package com.yxcr.admin.controller;

import com.yxcr.bean.dto.DictDto;
import com.yxcr.bean.mapper.DictMapper;
import com.yxcr.bean.pojo.Dict;
import com.yxcr.bean.service.DictService;
import com.yxcr.common.model.ApiResult;
import com.yxcr.common.model.BusinessException;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@AllArgsConstructor
@Tag(name = "字典")
@RequestMapping("/dict")
public class dictController {


    @Resource
    private DictMapper dictMapper;

    @GetMapping("/{dict}")
    public ApiResult<?> dict(@PathVariable("dict") String dict) throws BusinessException {
        List<DictDto> dictDtos = dictMapper.selectDictData(dict);
        return ApiResult.ok(dictDtos);
    }
}
