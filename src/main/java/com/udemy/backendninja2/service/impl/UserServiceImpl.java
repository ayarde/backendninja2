package com.udemy.backendninja2.service.impl;

import com.udemy.backendninja2.entity.User;
import com.udemy.backendninja2.entity.UserRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service("userServiceImpl")
public class UserServiceImpl implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }

    private User buildUser(User user, List<GrantedAuthority> authorities){
        return null;
    }

    private List<GrantedAuthority> buildAuthorities(Set<UserRole> userRole){
        return null;
    }
}
