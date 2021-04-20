package com.spykke.internal.adcampaign.login.service;

import com.spykke.internal.adcampaign.login.entity.LoginUser;

public interface LoginUserService {
    void save(LoginUser user);

    LoginUser findByUsername(String username);
}
