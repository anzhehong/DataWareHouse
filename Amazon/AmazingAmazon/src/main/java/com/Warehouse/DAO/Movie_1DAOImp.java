package com.Warehouse.DAO;

import com.Warehouse.entity.Movie_1;
import org.springframework.stereotype.Repository;

/**
 * Created by fowafolo on 16/1/1.
 */
@Repository
public class Movie_1DAOImp extends GeneralDAOImp<Movie_1> implements Movie_1DAO  {

   public Movie_1DAOImp()
   {
       super(Movie_1.class);
   }
}
