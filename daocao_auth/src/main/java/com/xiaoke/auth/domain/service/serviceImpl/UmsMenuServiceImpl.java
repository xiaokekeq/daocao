package com.xiaoke.auth.domain.service.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaoke.auth.domain.mapper.UmsMenuMapper;
import com.xiaoke.auth.domain.pojo.UmsMenu;
import com.xiaoke.auth.domain.service.IUmsMenuService;
import org.springframework.stereotype.Service;

@Service
public class UmsMenuServiceImpl extends ServiceImpl<UmsMenuMapper, UmsMenu> implements IUmsMenuService {
}
