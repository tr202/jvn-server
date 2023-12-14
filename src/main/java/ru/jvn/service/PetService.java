package ru.jvn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.jvn.model.Pet;
import ru.jvn.model.User;
import ru.jvn.repository.PetRepository;
import ru.jvn.repository.UserRepository;

import java.util.Optional;
import java.util.UUID;

@Component
public class PetService {

    @Autowired
    PetRepository petRepository;

    @Autowired
    UserRepository userRepository;

    public Pet setUser(UUID id, UUID user_id) {
        Optional<Pet> optional_pet = petRepository.findById(id);
        Optional<User> optional_user = userRepository.findById(user_id);
        if (optional_user.isPresent() && optional_pet.isPresent()) {
            optional_pet.get().setUser(optional_user.get());
            return optional_pet.get();
        }
        else {
            return null;
        }
    }
}
