package com.Warehouse.service;

import com.Warehouse.entity.AllMovie;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fowafolo
 * Date: 16/1/3
 * Time: 上午1:13
 */
public interface SearchMovieService {

    public ArrayList<AllMovie> getByMultiple(String name, String style, String version,
                                             String starring, String actor, String director,
                                             String year, String month, String day);

    /***
     * 通过名字返回所有符合条件的电影
     * @param name
     * @return
     */
    public List<AllMovie> getAllMovieByName(String name);


    /***
     * 得到全部Style，select标签显示
     * @return
     */
    public ArrayList<String> getAllMovieStyles();

    /**
     * 通过style拿到movie list
     * @param style
     * @return
     */
    public ArrayList<AllMovie> getByMovieStyle(String style);


    /**
     *
     * @param version
     * @return
     */
    public ArrayList<AllMovie> getByMovieVersion(String version);

    /**
     * 通过年份来查找电影
     * @param year
     * @return
     */
    public ArrayList<AllMovie> getByMovieYear(String year);

    /**
     * 通过具体日期查找
     * @param year
     * @param month
     * @param day
     * @return
     */
    public ArrayList<AllMovie> getByMovieTime(String year, String month, String day);

    /**
     * 通过主演名字找
     * @param starring
     * @return
     */
    public ArrayList<AllMovie> getByMovieStarring(String starring);

    /**
     * 通过演员名字找
     * @param actor
     * @return
     */
    public ArrayList<AllMovie> getByMovieActor(String actor);

    /**
     * 通过导演找
     * @param director
     * @return
     */
    public ArrayList<AllMovie> getByMovieDirector(String director);
}
