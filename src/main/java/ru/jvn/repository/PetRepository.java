package ru.jvn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import ru.jvn.model.Pet;

import java.util.UUID;


@Repository
public interface PetRepository extends CrudRepository<Pet, UUID> {
}
