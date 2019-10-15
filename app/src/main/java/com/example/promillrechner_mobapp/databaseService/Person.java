package com.example.promillrechner_mobapp.databaseService;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Person implements Serializable {

    @PrimaryKey(autoGenerate = true)

    private int id;
    private String name;
    private Double weight;
    private int size;
    private boolean male;

    public Person(String name, Double weight, int size, boolean male){
        this.name=name;
        this.weight=weight;
        this.size=size;
        this.male=male;
    }

    public int getId(){return id;}

    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public Double getWeight() {
        return weight;
    }

    public int getSize() {
        return size;
    }
    public boolean isMale() {
        return male;
    }



}
