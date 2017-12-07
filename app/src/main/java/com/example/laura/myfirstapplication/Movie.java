package com.example.laura.myfirstapplication;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Laura on 31-Oct-17.
 */

@Entity(tableName = "movies")
public class Movie implements Serializable{
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name="name")
    private String name;

    @ColumnInfo(name="year")
    private int year;

    @ColumnInfo(name="genre")
    @TypeConverters(value = EnumConverter.class)
    private List<CustomGenre> genre;

    @ColumnInfo(name="rating")
    private double rating;

    @ColumnInfo(name="plays")
    private int plays;

    @ColumnInfo(name="picture")
    private int picture;

    @ColumnInfo(name="trailer")
    private String trailerLink;

    @Ignore
    public Movie(String name, int year, List<CustomGenre> genre) {
        this.name = name;
        this.year = year;
        this.genre = genre;
    }

    public Movie(String name, int year, List<CustomGenre> genre, int picture) {
        this.name = name;
        this.year = year;
        this.genre = genre;
        this.picture = picture;
    }

    @Ignore
    public Movie(String name, int year, List<CustomGenre> genre, double rating, int plays, int picture, String trailerLink) {
        this.name = name;
        this.year = year;
        this.genre = genre;
        this.rating = rating;
        this.plays = plays;
        this.picture = picture;
        this.trailerLink = trailerLink;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
