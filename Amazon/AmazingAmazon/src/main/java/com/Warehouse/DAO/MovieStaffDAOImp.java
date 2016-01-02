package com.Warehouse.DAO;

import com.Warehouse.entity.MovieStaff;
import org.springframework.stereotype.Repository;

/**
 * Created by fowafolo on 16/1/1.
 */
@Repository
public class MovieStaffDAOImp extends GeneralDAOImp<MovieStaff> implements MovieStaffDAO {

    public MovieStaffDAOImp()
    {
        super(MovieStaff.class);
    }
}
