package com.Warehouse.DAO;

import com.Warehouse.entity.AllMovie;
import org.springframework.stereotype.Repository;

/**
 * Created by fowafolo on 16/1/1.
 */
@Repository
public class AllMovieDAOImp extends GeneralDAOImp<AllMovie> implements AllMovieDAO {

    public AllMovieDAOImp()
    {
        super(AllMovie.class);
    }
}
