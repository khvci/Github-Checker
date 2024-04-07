package com.msg2.githubcheckerspring.Entities;

import java.util.ArrayList;
import java.util.List;

public class MainUser {
    private String userName;
    private int followersPageNumber;
    private int followingPageNumber;
    private List<String> followers = new ArrayList<>();
    private List<String> following = new ArrayList<>();

    //getters and setters
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

    public List<String> getFollowers() {
        return followers;
    }

    public void setFollowers(List<String> followers) {
        this.followers = followers;
    }

    public List<String> getFollowing() {
        return following;
    }

    public void setFollowing(List<String> following) {
        this.following = following;
    }

    public enum UserTypes {
        FOLLOWER,
        FOLLOWING
    }
}
