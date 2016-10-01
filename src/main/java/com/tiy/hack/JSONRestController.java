package com.tiy.hack;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

/**
 * Created by RdDvls on 9/30/16.
 */
@RestController
public class JSONRestController {
    @Autowired
    UserRepository users;
    @Autowired
    EventRepository events;
    User myUser;

    @RequestMapping(path = "/testjson.json", method = RequestMethod.GET)
    public User testJson(HttpSession session, String firstName) {
        User myUser = new User();
        return myUser;
    }

    @RequestMapping(path = "/newUser.json", method = RequestMethod.GET)
//    public User register(HttpSession session,String email, String firstName, String lastName, String password) {
    public User testRegister(HttpSession session, @RequestBody User myUser) throws Exception {
        myUser = users.findFirstByEmail(myUser.email);
        if (myUser == null) {
            myUser = new User("Joe", "Fabiano", "test@test.com", "abc123!");
            users.save(myUser);
        }
        session.setAttribute("user", myUser);
        return myUser;
    }

    @RequestMapping(path = "/login.json", method = RequestMethod.POST)
    public User login(HttpSession session, String email, String password) throws Exception {
        User myUser = users.findFirstByEmail(email);
        if (myUser == null) {
            throw new Exception("User does not exist or was input incorrectly");
        } else if (!password.equals(myUser.getPassword())) {
            throw new Exception("Incorrect Password");
        }
        session.setAttribute("user", myUser);
        return myUser;
    }

    @RequestMapping(path = "/logout", method = RequestMethod.POST)
    public void logout(HttpSession session) {
        session.invalidate();

    }

    @RequestMapping(path = "/newUser.json", method = RequestMethod.POST)
//    public User register(HttpSession session,String email, String firstName, String lastName, String password) {
    public User register(HttpSession session, @RequestBody User myUser) throws Exception {
        myUser = users.findFirstByEmail(myUser.email);
        if (myUser == null) {
            myUser = new User(myUser.firstName, myUser.lastName, myUser.email, myUser.password);
            users.save(myUser);
        }
        session.setAttribute("user", myUser);
        return myUser;
    }

    @RequestMapping(path = "/createEvent.json", method = RequestMethod.POST)
    public ArrayList<EventItem> newEvent(HttpSession session, @RequestBody EventItem thisEvent) throws Exception{
        User myUser = (User) session.getAttribute("user");

        thisEvent = new EventItem(thisEvent.eventName, thisEvent.location,  thisEvent.description, thisEvent.isDone = false);
        thisEvent.user = myUser;


        System.out.println("My runtime repo: " + thisEvent.toString());
        events.save(thisEvent);

//        return getMyEvents();
        return getAllEvents();
    }

    @RequestMapping(path = "/deleteEvent.json", method = RequestMethod.POST)
    public ArrayList<EventItem> deleteEvent(HttpSession session) throws Exception{
        EventItem event = (EventItem) session.getAttribute("event");

        System.out.println("My runtime repo to delete event: " + event.toString());
        events.delete(event);

//        return getMyEvents();
        return getAllEvents();
    }
    @RequestMapping(path= "/profile.json", method = RequestMethod.POST)
    public User thisUsersProfile(HttpSession session) throws Exception {
         myUser = (User) session.getAttribute("user");

        return (myUser);
    }
    @RequestMapping(path= "/userInfo.json", method = RequestMethod.POST)
    public String clickedUser(HttpSession session, @RequestBody User user) throws Exception {
        User findUser = users.findOne(user.id);

        return (findUser.email);
    }



    @RequestMapping(path = "/allevents.json", method = RequestMethod.POST)
    public ArrayList<EventItem> allEvents(HttpSession session) throws Exception{
        return getAllEvents();

    }

    @RequestMapping(path = "/saveEvent.json", method = RequestMethod.POST)
    public ArrayList<EventItem> saveEvent(HttpSession session, @RequestBody EventItem event) throws Exception{
//        event = (Event) session.getAttribute("event");
//
//        event.name = name;
//        event.location = location;
//        event.dateAndTime = dateAndTime;
//        event.details = details;

        System.out.println("My runtime repo: " + event.toString());
        events.save(event);

//        return getMyEvents();
        return getAllEvents();
    }


//
//    @RequestMapping(path = "/eventInfo", method = RequestMethod.POST)
//    public EventItem eventInfo(int eventID) {
//
//    }
//

//
//    @RequestMapping(path = "/userContacts", method = RequestMethod.POST)
//    public ArrayList<User> contacts(int userID) {
//
//    }
//
//    @RequestMapping(path = "/contactInfo", method = RequestMethod.POST)
//    public User contactInfo(int userID) {
//
//    }
    ArrayList<EventItem> getMyEvents() {
        ArrayList<EventItem> eventList = new ArrayList<EventItem>();
        Iterable<EventItem> allEvents = events.findByUser(myUser);

        if (myUser != null){
            for (EventItem currentEvent : allEvents) {
                eventList.add(currentEvent);
            }
        }
        return eventList;
    }
    ArrayList<EventItem> getAllEvents() {
        ArrayList<EventItem> eventList = new ArrayList<EventItem>();
        Iterable<EventItem> allEvents = events.findAll();

        if (myUser != null){
            for (EventItem currentEvent : allEvents) {
                eventList.add(currentEvent);
            }
        }
        return eventList;
    }
}

