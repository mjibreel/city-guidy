package com.example.cityguidy.HelperClasses;

import com.google.firebase.database.ServerValue;

import java.util.Map;

public class CommentHelperClass {

    private String postKey;
    private  String commentContact;
//    private  String uid;
    private  String uName;
    private  String uImg;
    private  String rateText;

    public CommentHelperClass() {

    }

    public CommentHelperClass(String commentContact, String uName, String uImg,String rateText) {
        this.commentContact = commentContact;
//        this.uid = uid;
        this.uName = uName;
        this.uImg = uImg;
        this.rateText = rateText;
    }

    public String getCommentContact() {
        return commentContact;
    }

    public void setCommentContact(String commentContact) {
        this.commentContact = commentContact;
    }

//    public String getUid() {
//        return uid;
//    }
//
//    public void setUid(String uid) {
//        this.uid = uid;
//    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public String getuImg() {
        return uImg;
    }

    public void setuImg(String uImg) {
        this.uImg = uImg;
    }

    public String getRateText() {
        return rateText;
    }

    public void setRateText(String rateText) {
        this.rateText = rateText;
    }

        public String getPostKey() {
        return postKey;
    }

    public void setPostKey(String postKey) {
        this.postKey = postKey;
    }
}
