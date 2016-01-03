package com.Warehouse.DAO;

import com.Warehouse.entity.MovieStaff;

import java.util.ArrayList;

/**
 * Created by fowafolo on 16/1/1.
 */
public interface MovieStaffDAO extends GeneralDAO<MovieStaff> {

    public ArrayList<MovieStaff> getMovieStaffArrayListByStaffId(int staffId);
}
