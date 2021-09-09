package com.eugen.moviemaker.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@AllArgsConstructor
@Setter
@Getter
public class Movie {
    private long id;
    private String name_russian;
    private String name_native;
    private String description;
    private int year_of_release;
    private int rating;
    private double price;
    private String picture_path;
    private int votes;

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", name_russian='" + name_russian + '\'' +
                ", name_native='" + name_native + '\'' +
                ", description='" + description + '\'' +
                ", year_of_release=" + year_of_release +
                ", rating=" + rating +
                ", price=" + price +
                ", picture_path='" + picture_path + '\'' +
                ", votes=" + votes +
                '}';
    }

    public Movie() {
    }

    public int getYear_of_release() {
        return year_of_release;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return id == movie.id && rating == movie.rating && Double.compare(movie.price, price) == 0 && votes == movie.votes && Objects.equals(name_russian, movie.name_russian) && Objects.equals(name_native, movie.name_native) && Objects.equals(description, movie.description) && Objects.equals(year_of_release, movie.year_of_release) && Objects.equals(picture_path, movie.picture_path);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name_russian, name_native, description, year_of_release, rating, price, picture_path, votes);
    }
}
