package com.in28minutes.database.databasedemo.jdbc;

import com.in28minutes.database.databasedemo.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PersonJdbcDAO {
    // create a connection to the database
    @Autowired
    JdbcTemplate jdbcTemplate;
    //select * from person - from a query we get a result set
    public List<Person> findAll(){
        // in SpringJdbc there a automatic mapper so when column name matches - id -> id in the db
        return jdbcTemplate.query("select * from person",
                new BeanPropertyRowMapper<Person>(Person.class));
        // Whenever we are using beanROwMapper the Person bean should hava an empty constructor
        // Simple no args constructor
    }
}
