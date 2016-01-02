package com.Warehouse.DAO;

import com.Warehouse.entity.AllMovie;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fowafolo
 * Date: 16/1/3
 * Time: ионГ1:14
 */
public interface SearchMovieDAO extends GeneralDAO<AllMovie> {

    public List findByMovieName(String name);
}
