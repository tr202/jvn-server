package ru.jvn.model.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.jvn.model.Pet;

import java.util.Random;



//@JsonIgnoreProperties(ignoreUnknown = true, value = {"name"})
public class PetOutDto {


    private String name;

    private Integer randomNum;

    public PetOutDto(Pet pet) {
        this.name = pet.getName();
        this.randomNum = new Random().nextInt();
    }

//    public PetOutDto() {
//    }
//
//    public PetOutDto(String name, Integer randomNum) {
//        this.name = name;
//        this.randomNum = randomNum;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getRandomNum() {
        return randomNum;
    }

    public void setRandomNum(Integer randomNum) {
        this.randomNum = randomNum;
    }
}
