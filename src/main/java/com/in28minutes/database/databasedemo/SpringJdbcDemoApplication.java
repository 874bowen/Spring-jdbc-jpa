package com.in28minutes.database.databasedemo;

import com.in28minutes.database.databasedemo.entity.Person;
import com.in28minutes.database.databasedemo.jdbc.PersonJdbcDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

// the code which we write in a specific method would be launched up as soon as
// applicationContext is ready
// @SpringBootApplication // Commented out this so that JDBC code doesn't get fired
public class SpringJdbcDemoApplication implements CommandLineRunner {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	PersonJdbcDAO dao;
	public static void main(String[] args) {
		SpringApplication.run(DatabaseDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// fire the query
		logger.info("All users -> {}", dao.findAll());
		logger.info("User id 10001 -> {}", dao.findById(10001));
		logger.info("User Ivan -> {}", dao.findByName("Ivan"));
		logger.info("Deleting User with id 10002 -> No of rows deleted {}", dao.deleteByID(10002));
		logger.info("Creating User with id 10006 -> No of rows added {}",
				dao.insert(new Person(10006, "Ian", "Nai", new Date())));
		logger.info("Updating User with id 10001 -> No of rows added {}",
				dao.update(new Person(10001, "Toroitich", "Eldy", new Date())));
		logger.info("All users -> {}", dao.findAll());
	}
}
