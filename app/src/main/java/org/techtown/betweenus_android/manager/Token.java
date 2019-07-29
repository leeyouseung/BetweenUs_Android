package org.techtown.betweenus_android.manager;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.SharedPreferences;

/**
 *  @author 우주 최강 천재 건우
 *  토큰 sharedpreferences에 저장
 */
public class Token extends ContextWrapper {

    public Token(Context context) {
        super(context);
    }

    private String token;

    public void setToken(String token) {

        SharedPreferences sharedPreferences = getSharedPreferences("betweenus",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("token", token);

        editor.commit();
    }

    public String getToken() {

        SharedPreferences sharedPreferences = getSharedPreferences("betweenus",MODE_PRIVATE);

        token = sharedPreferences.getString("token","");

        return token;

    }
}
