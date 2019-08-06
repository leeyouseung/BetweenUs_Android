package org.techtown.betweenus_android.manager;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.google.gson.annotations.SerializedName;

import org.techtown.betweenus_android.model.Member;

public class CurrentUser extends SQLiteOpenHelper {

    private Context context;

    public CurrentUser(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);

        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE User (token TEXT PRIMARY KEY, studentidx Integer, name TEXT, school TEXT, profileimg TEXT, phoneNumber TEXT, id TEXT, grade Integer, schoolClass Integer);");
        db.execSQL("CREATE TABLE QR (idx Integer PRIMARY KEY, url TEXT, currentUser TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insert(Member member) {
        SQLiteDatabase db = getWritableDatabase();

        db.execSQL("INSERT INTO User VALUES('" + new Token(context).getToken() + "'  ,  " + member.getStudentidx() + "  ,  '" + member.getName() + "'  ,  '" + member.getSchool() + "'" +
                "  ,  '" + member.getProfileimg() + "'  ,  '" + member.getPhoneNumber() + "'  ,  '" + member.getId() + "'" +
                "  ,  " + member.getGrade() + "  ,  '" + member.getSchoolClass() + "');");
        db.close();
    }

    public void delete() {
        SQLiteDatabase db = getWritableDatabase();

        db.execSQL("DELETE FROM QR WHERE token='" + new Token(context).getToken() + "';");
        db.close();
    }

    public Member getResult() {

        SQLiteDatabase db = getReadableDatabase();

        Integer studentidx  = null;
        String name = null;
        String school = null;
        String profileimg = null;
        String phoneNumber = null;
        String id = null;
        Integer grade = null;
        Integer schoolClass = null;

        Member member = new Member();

        Cursor cursor = db.rawQuery("SELECT * FROM User WHERE token='"+new Token(context).getToken()+"'", null);
        while (cursor.moveToNext()) {
            studentidx = cursor.getInt(cursor.getColumnIndex("studentidx"));
            name = cursor.getString(cursor.getColumnIndex("name"));
            school = cursor.getString(cursor.getColumnIndex("school"));
            profileimg = cursor.getString(cursor.getColumnIndex("profileimg"));
            phoneNumber = cursor.getString(cursor.getColumnIndex("phoneNumber"));
            id = cursor.getString(cursor.getColumnIndex("id"));
            grade = cursor.getInt(cursor.getColumnIndex("grade"));
            schoolClass = cursor.getInt(cursor.getColumnIndex("schoolClass"));

            member = new Member(id, name, school, profileimg, phoneNumber, studentidx, grade, schoolClass);
        }

        return member;
    }
}

