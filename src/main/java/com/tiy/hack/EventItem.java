package com.tiy.hack;

import javax.persistence.*;
import java.util.ArrayList;

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

    @Column(nullable = false)
    String location;

    public EventItem(String eventName, String description, String location, ArrayList<User> attendees) {
        this.eventName = eventName;
        this.description = description;
        this.location = location;
        this.attendees = attendees;
    }

    @Column

    ArrayList<User> attendees = new ArrayList<>();


    public EventItem(String eventName, String description, String location) {
        this.eventName = eventName;
        this.description = description;
        this.location = location;
    }

//    public boolean isDone() {
//        return isDone;
//    }
//
//    public void setDone(boolean done) {
//        isDone = done;
////    }

    public EventItem(User user, String eventName, String description, String location) {
        this.user = user;
        this.eventName = eventName;
        this.description = description;
        this.location = location;
//        this.time = time;
//        this.isDone = isDone;
    }
    public void addToAttendees(User user) {
        //But I will need to resave whole event to db as well in json controller. (check)
        attendees.add(user);
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public ArrayList<User> getAttendees() {
        return attendees;
    }

    public void setAttendees(ArrayList<User> attendees) {
        this.attendees = attendees;
    }


//    public long getTime() {
//        return time;
//    }
//
//    public void setTime(long time) {
//        this.time = time;
//    }
}
