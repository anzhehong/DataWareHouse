package com.Warehouse.service;

import com.Warehouse.entity.AllMovie;

import java.util.List;

/**
 * Created by fowafolo
 * Date: 16/1/3
 * Time: 上午1:13
 */
public interface SearchMovieService {

    /***
     * 通过名字返回所有符合条件的电影
     * @param name
     * @return
     */
    public List<AllMovie> getAllMovieByName(String name);
}
