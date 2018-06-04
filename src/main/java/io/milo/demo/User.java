package io.milo.demo;

public class User {

    private String id;
    private String name;
    private String surname;
    private String gender;
    private String region;

    public User() {
    }

    public User(String id, String name, String surname, String gender, String region) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.gender = gender;
        this.region = region;
    }

    public User(String name, String surname, String gender, String region) {
        this.name = name;
        this.surname = surname;
        this.gender = gender;
        this.region = region;
    }

    @Override
    public String toString() {
        return name + " " + surname + " ( " + region + " ) ";
    }

    public String getId() {
        return id;
    }

    public User setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public String getSurname() {
        return surname;
    }

    public User setSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public String getGender() {
        return gender;
    }

    public User setGender(String gender) {
        this.gender = gender;
        return this;
    }

    public String getRegion() {
        return region;
    }

    public User setRegion(String region) {
        this.region = region;
        return this;
    }
}