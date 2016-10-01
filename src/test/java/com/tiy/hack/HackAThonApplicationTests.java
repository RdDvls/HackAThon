package com.tiy.hack;

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
public class HackAThonApplicationTests {
	@Autowired
	UserRepository users;

	@Test
	public void contextLoads() {

	}
	@Test
	public void testCreateUser() throws Exception {
		User tester = new User();

		tester.email = "ImaDuck@gmail.com";
		tester.firstName = "Louis";
		tester.lastName = "Platyrhynchos";
		tester.techSkills = "Java MF'in Master";
		tester.password = "quack";

		users.save(tester);

		int userID = tester.getId();

		User retrievedUser = users.findFirstByEmail("ImaDuck@gmail.com");

		assertEquals(tester.id, retrievedUser.id);

		users.delete(tester);
	}


}
