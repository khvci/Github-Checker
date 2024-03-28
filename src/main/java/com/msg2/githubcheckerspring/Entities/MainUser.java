package com.msg2.githubcheckerspring.Entities;

public class MainUser {
    private String userName;
    private int followersPageNumber;
    private int followingPageNumber;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getFollowersPageNumber() {
        return followersPageNumber;
    }

    public void setFollowersPageNumber(int followersPageNumber) {
        this.followersPageNumber = followersPageNumber;
    }

    public int getFollowingPageNumber() {
        return followingPageNumber;
    }

    public void setFollowingPageNumber(int followingPageNumber) {
        this.followingPageNumber = followingPageNumber;
    }
}
