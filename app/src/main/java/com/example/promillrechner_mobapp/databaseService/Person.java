package com.example.promillrechner_mobapp.databaseService;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Entity
public class Person implements Serializable {

    @PrimaryKey(autoGenerate = true)

    private int id;
    private String name;
    private int weight;
    private int size;
    private boolean male;
    private String date;

    public Person(String name, int weight, int size, boolean male){
        this.name=name;
        this.weight=weight;
        this.size=size;
        this.male=male;

        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/YY");
        this.date=""+formatter.format(date);
    }

    public int getId(){return id;}

    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public int getWeight() {
        return weight;
    }

    public int getSize() {
        return size;
    }
    public boolean isMale() {
        return male;
    }

    public String getDate() {return date;}

    public void setMale(boolean male) { this.male = male; }

    public void setName(String name) {
        this.name = name;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setDate(String date) {
        this.date = date;
    }


}
