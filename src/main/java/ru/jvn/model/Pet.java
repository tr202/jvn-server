package ru.jvn.model;

import javax.persistence.*;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Entity
public class Pet {

    @Id
    @GeneratedValue
    private UUID id;

    private String pet_type;
    @ManyToOne
    private User user;

    public String getPet_type() {
        return pet_type;
    }

    public void setPet_type(String pet_type) {
        this.pet_type = pet_type;
    }

    public Pet(UUID id, String pet_type, String name) {
        this.id = id;
        this.pet_type = pet_type;
        this.name = name;
    }

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
