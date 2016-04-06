package com.example.android.heartfit.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by kevinah95 on 05-Apr-16.
 */
public class Person extends RealmObject {
    @PrimaryKey
    private String id;
    private String name;
    private String email;
    private String gender;
    private int age;
    private double weight;
    private double height;
    private String activity_level;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public String getActivity_level() {
        return activity_level;
    }

    public void setActivity_level(String activity_level) {
        this.activity_level = activity_level;
    }
}
