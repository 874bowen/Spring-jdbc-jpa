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
        /* In some cases, you would want to define your own mapper
        * may be the names of the class and db don't match*/
        return jdbcTemplate.query("select * from person",
                new PersonRowMapper());
    }
    public Person findById(int id){
        // When we want a list we use .query() but when we want 1 thing we use .queryForObject()
        return jdbcTemplate.queryForObject("select * from person where id=?",
                new BeanPropertyRowMapper<Person>(Person.class),
                id);
    }
    public Person findByName(String name){
        // When we want a list we use .query() but when we want 1 thing we use .queryForObject()
        return jdbcTemplate.queryForObject("select * from person where name=?",
                new BeanPropertyRowMapper<Person>(Person.class),
                name);
    }
    // delete operation
    public int deleteByID(int id){
        // When you want to delete or update use the update() method
        return jdbcTemplate.update("delete from person where id=?", id);
    }
    // insert operation
    public int insert(Person person){
        // When you want to delete or update use the update() method
        return jdbcTemplate.update("insert into person (id, name, location, birth_date) " +
                        "values(? , ?, ?, ?)",
                person.getId(), person.getName(), person.getLocation(), new Timestamp(person.getBirthDate().getTime()));
    }
    // update operation
    public int update(Person person){
        // When you want to delete or update use the update() method
        return jdbcTemplate.update("update person " +
                        "set name = ?, location = ?, birth_date = ? " +
                        "where id=?",
                person.getName(), person.getLocation(), new Timestamp(person.getBirthDate().getTime()), person.getId());
    }
}
