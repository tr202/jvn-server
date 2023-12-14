package ru.jvn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.jvn.model.Pet;
import ru.jvn.model.dto.PetOutDto;
import ru.jvn.repository.PetRepository;
import ru.jvn.service.PetService;

import java.awt.*;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("pets")
public class PetController {




    @PutMapping(value = "/{id}/setUsers")
    public Pet setUsers(@PathVariable UUID id, @RequestBody Map<String,UUID> user_id) {//, @RequestBody UUID user_id) {
        return petService.setUser(id, user_id.get("user_id"));
        //ids.stream().map(row -> row.get("user_id")).forEach(System.out::println);
    }


    @Autowired
    private PetRepository petRepository;

    @Autowired
    PetService petService;


    @ResponseBody
    //@RequestMapping(value = "/{id}/setUser", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    @PutMapping(value = "/{id}/setUser") // Due to rest controller
    public String setUser(@PathVariable UUID id) {//, @RequestBody UUID user_id) {
        //Pet pet = petService.setUser(id, user_id);
        return "ok" + id;// + user_id;
    }



    @GetMapping("dto")
    public List<PetOutDto> getPetsDto() {
        List<Pet> pets = petRepository.findAll();
        //return pets.stream().map((Pet pet) -> {return new PetOutDto(pet);}).collect(Collectors.toList());
        return pets.stream().map(PetOutDto::new).peek(PetOutDto::toString).collect(Collectors.toList());
    }

    @GetMapping
    public List<Pet> getPets() {
        Pet pet = petRepository.findAll().stream().findFirst().get();
        PetOutDto petout = new PetOutDto(pet);
        System.out.println(petout.getName());
        return petRepository.findAll();
    }


    @PostMapping
    public List<UUID> createPet(@RequestBody List<Pet> pets) {
        //petRepository.save(pets);
        petRepository.saveAll(pets);
        //Session currentSession = sessionFactory.getCurrentSession();
        //currentSession.saveOrUpdate(pet);
        //Stream<Pet> petsIds = pets.stream();
        //listIds = [pet.id for pet in pets]
        return pets.stream().map(Pet::getId).collect(Collectors.toList()); //"Response-sec  ";+ pet.getId();
        //return pets.stream().map((Pet pet) -> {return new PetOutDto(pet);}).collect(Collectors.toList());
    }

    public PetController() {
    }

    public PetController(PetRepository petRepository) {
        this.petRepository = petRepository;
    }
}
