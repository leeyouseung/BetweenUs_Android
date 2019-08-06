package org.techtown.betweenus_android.manager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class QR extends SQLiteOpenHelper {

    CurrentUser user;

    public QR(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);

        user = new CurrentUser(context,name,factory,version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE QR (idx INTEGER PRIMARY KEY, url TEXT, currentUser TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insert(String url) {
        SQLiteDatabase db = getWritableDatabase();

        Integer idx = Integer.parseInt(url.split("/")[3]);
        db.execSQL("INSERT INTO QR VALUES(" + idx + "  ,  '" + url + "'  ,  '" +  user.getResult().getId() + "');");
        db.close();
    }

    public void delete(String url) {
        SQLiteDatabase db = getWritableDatabase();

        db.execSQL("DELETE FROM QR WHERE url='" + url + "';");
        db.close();
    }

    public String getResult(Integer idx) {

        SQLiteDatabase db = getReadableDatabase();
        String result = "";

        Cursor cursor = db.rawQuery("SELECT * FROM QR WHERE idx="+idx+" AND currentUser='"+user.getResult().getId()+"'", null);
        while (cursor.moveToNext()) {
            result = cursor.getString(cursor.getColumnIndex("url"));
        }

        return result;
    }
}

