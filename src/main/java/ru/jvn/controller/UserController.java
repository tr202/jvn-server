package ru.jvn.controller;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.jvn.model.User;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    SessionFactory sessionFactory;

  /*  @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @Transactional
    public List<User> getAllUsers() {
        //User user = new User(UUID.randomUUID(), "peta", "feta");
        Session currentSession = sessionFactory.getCurrentSession();
        return currentSession.createQuery("SELECT a FROM user a", User.class).getResultList();

        //Session session = sessionFactory.openSession();
        //session.persist("user",user);
        //session.close();
        //return "ok";
    }*/


}
