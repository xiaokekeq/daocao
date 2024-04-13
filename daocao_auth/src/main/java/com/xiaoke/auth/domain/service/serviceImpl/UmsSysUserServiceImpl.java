package com.xiaoke.auth.domain.service.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaoke.auth.domain.mapper.UmsSysUserMapper;
import com.xiaoke.auth.domain.pojo.UmsSysUser;
import com.xiaoke.auth.domain.service.IUmsSysUserService;
import org.springframework.stereotype.Service;

@Service
public class UmsSysUserServiceImpl extends ServiceImpl<UmsSysUserMapper, UmsSysUser> implements IUmsSysUserService {
}
