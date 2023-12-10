package ru.jvn.model;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;


@Entity( name = "user" )
@Table(name = "user", schema = "public")
public class  User {

    @Id
    @GeneratedValue
    private Long id;


    private String username;


    private String pass;

    public User() {

    }


    public User(Long id, String username, String pass) {
        this.id = id;
        this.username = username;
        this.pass = pass;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
}


