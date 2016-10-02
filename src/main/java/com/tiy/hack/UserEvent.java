package com.tiy.hack;

import javax.persistence.*;

/**
 * Created by RdDvls on 10/2/16.
 */
@Entity
@Table(name = "user_events")
public class UserEvent {
    @Id
    @GeneratedValue
    int id;

    @ManyToOne
    User user;

    @ManyToOne
    EventItem event;

    public UserEvent() {
    }

    public UserEvent(User user, EventItem event) {
        this.user = user;
        this.event = event;
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

    public EventItem getEvent() {
        return event;
    }

    public void setEvent(EventItem event) {
        this.event = event;
    }
}