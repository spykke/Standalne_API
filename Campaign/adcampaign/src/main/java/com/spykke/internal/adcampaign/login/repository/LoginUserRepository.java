package com.spykke.internal.adcampaign.login.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spykke.internal.adcampaign.login.entity.LoginUser;

public interface LoginUserRepository extends JpaRepository<LoginUser, Long> {
    LoginUser findByUsername(String username);
}
