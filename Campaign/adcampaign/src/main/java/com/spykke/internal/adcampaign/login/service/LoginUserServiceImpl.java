package com.spykke.internal.adcampaign.login.service;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.spykke.internal.adcampaign.login.entity.LoginUser;
import com.spykke.internal.adcampaign.login.repository.RoleRepository;
import com.spykke.internal.adcampaign.login.repository.LoginUserRepository;

@Service
public class LoginUserServiceImpl implements LoginUserService {
    @Autowired
    private LoginUserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void save(LoginUser user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(new HashSet<>(roleRepository.findAll()));
        userRepository.save(user);
    }

    @Override
    public LoginUser findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
