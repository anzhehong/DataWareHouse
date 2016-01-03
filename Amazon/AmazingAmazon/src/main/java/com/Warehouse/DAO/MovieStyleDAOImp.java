package com.Warehouse.DAO;

import com.Warehouse.entity.MovieStyle;
import org.springframework.stereotype.Repository;

/**
 * Created by fowafolo on 16/1/1.
 */
@Repository
public class MovieStyleDAOImp extends GeneralDAOImp<MovieStyle> implements MovieStyleDAO {

    public MovieStyleDAOImp()
    {
        super(MovieStyle.class);
    }

}
