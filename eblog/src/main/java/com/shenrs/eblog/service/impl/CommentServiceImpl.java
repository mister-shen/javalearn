package com.shenrs.eblog.service.impl;

import com.shenrs.eblog.entity.Comment;
import com.shenrs.eblog.mapper.CommentMapper;
import com.shenrs.eblog.service.CommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author shenrs
 * @since 2020-06-30
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

}
