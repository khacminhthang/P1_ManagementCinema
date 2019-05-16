/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.minhthang.core;

/**
 *
 * @author Minh Thắng
 */
public class Movie {

    private long id;
    private String type;
    private String name;
    private String introduction;
    private String actor;
    private String director;
    private String country;
    private String length;
    private String image;
    private String day;
    private String time;

    @Override

    public String toString() {
        return "Movie{" + "id=" + id + ", type=" + type + ", name=" + name + ", introduction=" + introduction + '}';
    }

    public Movie() {
        super();
    }

    public Movie(long id, String type, String name, String introduction) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.introduction = introduction;
    }

    public long getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getActor() {
        return actor;
    }

    public String getDirector() {
        return director;
    }

    public String getCountry() {
        return country;
    }

    public String getLength() {
        return length;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDay() {
        return day;
    }

    public String getTime() {
        return time;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Movie(long id, String type, String name, String introduction, String actor, String director, String country, String length, String image, String day, String time) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.introduction = introduction;
        this.actor = actor;
        this.director = director;
        this.country = country;
        this.length = length;
        this.image = image;
        this.day = day;
        this.time = time;
    }

}
