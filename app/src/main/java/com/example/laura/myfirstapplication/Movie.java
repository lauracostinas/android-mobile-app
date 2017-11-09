package com.example.laura.myfirstapplication;

import android.graphics.Picture;
import android.media.tv.TvContract;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Laura on 31-Oct-17.
 */

public class Movie implements Serializable{
    private String name;
    private int year;
    private List<CustomGenres> genre;
    private double rating;
    private int plays;
    private int picture;
    private String trailerLink;

    public Movie(String name, int year, List<CustomGenres> genre) {
        this.name = name;
        this.year = year;
        this.genre = genre;
    }

    public Movie(String name, int year, List<CustomGenres> genre, int picture) {
        this.name = name;
        this.year = year;
        this.genre = genre;
        this.picture = picture;
    }

    public Movie(String name, int year, List<CustomGenres> genre, double rating, int plays, int picture, String trailerLink) {
        this.name = name;
        this.year = year;
        this.genre = genre;
        this.rating = rating;
        this.plays = plays;
        this.picture = picture;
        this.trailerLink = trailerLink;
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

    public List<CustomGenres> getGenre() {
        return genre;
    }

    public void setGenre(List<CustomGenres> genre) {
        this.genre = genre;
    }

    public void addGenre(CustomGenres genre){
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
