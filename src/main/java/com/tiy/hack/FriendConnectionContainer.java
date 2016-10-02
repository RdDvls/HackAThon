package com.tiy.hack;

/**
 * Created by RdDvls on 10/2/16.
 */
public class FriendConnectionContainer {
    int userID;
    int friendID;

    public FriendConnectionContainer() {
    }

    public FriendConnectionContainer(int userID, int friendId) {
        this.userID = userID;
        this.friendID = friendId;
    }

    //Getters and setters
    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getFriendID() {
        return friendID;
    }

    public void setFriendID(int friendID) {
        this.friendID = friendID;
    }
}

