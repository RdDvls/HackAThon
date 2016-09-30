package com.tiy.hack;

import javax.persistence.*;

/**
 * Created by RdDvls on 9/30/16.
 */
@Entity
@Table(name = "events")
public class EventItem {
    @Id
    @GeneratedValue
    int id;
    @ManyToOne
    User user;
    @Column(nullable = false)
    String eventName;
    @Column(nullable = false)
    String description;
//    @Column(nullable = false)
//    String location;
//    @Column(nullable = false)
//    long time;
    @Column(nullable = false)
    boolean isDone;
//    @Column(nullable = false)
//    long date;

    public EventItem(int id, User user, String eventName, String description, String location, long time, boolean isDone, long date) {
        this.id = id;
        this.user = user;
        this.eventName = eventName;
        this.description = description;
        this.isDone = isDone;
    }



    public int getId() {

        return id;
    }

    public EventItem() {
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

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }
}
// users_events_attended table    -- one-to-many relationship with users      --one to many with events table
// user-contacts