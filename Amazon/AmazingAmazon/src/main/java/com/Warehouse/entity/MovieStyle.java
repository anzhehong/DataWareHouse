package com.Warehouse.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by fowafolo on 16/1/1.
 */
@Table
@Entity
public class MovieStyle {
    @Id
    private int id;
    private int MovieId;
    private String MovieStyle;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMovieId() {
        return MovieId;
    }

    public void setMovieId(int movieId) {
        MovieId = movieId;
    }

    public String getMovieStyle() {
        return MovieStyle;
    }

    public void setMovieStyle(String movieStyle) {
        MovieStyle = movieStyle;
    }
}
