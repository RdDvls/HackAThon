package com.tiy.hack;

import javax.persistence.*;

/**
 * Created by RdDvls on 10/2/16.
 */
@Entity
@Table(name = "friends")
public class Friend {
    @Id
    @GeneratedValue
    int id;

    @ManyToOne
    User user;

    @ManyToOne
    User friend;

    public Friend() {
    }

    public Friend(User user) {
        this.user = user;
//        this.friend = this;
    }

    //Getters and setters
    public int getID() {
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

    public User getFriend() {
        return friend;
    }

    public void setFriend(User friend) {
        this.friend = friend;
    }
}
