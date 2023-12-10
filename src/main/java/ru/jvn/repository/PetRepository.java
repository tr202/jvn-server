package ru.jvn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import ru.jvn.model.Pet;

import java.util.UUID;


public interface PetRepository extends JpaRepository<Pet, UUID>
{
}
