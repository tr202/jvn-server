package ru.jvn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ru.jvn.model.Pet;
import ru.jvn.repository.PetRepository;

import java.util.List;

@RestController
@RequestMapping("pets")
public class PetController {

    @Autowired
    private PetRepository petRepository;


    @PostMapping

    public String createPet(@RequestBody List<Pet> pets) {
        //petRepository.save(pets);
        petRepository.saveAll(pets);
        //Session currentSession = sessionFactory.getCurrentSession();
        //currentSession.saveOrUpdate(pet);
        return "Response-sec  "; //+ pet.getId();
    }

    public PetController() {
    }

    public PetController(PetRepository petRepository) {
        this.petRepository = petRepository;
    }
}
