package org.techtown.betweenus_android.manager;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.SharedPreferences;

public class QR extends ContextWrapper {

    public QR(Context context) {
        super(context);
    }

    private String url;

    public void setUrl(String url) {

        SharedPreferences sharedPreferences = getSharedPreferences("betweenus",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("qr", url);

        editor.commit();
    }

    public String getUrl() {

        SharedPreferences sharedPreferences = getSharedPreferences("betweenus",MODE_PRIVATE);

        url = sharedPreferences.getString("qr","");

        return url;

    }

}
