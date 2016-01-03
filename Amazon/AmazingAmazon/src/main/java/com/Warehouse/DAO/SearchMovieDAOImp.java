package com.Warehouse.DAO;

import com.Warehouse.entity.AllMovie;
import com.Warehouse.entity.MovieStyle;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private MovieStyleDAO movieStyleDAO;


    @Override
    public ArrayList<String> getAllMovieStyles() {
        ArrayList<MovieStyle> styles = (ArrayList<MovieStyle>) movieStyleDAO.queryAll();
        ArrayList<String> arrayList = new ArrayList<String>();
        for (int i = 0; i< styles.size(); i++)
        {
            arrayList.add(styles.get(i).getMovieStyle());
        }
        ArrayList<String> strings = new ArrayList<String>();
        for (int j = 0; j<arrayList.size(); j++)
        {
            if (!strings.contains(arrayList.get(j)))
                strings.add(arrayList.get(j));
        }

        return strings;
    }

    public ArrayList<AllMovie> findByMutiple(String name, String style, String starring, String actor, String director, String version, String year, String month, String day) {
        return null;
    }

    public List findByMovieName(String name) {

        String hql = "from AllMovie where MovieName = :m";
        Query query = super.sessionFactory.getCurrentSession().createQuery(hql);
        query.setString("m",name);

        return query.list();
    }

    public ArrayList<AllMovie> findByMovieStyle(String style) {
        String hql1 = "from MovieStyle where MovieStyle = :m";
        Query query1 = super.sessionFactory.getCurrentSession().createQuery(hql1);
        query1.setString("m",style);
        ArrayList<MovieStyle> styles = (ArrayList<MovieStyle>) query1.list();

        ArrayList<AllMovie> allMovieArrayList = new ArrayList<AllMovie>();
        for (int i = 0; i < styles.size(); i++)
        {
            AllMovie temp = queryByIntId(styles.get(i).getMovieId());
            allMovieArrayList.add(temp);
        }
        return allMovieArrayList;
    }

    public ArrayList<AllMovie> findByStarring(String starring) {

        return null;
    }

    public ArrayList<AllMovie> findByActor(String actor) {
        return null;
    }

    public ArrayList<AllMovie> findByDirector(String director) {
        return null;
    }

    public ArrayList<AllMovie> findByVersion(String version) {
        String hql = "from AllMovie where MovieVersion like :m";
        Query query = super.sessionFactory.getCurrentSession().createQuery(hql);
        query.setString("m",version+"%");
        return (ArrayList<AllMovie>) query.list();
    }

    public ArrayList<AllMovie> findByTime(String year, String month, String day) {
        String hql = "from AllMovie where Year = :m and Month = :n and Day = :o";
        Query query = super.sessionFactory.getCurrentSession().createQuery(hql);
        query.setString("m",year);
        query.setString("n",month);
        query.setString("o",day);
        return null;
    }

    public ArrayList<AllMovie> findByYear(String year) {
        String hql = "from AllMovie where Year = :m";
        Query query = super.sessionFactory.getCurrentSession().createQuery(hql);
        query.setString("m",year);
        return null;
    }
}
