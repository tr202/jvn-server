package ru.jvn.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Entity
public class Pet {

    @Id
    @GeneratedValue
    private UUID id;


    private String name;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Pet() {
    }

    public Pet(UUID id, String name) {
        this.id = id;
        this.name = name;
    }
}
