package ru.jvn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ru.jvn.model.Pet;
import ru.jvn.repository.PetRepository;

@RestController
public class PetController {

    //@Autowired
    //private PetRepository petRepository;

    //@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping
    @Transactional
    public String createUser(@RequestBody String pet) {
        //petRepository.save(pet);
        //Session currentSession = sessionFactory.getCurrentSession();
        //currentSession.saveOrUpdate(pet);
        return "Response-sec  " + pet;
    }

}
