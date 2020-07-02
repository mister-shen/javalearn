package com.shenrs.eblog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shenrs.eblog.entity.Post;
import com.shenrs.eblog.mapper.PostMapper;
import com.shenrs.eblog.service.PostService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.shenrs.eblog.mapper.PostMapper;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author shenrs
 * @since 2020-06-30
 */
@Service
public class PostServiceImpl extends ServiceImpl<PostMapper, Post> implements PostService {

    /*@Autowired
    private PostMapper postMapper;

    @Override
    public IPage paging(Page page, Long categoryId, Long userId, Integer level, Boolean recommend, String order) {
        QueryWrapper wrapper = new QueryWrapper<Post>()
                .eq(categoryId != null, "category_id", categoryId)
                .eq(userId != null, "user_id", userId)
                .eq(level == 0, "level", 0)
                .eq(level > 0, "level", 0)
                .eq(recommend != null, "recommend", recommend?1:0)
                .orderByDesc(order != null , order);

        return postMapper.selectPosts(page, wrapper);
    }*/
}
