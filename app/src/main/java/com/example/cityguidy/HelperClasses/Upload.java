package com.example.cityguidy.HelperClasses;


import com.google.firebase.database.ServerValue;

public class Upload {

    private String postKey;
//    private String countPost;
    private String aName;
    private String aDetail;
    private String aLocation;
    private String aPhone;
    private String aCity;
    private String aCategory;
    private String mImageUri;
//    private String userId;
    private String userPhoto;
    private String userName;
    private Object timestamp;



    public Upload() {
    }

    public Upload(String aName, String aDetail, String aPhone, String aCity, String aCategory,
                  String mImageUri, String userPhoto, String userName ,String aLocation) {
        this.aName = aName;
        this.aDetail = aDetail;
        this.aPhone = aPhone;
        this.aCity = aCity;
        this.aCategory = aCategory;
        this.mImageUri = mImageUri;
//        this.userId = userId;
        this.userPhoto = userPhoto;
        this.userName = userName;
        this.aLocation = aLocation;
        this.timestamp = ServerValue.TIMESTAMP;
    }

    public String getaName() {
        return aName;
    }

    public void setaName(String aName) {
        this.aName = aName;
    }

    public String getaDetail() {
        return aDetail;
    }

    public void setaDetail(String aDetail) {
        this.aDetail = aDetail;
    }

    public String getaPhone() {
        return aPhone;
    }

    public void setaPhone(String aPhone) {
        this.aPhone = aPhone;
    }

    public String getaCity() {
        return aCity;
    }

    public void setaCity(String aCity) {
        this.aCity = aCity;
    }

    public String getaCategory() {
        return aCategory;
    }

    public void setaCategory(String aCategory) {
        this.aCategory = aCategory;
    }

    public String getmImageUri() {
        return mImageUri;
    }

    public void setmImageUri(String mImageUri) {
        this.mImageUri = mImageUri;
    }

//    public String getUserId() {
//        return userId;
//    }
//
//    public void setUserId(String userId) {
//        this.userId = userId;
//    }

    public String getUserPhoto() {
        return userPhoto;
    }

    public void setUserPhoto(String userPhoto) {
        this.userPhoto = userPhoto;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPostKey() {
        return postKey;
    }

    public void setPostKey(String postKey) {
        this.postKey = postKey;
    }

    public Object getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Object timestamp) {
        this.timestamp = timestamp;
    }

    public String getaLocation() {
        return aLocation;
    }

    public void setaLocation(String aLocation) {
        this.aLocation = aLocation;
    }
}

