package com.yxcr.bean.mapper;

import java.util.List;

import com.yxcr.bean.dto.DictDto;
import com.yxcr.bean.pojo.Dict;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author Administrator
 * @description 针对表【yx_sys_dict】的数据库操作Mapper
 * @createDate 2024-02-02 17:17:24
 * @Entity com.yxcr.bean.pojo.Dict
 */
public interface DictMapper extends BaseMapper<Dict> {

    List<DictDto> selectDictData(@Param("dict") String dict);

}




