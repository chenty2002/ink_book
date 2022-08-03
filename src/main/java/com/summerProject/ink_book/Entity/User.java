package com.summerProject.ink_book.Entity;

public class User {
    private Integer userId;
    private String userName;
    private String password;
    private String userRealName;
    private String userProfile;
    private String userEmail;

    public static User copyUser(User user) {
        User newUser = new User();
        newUser.setUserId(user.getUserId());
        newUser.setPassword(user.getPassword());
        newUser.setUserName(user.getUserName());
        newUser.setUserEmail(user.getUserEmail());
        newUser.setUserRealName(user.getUserRealName());
        newUser.setUserProfile(user.getUserProfile());
        return newUser;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", userRealName='" + userRealName + '\'' +
                ", userProfile='" + userProfile + '\'' +
                ", userEmail='" + userEmail + '\'' +
                '}';
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserRealName() {
        return userRealName;
    }

    public void setUserRealName(String userRealName) {
        this.userRealName = userRealName;
    }

    public String getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(String userProfile) {
        this.userProfile = userProfile;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}
