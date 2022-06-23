package com.example.openpayd_client.dto.external;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OpenpaydLoginRequestDTO {
    @JsonProperty("access_token")
    private String accessToken;
    @JsonProperty("expiresIn")
    private int expiresIn;

    public OpenpaydLoginRequestDTO() {
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
}
