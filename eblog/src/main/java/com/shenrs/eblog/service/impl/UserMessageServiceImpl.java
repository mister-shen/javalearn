package com.shenrs.eblog.service.impl;

import com.shenrs.eblog.entity.UserMessage;
import com.shenrs.eblog.mapper.UserMessageMapper;
import com.shenrs.eblog.service.UserMessageService;
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
public class UserMessageServiceImpl extends ServiceImpl<UserMessageMapper, UserMessage> implements UserMessageService {

}
