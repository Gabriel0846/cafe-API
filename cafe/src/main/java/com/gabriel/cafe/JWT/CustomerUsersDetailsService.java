package com.gabriel.cafe.JWT;

import java.util.ArrayList;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.gabriel.cafe.dao.UserDao;
import com.gabriel.cafe.POJO.User;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CustomerUsersDetailsService implements UserDetailsService {

    @Autowired
    UserDao userDao;

    private User userDetail;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("inside loadUserByUsername {}", username);
        userDetail = userDao.findByEmailId(username);
        if (Objects.isNull(userDetail)) {
            throw new UsernameNotFoundException("Usuário não encontrado.");
        }
        return org.springframework.security.core.userdetails.User.withUsername(userDetail.getEmail())
                .password(userDetail.getPassword())
                .roles(userDetail.getPerfil())
                .build();
    }

    public User getUserDetail() {
        return userDetail;
    }
}
