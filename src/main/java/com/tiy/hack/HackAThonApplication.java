package com.tiy.hack;

import org.h2.tools.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;

@SpringBootApplication
public class HackAThonApplication {

	public static void main(String[] args) throws SQLException {
		SpringApplication.run(HackAThonApplication.class, args);
		Server.createWebServer().start();
	}
	public void createUser()
	{

	}
}
