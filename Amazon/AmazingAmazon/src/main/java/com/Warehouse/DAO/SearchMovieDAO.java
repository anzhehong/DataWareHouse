package com.Warehouse.DAO;

import com.Warehouse.entity.AllMovie;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fowafolo
 * Date: 16/1/3
 * Time: ÉÏÎç1:14
 */
public interface SearchMovieDAO extends GeneralDAO<AllMovie> {

    public ArrayList<String> getAllMovieStyles();

    public ArrayList<AllMovie> findByMutiple(String name, String style, String starring,
                                             String actor, String director, String version,
                                             String year, String month, String day);

    /**
     * pass
     * @param name
     * @return
     */
    public List findByMovieName(String name);

    /**
     * pass
     * @param style
     * @return
     */
    public ArrayList<AllMovie> findByMovieStyle(String style);

    public ArrayList<AllMovie> findByStarring(String starring);

    public ArrayList<AllMovie> findByActor(String actor);

    public ArrayList<AllMovie> findByDirector(String director);

    public ArrayList<AllMovie> findByVersion(String version);

    public ArrayList<AllMovie> findByTime(String year, String month, String day);

    public ArrayList<AllMovie> findByYear(String year);

    /**
     * test get session
     */
    public SessionFactory getSessionFactory();
}
