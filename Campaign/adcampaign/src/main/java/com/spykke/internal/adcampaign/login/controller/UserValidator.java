package com.spykke.internal.adcampaign.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.spykke.internal.adcampaign.login.entity.LoginUser;
import com.spykke.internal.adcampaign.login.service.LoginUserService;

@Component
public class UserValidator implements Validator {
    @Autowired
    private LoginUserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return LoginUser.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        LoginUser user = (LoginUser) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty");
        if (user.getUsername().length() < 2 || user.getUsername().length() > 32) {
            errors.rejectValue("username", "Size.userForm.username");
        }
        if (userService.findByUsername(user.getUsername()) != null) {
            errors.rejectValue("username", "Duplicate.userForm.username");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
        if (user.getPassword().length() < 5 || user.getPassword().length() > 32) {
            errors.rejectValue("password", "Size.userForm.password");
        }

        if (!user.getPasswordConfirm().equals(user.getPassword())) {
            errors.rejectValue("passwordConfirm", "Diff.userForm.passwordConfirm");
        }
    }
}
