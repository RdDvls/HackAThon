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
    @Before
    public void setUp() throws Exception {
//        User userToAdd = new User("Joe", "Fabiano", "test@test.com", "abc123!");
//        users.save(userToAdd);
        //users.delete(userToAdd);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testAddUser() throws Exception {
        User userToAdd = new User("test@test.com", "Joe", "Fabiano", "abc123!", "techSkills");
        System.out.println("User first name = " + userToAdd.getFirstName() + "\nUser Last name = " + userToAdd.getLastName() + "\nUser password = " + userToAdd.getPassword() + "\nUser email = " + userToAdd.getEmail() + "\nUser techSkills = " + userToAdd.getTechSkills());
        users.save(userToAdd);
        myUser = users.findFirstByEmail("test@test.com");
        System.out.println("User first name = " + myUser.getFirstName() + "\nUser Last name = " + myUser.getLastName() + "\nUser password = " + myUser.getPassword() + "\nUser email = " + myUser.getEmail() + "\nUser techSkills = " + myUser.getTechSkills());
        assertEquals("Joe", myUser.getFirstName());
        assertEquals("Fabiano", myUser.getLastName());
        assertEquals("test@test.com", myUser.getEmail());
        assertEquals("abc123!", myUser.getPassword());
        assertEquals("techSkills", myUser.getTechSkills());

        users.delete(myUser);
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