package com.example.readingisgood.dto.token;

import java.util.Date;

public class TokenDto {

    private String token;
    private Date expire;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getExpire() {
        return expire;
    }

    public void setExpire(Date expire) {
        this.expire = expire;
    }
}
