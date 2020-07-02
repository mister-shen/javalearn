package com.shenrs.eblog.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shenrs.eblog.entity.Post;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author shenrs
 * @since 2020-06-30
 */
public interface PostService extends IService<Post> {

//    IPage paging(Page page, Long categoryId, Long userId, Integer level, Boolean recommend, String created);
}
