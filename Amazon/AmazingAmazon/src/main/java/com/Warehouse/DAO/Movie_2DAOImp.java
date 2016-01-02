package com.Warehouse.DAO;

import com.Warehouse.entity.Movie_2;
import org.springframework.stereotype.Repository;

/**
 * Created by fowafolo on 16/1/1.
 */
@Repository
public class Movie_2DAOImp extends GeneralDAOImp<Movie_2> implements Movie_2DAO{

    public Movie_2DAOImp()
    {
        super(Movie_2.class);
    }
}
