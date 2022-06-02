package com.in28minutes.database.databasedemo.jdbc;

import com.in28minutes.database.databasedemo.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

@Repository
public class PersonJdbcDAO {
    // create a connection to the database
    @Autowired
    JdbcTemplate jdbcTemplate;

    // define rowMapper -> implement a simple interface RowMapper
    // inner class because we want this to be used only inside PersonJdbcDAO
    class PersonRowMapper implements RowMapper<Person>{

        @Override
        public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
            Person person = new Person();
            person.setId(rs.getInt("id"));
            person.setName(rs.getString("name"));
            person.setLocation(rs.getString("location"));
            person.setBirthDate(rs.getTimestamp("birth_date"));
            return person;
        }
    }

    //select * from person - from a query we get a result set
    public List<Person> findAll(){
        // in SpringJdbc there a automatic mapper so when column name matches - id -> id in the db
//        return jdbcTemplate.query("select * from person",
//                new BeanPropertyRowMapper<Person>(Person.class));
        // Whenever we are using beanROwMapper the Person bean should hava an empty constructor
        // Simple no args constructor
    }
}
