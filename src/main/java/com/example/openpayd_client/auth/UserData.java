package com.example.openpayd_client.auth;

import com.example.openpayd_client.model.UserModel;
import org.springframework.security.core.userdetails.User;

public class UserData extends User {

    public UserData(UserModel user) {
        super(user.getId().toString(), user.getPassword(), user.getGrantedAuthorities());
    }
}
