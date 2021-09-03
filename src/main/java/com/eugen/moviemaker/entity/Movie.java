package com.eugen.moviemaker.entity;

import java.util.Date;

public class Movie {
    private int id;
    private String name_russian;
    private String name_native;
    private String description;
    private Date year_of_realease;
    private int rating;
    private double price;
    private String picture_path;
    private int votes;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getPicture_path() {
        return picture_path;
    }

    public void setPicture_path(String picture_path) {
        this.picture_path = picture_path;
    }

    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName_russian() {
        return name_russian;
    }

    public void setName_russian(String name_russian) {
        this.name_russian = name_russian;
    }

    public String getName_native() {
        return name_native;
    }

    public void setName_native(String name_native) {
        this.name_native = name_native;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", name_russian='" + name_russian + '\'' +
                ", name_native='" + name_native + '\'' +
                ", description='" + description + '\'' +
                ", year_of_realease=" + year_of_realease +
                ", rating=" + rating +
                ", price=" + price +
                ", picture_path='" + picture_path + '\'' +
                ", votes=" + votes +
                '}';
    }

    public Movie() {
    }

    public Movie(int id, String name_russian, String name_native, String description, Date year_of_realease, int rating, double price, String picture_path, int votes) {
        this.id = id;
        this.name_russian = name_russian;
        this.name_native = name_native;
        this.description = description;
        this.year_of_realease = year_of_realease;
        this.rating = rating;
        this.price = price;
        this.picture_path = picture_path;
        this.votes = votes;
    }

    public Date getYear_of_realease() {
        return year_of_realease;
    }
}
