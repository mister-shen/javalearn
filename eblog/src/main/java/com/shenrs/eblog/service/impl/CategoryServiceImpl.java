package com.shenrs.eblog.service.impl;

import com.shenrs.eblog.entity.Category;
import com.shenrs.eblog.mapper.CategoryMapper;
import com.shenrs.eblog.service.CategoryService;
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
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

}
