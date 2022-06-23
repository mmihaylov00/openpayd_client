package com.example.openpayd_client.dto.external;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Calendar;

public class OpenpaydLoginResponseDTO {
    @JsonProperty("access_token")
    private String accessToken;
    @JsonProperty("expiresIn")
    private int expiresIn;

    private final long loginTime = now();

    public OpenpaydLoginResponseDTO() {
    }

    public boolean isExpired() {
        return loginTime + expiresIn * 1000L <= now() + 5000;
    }

    public String getToken() {
        return "Bearer " + accessToken;
    }

    private long now() {
        return Calendar.getInstance().getTimeInMillis();
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public int getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
    }

    public long getLoginTime() {
        return loginTime;
    }
}
