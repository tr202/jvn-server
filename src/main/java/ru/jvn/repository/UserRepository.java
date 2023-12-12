package ru.jvn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import ru.jvn.model.User;

import java.util.UUID;


public interface UserRepository extends JpaRepository<User, UUID>
{
}
