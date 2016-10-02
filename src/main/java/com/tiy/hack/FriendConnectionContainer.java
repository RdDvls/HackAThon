package com.tiy.hack;

/**
 * Created by RdDvls on 10/2/16.
 */
public class FriendConnectionContainer {
    int userId;
    int userWhoWantsToBeFriendId;
    int friendId;

    public int getFriendId() {
        return friendId;
    }
//
    public void setFriendId(int friendId) {
        this.friendId = friendId;
    }

    public FriendConnectionContainer() {

    }

    public FriendConnectionContainer(int userId, int userWhoWantsToBeFriendId) {
        this.userId = userId;
        this.userWhoWantsToBeFriendId = userWhoWantsToBeFriendId;
    }

    //Getters and setters
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getUserWhoWantsToBeFriendId() {
        return userWhoWantsToBeFriendId;
    }

    public void setUserWhoWantsToBeFriendId(int userWhoWantsToBeFriendId) {
        this.userWhoWantsToBeFriendId = userWhoWantsToBeFriendId;
    }
}

