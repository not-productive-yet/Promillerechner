package com.example.promillrechner_mobapp;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class data_person {

    @PrimaryKey(autoGenerate = true)

    private int id;
    private String name;
    private Double weight;
    private int size;
    private boolean male;

    public data_person(String name, Double weight, int size, boolean male){
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
