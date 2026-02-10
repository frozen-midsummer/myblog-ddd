package com.wjx.myblog.infrastructure.springsecurity;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.wjx.common.exception.SystemException;
import com.wjx.myblog.domain.common.MyblogErrorCodeEnum;
import com.wjx.myblog.infrastructure.database.dataobject.UserInfoDO;
import com.wjx.myblog.infrastructure.database.mapper.UserInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LambdaQueryWrapper<UserInfoDO> queryWrapper = Wrappers.lambdaQuery(UserInfoDO.class)
                .eq(UserInfoDO::getUsername, username);
        UserInfoDO userInfo = userInfoMapper.selectOne(queryWrapper);
        if (userInfo == null) {
            throw new SystemException(MyblogErrorCodeEnum.RECORD_NOT_FOUND.code(), "User not found with username: " + username);
        }
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        return new SecurityUser(
                userInfo.getId(),
                userInfo.getUsername(),
                userInfo.getPassword(),
                true,
                true,
                true,
                true,
                authorities
        );
    }
}
