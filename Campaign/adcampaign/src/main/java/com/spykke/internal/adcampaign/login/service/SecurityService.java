package com.spykke.internal.adcampaign.login.service;

public interface SecurityService {
    boolean isAuthenticated();
    void autoLogin(String username, String password);
}
