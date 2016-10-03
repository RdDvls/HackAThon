package com.tiy.hack;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.*;

/**
 * Created by jfabiano on 10/1/2016.
 */
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.servlet.http.HttpSession;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest
//@RestController
public class UserRepositoryTest {
    @Autowired
    UserRepository users;

    @Autowired
    EventRepository events;
    User myUser;
    User userToAdd;
    EventItem myEvent;
    @Before
    public void setUp() throws Exception {
//        User userToAdd = new User("Joe", "Fabiano", "test@test.com", "abc123!");
//        users.save(userToAdd);
        //users.delete(userToAdd);
    }

    @After
    public void tearDown() throws Exception {

    }
    //Add user test method which adds a user to DB, and then deletes the user
    @Test
    public void testAddUser() throws Exception {
        User userToAdd = new User("test@test.com", "abc123!", "Joe", "Fabiano");
        System.out.println("User first name = " + userToAdd.getFirstName() + "\nUser Last name = " + userToAdd.getLastName() + "\nUser password = " + userToAdd.getPassword() + "\nUser email = " + userToAdd.getEmail());
        users.save(userToAdd);
        User myUser = users.findByEmail("test@test.com");
        System.out.println("User first name = " + myUser.getFirstName() + "\nUser Last name = " + myUser.getLastName() + "\nUser password = " + myUser.getPassword() + "\nUser email = " + myUser.getEmail());
        assertEquals("Joe", myUser.getFirstName());
        assertEquals("Fabiano", myUser.getLastName());
        assertEquals("test@test.com", myUser.getEmail());
        assertEquals("abc123!", myUser.getPassword());
        //assertEquals("techSkills", myUser.getTechSkills());

        testDeleteUser(myUser);
    }
    //Add user test method for other test methods to call which returns the user object
    @Test
    public User testReturnAddUser() throws Exception {
        User userToAdd = new User("test@test.com", "abc123!", "Joe", "Fabiano");
        System.out.println("User first name = " + userToAdd.getFirstName() + "\nUser Last name = " + userToAdd.getLastName() + "\nUser password = " + userToAdd.getPassword() + "\nUser email = " + userToAdd.getEmail());
        users.save(userToAdd);
        User myUser = users.findByEmail("test@test.com");
        System.out.println("User first name = " + myUser.getFirstName() + "\nUser Last name = " + myUser.getLastName() + "\nUser password = " + myUser.getPassword() + "\nUser email = " + myUser.getEmail());
        assertEquals("Joe", myUser.getFirstName());
        assertEquals("Fabiano", myUser.getLastName());
        assertEquals("test@test.com", myUser.getEmail());
        assertEquals("abc123!", myUser.getPassword());
        //assertEquals("techSkills", myUser.getTechSkills());

        return myUser;
    }
    //A test login method which adds a user to DB, and then searches the DB by Email for the entered user. It then creates a login container,
    //and tests that the user has the expected attributes. The ID value is tested to ensure it is not null. The user is then deleted.
    @Test
    public void testLogin() throws Exception {
        User userToAdd = new User("test@test.com", "abc123!", "Joe", "Fabiano");
        System.out.println("User first name = " + userToAdd.getFirstName() + "\nUser Last name = " + userToAdd.getLastName() + "\nUser password = " + userToAdd.getPassword() + "\nUser email = " + userToAdd.getEmail());
        users.save(userToAdd);
        User myUser = users.findByEmail("test@test.com");
        System.out.println("User first name = " + myUser.getFirstName() + "\nUser Last name = " + myUser.getLastName() + "\nUser password = " + myUser.getPassword() + "\nUser email = " + myUser.getEmail());
//        assertEquals("Joe", myUser.getFirstName());
//        assertEquals("Fabiano", myUser.getLastName());
//        assertEquals("test@test.com", myUser.getEmail());
//        assertEquals("abc123!", myUser.getPassword());
        //assertEquals("techSkills", myUser.getTechSkills());
        LoginContainer myLoginContainer = new LoginContainer();
                myLoginContainer.setErrorMessage("Test Error Message");
                myLoginContainer.setUser(myUser);
        assertEquals("Joe", myUser.getFirstName());
        assertEquals("Fabiano", myUser.getLastName());
        assertEquals("test@test.com", myUser.getEmail());
        assertEquals("abc123!", myUser.getPassword());
        assertNotNull(myUser.getId());
        System.out.println("user ID = " + myUser.getId());
        testDeleteUser(myUser);
    }
    //This addEvent method test looks like it needs to be changed as the find event by ID is searching based on the eventItem when it should be searching based on the id
    @Test
    public void testAddEvent() throws Exception {
        User myUser = testReturnAddUser();
        users.save(myUser);
        EventItem eventToAdd = new EventItem(myUser, "test event name", "test event description", "test event location");
        events.save(eventToAdd);
        //EventItem eventFromDB = events.findById(eventToAdd);//This needs to be changed to return a eventItem to test this properly
//        assertNotNull(eventFromDB);
//        assertEquals("test event name", eventFromDB.getEventName());
//        assertEquals("test event description", eventFromDB.getDescription());
//        assertEquals("test event location", eventFromDB.getLocation());
        testDeleteEvent(eventToAdd);
        testDeleteUser(myUser);
    }
    @Test
    public void testDeleteUser(User myUser) throws Exception {
        users.delete(myUser);
    }
    @Test
    public void testDeleteEvent(EventItem myEvent) throws Exception {
        events.delete(myEvent);
    }
//    @RequestMapping(path = "/newUser.json", method = RequestMethod.GET)
////    public User register(HttpSession session,String email, String firstName, String lastName, String password) {
//    public User testRegister(HttpSession session, @RequestBody User myUser) throws Exception {
//        myUser = users.findFirstByEmail(myUser.email);
//        if (myUser == null) {
//            myUser = new User("Joe", "Fabiano", "test@test.com", "abc123!");
//            users.save(myUser);
//        }
//        session.setAttribute("user", myUser);
//        return myUser;
//    }

}