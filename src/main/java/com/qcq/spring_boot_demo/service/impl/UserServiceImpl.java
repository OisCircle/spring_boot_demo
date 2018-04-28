package com.qcq.spring_boot_demo.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.qcq.spring_boot_demo.mapper.UserMapper;
import com.qcq.spring_boot_demo.entity.User;
import com.qcq.spring_boot_demo.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService {
}
