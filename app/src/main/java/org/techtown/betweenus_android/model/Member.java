package org.techtown.betweenus_android.model;

import com.google.gson.annotations.SerializedName;

public class Member {

    private String id;
    private String name;
    private String school;
    private String profileImg;
    @SerializedName("phone_number")
    private String phoneNumber;
    private Integer studentidx;
    private Integer grade;
    @SerializedName("class")
    private Integer schoolClass;

    public Member() {

    }

    public Member(String id, String name, String school, String profileImg, String phoneNumber, Integer studentidx, Integer grade, Integer schoolClass) {
        this.id = id;
        this.name = name;
        this.school = school;
        this.profileImg = profileImg;
        this.phoneNumber = phoneNumber;
        this.studentidx = studentidx;
        this.grade = grade;
        this.schoolClass = schoolClass;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getprofileImg() {
        return profileImg;
    }

    public void setprofileImg(String profileImg) {
        this.profileImg = profileImg;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getStudentidx() {
        return studentidx;
    }

    public void setStudentidx(Integer studentidx) {
        this.studentidx = studentidx;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Integer getSchoolClass() {
        return schoolClass;
    }

    public void setSchoolClass(Integer schoolClass) {
        this.schoolClass = schoolClass;
    }
}
