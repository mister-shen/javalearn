package com.shenrs.eblog.service.impl;

import com.shenrs.eblog.entity.User;
import com.shenrs.eblog.mapper.UserMapper;
import com.shenrs.eblog.service.UserService;
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
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
