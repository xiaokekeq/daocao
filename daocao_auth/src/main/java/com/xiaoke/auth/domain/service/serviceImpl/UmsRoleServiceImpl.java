package com.xiaoke.auth.domain.service.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaoke.auth.domain.mapper.UmsRoleMapper;
import com.xiaoke.auth.domain.pojo.UmsRole;
import com.xiaoke.auth.domain.service.IUmsRoleService;
import org.springframework.stereotype.Service;

@Service
public class UmsRoleServiceImpl extends ServiceImpl<UmsRoleMapper, UmsRole> implements IUmsRoleService {
}
