package com.example.ws_session1.models;

import java.io.Serializable;

public class LoginResponse implements Serializable {
    private String id;
    private String email;
    private String nickName;
    private String avatar;
    private String token;

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getNickName() {
        return nickName;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getToken() {
        return token;
    }
}
