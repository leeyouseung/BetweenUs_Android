package org.techtown.betweenus_android.model;

public class Login {

    private String token;

    private String refreshToken;

    private Member info;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public Member getInfo() {
        return info;
    }

    public void setInfo(Member info) {
        this.info = info;
    }
}
