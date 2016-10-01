package com.tiy.hack;


import javax.persistence.*;

/**
 * Created by RdDvls on 10/1/16.
 */
@Entity
@Table(name = "attending")
public class AttendingEvents {

    @Id
    @GeneratedValue
    int id;

    @ManyToOne
    EventItem event;

    @ManyToOne
    User user;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public EventItem getEvent() {
        return event;
    }

    public void setEvent(EventItem event) {
        this.event = event;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public AttendingEvents(EventItem event, User user) {
        this.event = event;
        this.user = user;
    }

    public AttendingEvents () {

    }
}


