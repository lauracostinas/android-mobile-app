package com.example.laura.myfirstapplication;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Laura on 31-Oct-17.
 */

public class Movie implements Serializable{
    private String id;

    private String name;

    private int year;

    private List<CustomGenre> genre;

    private double rating;

    private int plays;

    private int picture;

    private String trailerLink;

    public Movie() {
    }

    public void generateId() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + year;
        result = 31 * result + (genre != null ? genre.hashCode() : 0);

        this.id = String.valueOf(result);
    }


    public Movie(String name, int year, List<CustomGenre> genre) {
        this.name = name;
        this.year = year;
        this.genre = genre;

        this.generateId();
    }

    public Movie(String name, int year, List<CustomGenre> genre, int picture) {
        this.name = name;
        this.year = year;
        this.genre = genre;
        this.picture = picture;

        this.generateId();
    }

    public Movie(String name, int year, List<CustomGenre> genre, double rating, int plays, int picture, String trailerLink) {
        this.name = name;
        this.year = year;
        this.genre = genre;
        this.rating = rating;
        this.plays = plays;
        this.picture = picture;
        this.trailerLink = trailerLink;

        this.generateId();
    }

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

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public List<CustomGenre> getGenre() {
        return genre;
    }

    public void setGenre(List<CustomGenre> genre) {
        this.genre = genre;
    }

    public void addGenre(CustomGenre genre){
        this.genre.add(genre);
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getPlays() {
        return plays;
    }

    public void setPlays(int plays) {
        this.plays = plays;
    }

    public int getPicture() {
        return picture;
    }

    public void setPicture(int picture) {
        this.picture = picture;
    }

    public String getTrailerLink() {
        return trailerLink;
    }

    public void setTrailerLink(String trailerLink) {
        this.trailerLink = trailerLink;
    }


}
