package ru.jvn.controller;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ru.jvn.model.User;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;


@Controller
@RequestMapping(value = "/test")
public class TestController {

    @Autowired
    DataSource dataSource;

    @RequestMapping(value = "/units", method = RequestMethod.GET)
    @ResponseBody
    public String getUnits(@RequestParam(defaultValue = "0") Integer type) {
//        if (type == null) {
//            type = 0;
//        }
        return type.toString();
    }


    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getTest() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        List<Map<String, Object>> resultSet = jdbcTemplate.queryForList("SELECT * FROM pet");
        resultSet.stream().peek(o -> System.out.println(o.getClass())).forEach(System.out::println);
        for (Map<String, Object> row:resultSet){
            UUID id = (UUID) row.get("id");
            System.out.println(id);
        }


        return "ok";
    }

}








/////////////////////////////////

/*
@Controller
@RequestMapping(value = "/test")
public class TestController {

  //  @Autowired
  //  SessionFactory sessionFactory;

    @Autowired
    DataSource dataSource;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
   // @Transactional
    public String getTest() {
        //User user = new User(UUID.randomUUID(), "peta", "feta");
      //  Session currentSession = sessionFactory.getCurrentSession();
      //  currentSession.saveOrUpdate(user);
        //Session session = sessionFactory.openSession();
        //session.persist("user",user);
        //session.close();
        return "ok";
    }
   /* @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @Transactional
    public String createUser(@RequestBody User user) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.saveOrUpdate(user);
        return "Response-sec  " + user.getId();
    }

}

*/