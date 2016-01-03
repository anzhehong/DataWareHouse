package com.Warehouse.DAO;

import com.Warehouse.entity.MovieStaff;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

/**
 * Created by fowafolo on 16/1/1.
 */
@Repository
public class MovieStaffDAOImp extends GeneralDAOImp<MovieStaff> implements MovieStaffDAO {

    public MovieStaffDAOImp()
    {
        super(MovieStaff.class);
    }

    @Override
    public ArrayList<MovieStaff> getMovieStaffArrayListByStaffId(int staffId) {

        String hql1 = "from MovieStaff where StaffId = :m";
        Query query1 = super.sessionFactory.getCurrentSession().createQuery(hql1);
        query1.setInteger("m", staffId);
        return (ArrayList<MovieStaff>) query1.list();
    }
}
