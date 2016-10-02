package com.tiy.hack;

import javax.persistence.*;

/**
 * Created by RdDvls on 10/2/16.
 */
@Entity
@Table(name = "friends")
public class Friend{
    @Id
    @GeneratedValue
    int id;

    @ManyToOne
    User user;

//    @ManyToOne
//    User friend;

    @Column(nullable = false)
    int friendId;

    public Friend() {
    }

    public Friend(User user, int userWhoWantsToBeFriendId) {
        this.user = user;
        this.friendId = userWhoWantsToBeFriendId;
    }

    //Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

//    public User getFriend() {
//        return friend;
//    }
//
//    public void setFriend(User friend) {
//        this.friend = friend;
//    }

    public int getFriendId() {
        return friendId;
    }

    public void setFriendId(int friendId) {
        this.friendId = friendId;
    }
}
    