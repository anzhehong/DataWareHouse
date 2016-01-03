package com.Warehouse.service;

import com.Warehouse.DAO.SearchMovieDAO;
import com.Warehouse.DAO.StylesDAO;
import com.Warehouse.entity.AllMovie;
import com.Warehouse.entity.Styles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by fowafolo
 * Date: 16/1/3
 * Time: ÉÏÎç1:14
 */

@Service
@Transactional
public class SearchMovieServiceImp implements SearchMovieService {

    @Autowired
    private SearchMovieDAO searchMovieDAO;
    @Autowired
    private StylesDAO stylesDAO;

    @Override
    public ArrayList<AllMovie> getByMultiple(String name, String style, String version, String starring, String actor, String direcotr, String year, String month, String day) {
        return null;
    }

    public List<AllMovie> getAllMovieByName(String name) {
        return searchMovieDAO.findByMovieName(name);
    }

    @Override
    public ArrayList<String> getAllMovieStyles() {
        ArrayList<Styles> styles = (ArrayList<Styles>) stylesDAO.queryAll();
        ArrayList<String> strings = new ArrayList<String>();
        for (int i = 0; i< styles.size(); i++)
        {
            strings.add(styles.get(i).getName());
        }

        return strings;
    }

    @Override
    public ArrayList<AllMovie> getByMovieStyle(String style) {
        return searchMovieDAO.findByMovieStyle(style);
    }

    @Override
    public ArrayList<AllMovie> getByMovieVersion(String version) {

        return searchMovieDAO.findByVersion(version);
    }

    @Override
    public ArrayList<AllMovie> getByMovieYear(String year) {
        return null;
    }

    @Override
    public ArrayList<AllMovie> getByMovieTime(String date) {
        return null;
    }

    @Override
    public ArrayList<AllMovie> getByMovieStarring(String starring) {
        return null;
    }

    @Override
    public ArrayList<AllMovie> getByMovieActor(String actor) {
        return null;
    }

    @Override
    public ArrayList<AllMovie> gtByMovieDirector(String director) {
        return null;
    }
}
