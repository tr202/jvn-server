package ru.jvn.controller;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ru.jvn.model.User;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.UUID;


@Controller
@RequestMapping(value = "/test")
public class TestController {

  //  @Autowired
  //  SessionFactory sessionFactory;

//    @Autowired
//    DataSource dataSource;

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
    }*/

}
