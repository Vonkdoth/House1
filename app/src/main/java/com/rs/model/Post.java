package com.rs.model;

/**
 * Created by richasaxena on 27/04/16.
 */
public class Post {
   /* "post_name": "post121113",
            "user_name": "ankit",
            "category_name": "category",
            "sub_category": "sub_category",
            "phone": 123456,
            "address": "asdasd",
            "added_on": "2016-04-14"*/
    String postName;
    String userName;
    String categoryName;
    String phone;
    String address;
    String date;

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
