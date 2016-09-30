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

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public String login(HttpSession session, String email, String password) throws Exception {
        User myUser = users.findFirstByEmail(email);
        if(myUser == null){
            throw new Exception("User does not exist or was input incorrectly");
        } else if (!password.equals(myUser.getPassword())){
            throw new Exception("Incorrect Password");
        }
        session.setAttribute("user", myUser);
        return "user";
    }

    @RequestMapping(path = "/logout", method = RequestMethod.POST)
    public String logout(HttpSession session) {
        session.invalidate();
        
    }

    @RequestMapping(path = "/newUser", method = RequestMethod.POST)
    public String register(HttpSession session, String email, String firstName, String lastName, String password) {
        User myUser = users.findFirstByEmail(email);
        if(myUser == null){
            myUser = new User(email, firstName, lastName, password);
            users.save(myUser);
        }
        session.setAttribute("user", myUser);
//        return "redirect:/home";
        return "user";
    }

    @RequestMapping(path = "/events", method = RequestMethod.POST)
    public ArrayList<EventItem> allEvents() {

    }

    @RequestMapping(path = "/eventInfo", method = RequestMethod.POST)
    public EventItem eventInfo(int eventID) {

    }

    @RequestMapping(path = "/createEvent", method = RequestMethod.POST)
    public EventItem addEvent(HttpSession session, @RequestBody EventItem event) {

    }

    @RequestMapping(path = "/userContacts", method = RequestMethod.POST)
    public ArrayList<User> contacts(int userID) {

    }

    @RequestMapping(path = "/contactInfo", method = RequestMethod.POST)
    public User contactInfo(int userID) {

    }

}
