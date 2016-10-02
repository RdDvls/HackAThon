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
    EventItem eventItem;

    @ManyToOne
    User user;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public EventItem getEventItem() {
        return eventItem;
    }

    public void setEventItem(EventItem eventItem) {
        this.eventItem = eventItem;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public AttendingEvents(EventItem eventItem, User user) {
        this.eventItem = eventItem;
        this.user = user;
    }

    public AttendingEvents () {

    }
}


