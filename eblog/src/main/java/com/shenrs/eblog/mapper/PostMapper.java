package com.shenrs.eblog.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shenrs.eblog.entity.Post;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shenrs.eblog.vo.PostVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author shenrs
 * @since 2020-06-30
 */
public interface PostMapper extends BaseMapper<Post> {

    /**
     * 注：加上@Param(Constants.WRAPPER)后再mapper文件对应 @Param(Constants.WRAPPER)
     * @param page
     * @param wrapper
     * @return
     */
    IPage<PostVo> selectPosts(Page page, @Param(Constants.WRAPPER) QueryWrapper wrapper);
}
