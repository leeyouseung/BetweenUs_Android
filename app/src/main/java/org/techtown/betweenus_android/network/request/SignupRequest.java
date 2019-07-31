package org.techtown.betweenus_android.network.request;

import android.net.Uri;

public class SignupRequest {

    private String id;

    private String password;

    private String name;

    private String school;

    private Uri profileImg;

    private String phoneNumber;

    public SignupRequest(String id, String password, String name, String school, Uri profileImg, String phoneNumber) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.school = school;
        this.profileImg = profileImg;
        this.phoneNumber = phoneNumber;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public Uri getProfileImg() {
        return profileImg;
    }

    public void setProfileImg(Uri profileImg) {
        this.profileImg = profileImg;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
