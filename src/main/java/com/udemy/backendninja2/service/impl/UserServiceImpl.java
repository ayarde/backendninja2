package com.udemy.backendninja2.service.impl;

import com.udemy.backendninja2.entity.User;
import com.udemy.backendninja2.entity.UserRole;
import com.udemy.backendninja2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service("userServiceImpl")
public class UserServiceImpl implements UserDetailsService {

    @Autowired
    @Qualifier("userRepository")
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        List<GrantedAuthority> authorities = buildAuthorities(user.getUserRole());
        return (UserDetails) buildUser(user,authorities);
    }

    private User buildUser(User user, List<GrantedAuthority> authorities){

        return new User(user.getUsername(),user.getPassword(),user.isEnabled(),true,true,true,authorities);
    }

    private List<GrantedAuthority> buildAuthorities(Set<UserRole> userRole){

        Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
        for(UserRole userRole1 : userRole){
            authorities.add(new SimpleGrantedAuthority((userRole1.getRole())));
        }
        return new ArrayList<>(authorities);
    }
}
