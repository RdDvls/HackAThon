package com.tiy.hack;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by RdDvls on 9/30/16.
 */
@RestController
public class JSONRestController {
    @Autowired
    UserEventRepository userEvents;

    @Autowired
    UserRepository users;

    @Autowired
    FriendRepository friends;

    @Autowired
    EventRepository events;





    //From Austin: container holding String email and String password
    @RequestMapping(path = "/login.json", method = RequestMethod.POST)
    public LoginContainer login(@RequestBody User user /*String email, String password*/) throws Exception {

        LoginContainer myLoginContainer = new LoginContainer();

        if (user.email == null) {
            throw new Exception("The information entered was incorrect");
        } else if (user.email != null) {
            User thisUser = users.findByEmail(user.email);
            if (thisUser == null) {
                myLoginContainer.setErrorMessage("User does not have account");
                myLoginContainer.setUser(null);
            } else {
                if (!user.password.equals(thisUser.password)) {
                    myLoginContainer.setErrorMessage("Password does not match");
                    myLoginContainer.setUser(null);
                } else {
                    myLoginContainer.setErrorMessage(null);
                    myLoginContainer.setUser(thisUser);
                    System.out.println("New login from: " + thisUser.getFirstName() + " " + thisUser.getLastName());
                }
            }
        }
        return myLoginContainer; //make a new container class to handle this
    }

    @RequestMapping(path = "/logout", method = RequestMethod.POST)
    public void logout(HttpSession session) {
        session.invalidate();

    }

    //From Austin: container holding user info: String email, String password, String firstName, String lastName
    @RequestMapping(path = "/newUser.json", method = RequestMethod.POST)
    //Problem -> can't call @RequestBody on multiple things! Just a single java object.
    public LoginContainer register(@RequestBody User newUser) {
        users.save(newUser);

        User retrievedUser = users.findOne(newUser.getId());
        LoginContainer myLoginContainer;
        if (retrievedUser == null) {
            myLoginContainer = new LoginContainer("Could not create user", null);
        } else {
            myLoginContainer = new LoginContainer(null, retrievedUser);
        }

        return myLoginContainer;
    }

    @RequestMapping(path = "/viewUsers.json", method = RequestMethod.GET)
    //Problem -> can't call @RequestBody on multiple things! Just a single java object.
    public List<User> getUsers() {

        List<User> userList = new ArrayList<>();
        Iterable <User> allUsers = users.findAll();
        for (User user : allUsers) {
            userList.add(user);
        }
        return userList;
    }


    //From Austin: container holding event info: String name, String description,String location(will have an empty list of attendees when first created)
    @RequestMapping(path = "/addEvent.json", method = RequestMethod.POST)
    public List<EventItem> addEvent(@RequestBody EventItem event) throws Exception {
        events.save(event);
        List<EventItem> eventList = new ArrayList<EventItem>();
        Iterable<EventItem> allEvents = events.findAll();
        for (EventItem currentEvent : allEvents) {
            setListOfAttendees(currentEvent);
            eventList.add(currentEvent);
        }
        return eventList;
    }
    public ArrayList<User> setListOfAttendees(EventItem event) {
        //I need to query the userevents for ones that have this eventid
        Iterable<UserEvent> allUserEventsForThisEvent = userEvents.findAllByEventID(event.getId());
        ArrayList<User> allAttendees = new ArrayList<>();
        for (UserEvent currentUserEvent : allUserEventsForThisEvent) {
            //get the user for currentUserEvent
            User currentUser = users.findOne(currentUserEvent.getUser().getId());
            //add them to the list of people attending the event
            allAttendees.add(currentUser);
        }
        return allAttendees;
    }

//    @RequestMapping(path = "/createEvent.json", method = RequestMethod.POST)
//    public ArrayList<EventItem> createNewEvent(HttpSession session, @RequestBody EventItem thisEventItem) throws Exception{
//        User myUser = (User) session.getAttribute("user");
//
//        thisEventItem = new EventItem(thisEventItem.eventName, thisEventItem.location,  thisEventItem.description, thisEventItem.isDone = false);
//        thisEventItem.user = myUser;
//
//
//        System.out.println("My runtime repo: " + thisEventItem.toString());
//        events.save(thisEventItem);
//
////        return getMyEvents();
//        return getAllEvents();
//    }



//    From Austin: Just int eventId
    @RequestMapping(path = "/getSingleEvent.json", method = RequestMethod.POST)
    public EventItem singleEventView(@RequestBody int eventID) {
        EventItem event = events.findOne(eventID);
        System.out.println("Now returning event " + event.getEventName());
        return event;
    }

    //From Austin: container holding int userId and int eventId
    @RequestMapping(path = "/joinEvent.json", method = RequestMethod.POST)
    public ArrayList<User> joinEvent(@RequestBody UserIDEventIDConnectionContainer idContainer) {
        //use userId to go get userJoiningEvent
        User userJoiningEvent = users.findOne(idContainer.getUserId());
        //use eventId to go get eventBeingJoined
        EventItem eventBeingJoined = events.findOne(idContainer.getEventId());

        //Save user and event connection to userEvents table in db
        UserEvent userEvent = new UserEvent(userJoiningEvent, eventBeingJoined);
        userEvents.save(userEvent);

        //add userJoiningEvent to eventBeingJoined's list of attendees
        //it says attendees is null??
//        eventBeingJoined.addToAttendees(userJoiningEvent);
        //Instead, try finding all attendees by userEvents table
        ArrayList<User> thisEventsAttendees = new ArrayList<>();
        //If it doesn't work, might need to pass in the event instead of the event id.
        Iterable<UserEvent> allUserEventsLinkedToThisEvent = userEvents.findAllByEventID(idContainer.getEventId());
        for (UserEvent currentUserEvent : allUserEventsLinkedToThisEvent) {
            thisEventsAttendees.add(currentUserEvent.getUser());
        }

        System.out.println("Adding user " + userJoiningEvent.getFirstName() + " " + userJoiningEvent.getLastName() + " to event " + eventBeingJoined.getEventName());
        return thisEventsAttendees;
    }



    //Just for local testing purposes
    @RequestMapping(path = "/seeUserEvents.json", method = RequestMethod.GET)
    public ArrayList<UserEvent> seeUserEvents() {
        ArrayList<UserEvent> listOfUserEvents = new ArrayList<>();
        Iterable<UserEvent> allUserEvents = userEvents.findAll();
        for (UserEvent currentUserEvent : allUserEvents) {
            listOfUserEvents.add(currentUserEvent);
        }
        return listOfUserEvents;
    }

    //From Austin: container (FriendConnectionContainer object) holding userId and friendID
    // PROBLEM: RIGHT NOW THIS IS NOT ASKING THE USER TO GRANT PERMISSION. WILL NEED 2 ENDPOINTS I THINK.
    // This one is just adding the friend connection in friend database and returning the user's friends, so
    // it's assuming that permission was already granted.
    @RequestMapping(path = "/requestContact.json", method = RequestMethod.POST)
    public ArrayList<Friend> requestContact(@RequestBody FriendConnectionContainer friendConnectionContainer) throws Exception {
        //Find USER in users based on userID -- just to make sure valid
        User user = users.findOne(friendConnectionContainer.getUserId());
//        User user = friends.findOne(friendConnectionContainer.getUserId());

        //Find FRIEND in users based on userID -- just to make sure valid
        User friend = users.findOne(friendConnectionContainer.getFriendId());
        if (user == null) {
            throw new Exception("Requested user is not in database");
        } else if (friend == null) {
            throw new Exception("Requested friend is not in database");
        } else {
            //Make a new Friend object with userId from db and friendId from db
            Friend myFriend = new Friend(user,friend.getId());
            //save to friends table
            friends.save(myFriend);
            //return the user's list of friends by querying table
            Iterable<Friend> allMyFriends = friends.findAllByUserID(user.getId());
            ArrayList<Friend> listOfMyFriends = new ArrayList<>();
            for (Friend currentFriend : allMyFriends) {
                listOfMyFriends.add(currentFriend);
            }
            return listOfMyFriends;
        }
    }

    //New idea: Make a viewUserInfo method that checks if the currentuser is on the friends list of the person they want to see the
    //info of. If currentuser on list, return contact info of friend (return the friend's whole user object).
    // If currentuser not on list, return error message.
    //On Austins's side, he will display the email if he gets the user back, and display the button to request contact info if
    //he gets the error message back.
    //From Austin: container holding int userId (current user, requester), int friendId (person currentUser wants to email, requestee)
    @RequestMapping(path = "/viewUserInfo.json", method = RequestMethod.POST)
    public LoginContainer viewUserInfo(@RequestBody FriendConnectionContainer friendConnectionContainer) {
        //go through friends table and find
        // current user is seeing if they are on friend list of friend
        User requesterUser = users.findOne(friendConnectionContainer.userId);
        User requesteeFriend = users.findOne(friendConnectionContainer.friendId);

        LoginContainer myContainer = new LoginContainer();
        boolean noAccess = true;

        Iterable<Friend> requesteesFriendList = friends.findAllByUserID(friendConnectionContainer.friendId);
        for (Friend friend : requesteesFriendList) {
            if (friendConnectionContainer.userId == friend.getId()) {
                myContainer.user = users.findOne(friendConnectionContainer.friendId);
                myContainer.errorMessage = null;
                noAccess = false;
            }
        }

        if (noAccess) {
            myContainer.user = null;
            myContainer.errorMessage = "You do not have permission to view this person's contact info.";
        }

        return myContainer;
    }




}



