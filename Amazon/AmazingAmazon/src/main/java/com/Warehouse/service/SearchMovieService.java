package com.Warehouse.service;

import com.Warehouse.entity.AllMovie;

import java.util.List;

/**
 * Created by fowafolo
 * Date: 16/1/3
 * Time: ����1:13
 */
public interface SearchMovieService {

    /***
     * ͨ�����ַ������з��������ĵ�Ӱ
     * @param name
     * @return
     */
    public List<AllMovie> getAllMovieByName(String name);
}
