package com.Warehouse.DAO;

import com.Warehouse.entity.AllMovie;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fowafolo
 * Date: 16/1/3
 * Time: ÉÏÎç1:15
 */

@Repository
public class SearchMovieDAOImp extends GeneralDAOImp<AllMovie> implements SearchMovieDAO {

    public SearchMovieDAOImp()
    {
        super(AllMovie.class);
    }

    public List findByMovieName(String name) {

        String hql = "from AllMovie where MovieName = :m";
        Query query = super.sessionFactory.getCurrentSession().createQuery(hql);
        query.setString("m",name);

        return query.list();
    }
}
