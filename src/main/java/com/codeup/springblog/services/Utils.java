package com.codeup.springblog.services;

import com.codeup.springblog.models.User;
import org.springframework.security.core.context.SecurityContextHolder;

public class Utils {

    public static User loggedInUser(){
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
