package com.Warehouse.DAO;

import com.Warehouse.entity.Movie_3;
import org.springframework.stereotype.Repository;

/**
 * Created by fowafolo on 16/1/1.
 */
@Repository
public class Movie_3DAOImp extends GeneralDAOImp<Movie_3> implements Movie_3DAO{

    public Movie_3DAOImp() {
        super(Movie_3.class);
    }
}
