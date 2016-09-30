package com.tiy.hack;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.servlet.http.HttpSession;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HackAThonApplicationTests {
	@Autowired
	UserRepository users;

	@Test
	public void contextLoads() {

	}
	@Test
	public void testInsertUser(){
		String email = "HC@aol.com";
		String firstName = "HC Clay";
		String lastName = "HC Strickland";
		String password = "pass";
		String techSkills = "Java???";
		User myUser = new User(email, firstName,lastName,password, techSkills);
		users.save(myUser);


	}

}
