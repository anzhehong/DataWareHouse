package com.Warehouse.service;

import com.Warehouse.entity.AllMovie;
import com.Warehouse.entity.MovieStaff;
import com.Warehouse.entity.MovieStyle;
import com.Warehouse.entity.Staff;

import java.util.ArrayList;

/**
 * Created by fowafolo on 16/1/1.
 */
public interface AllMovieService {
    public void insertMovieInfo(ArrayList<AllMovie> allMovie);

    public void insertMovieStyle(ArrayList<MovieStyle> arrayList);

    public void insertStaff(ArrayList<Staff> arrayList);

    public void insertMovieStaff(ArrayList<MovieStaff> arrayList);
}
