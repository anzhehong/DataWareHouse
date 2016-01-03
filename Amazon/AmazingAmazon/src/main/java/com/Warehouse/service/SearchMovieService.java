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
                                             String starring, String actor, String direcotr,
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

    public ArrayList<AllMovie> getByMovieVersion(String version);

    public ArrayList<AllMovie> getByMovieYear(String year);

    public ArrayList<AllMovie> getByMovieTime(String date);

    public ArrayList<AllMovie> getByMovieStarring(String starring);

    public ArrayList<AllMovie> getByMovieActor(String actor);

    public ArrayList<AllMovie> gtByMovieDirector(String director);
}
