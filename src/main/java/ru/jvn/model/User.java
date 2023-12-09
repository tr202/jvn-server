package ru.jvn.model;
//import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.*;
import java.util.List;
import java.util.UUID;


@javax.persistence.Entity(name="user")
@Table(name = "user", schema = "public")
public class  User {

    @Id
    @GeneratedValue
    @JsonIgnore
    private UUID id;

    @OneToMany
    @JoinColumn(name="owner_id")
    private List<Pet> pets;

    @Column(name = "username")
    private String username;

    @Column(name = "pass")
    private String pass;

    public User() {

    }

    public User (UUID id, String username, String pass) {
        this.id = id;
        this.username = username;
        this.pass = pass;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", pass='" + pass + '\'' +
                '}';
    }
}
