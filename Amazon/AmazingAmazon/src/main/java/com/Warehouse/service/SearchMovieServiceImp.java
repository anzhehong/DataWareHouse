package com.Warehouse.service;

import com.Warehouse.DAO.SearchMovieDAO;
import com.Warehouse.entity.AllMovie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by fowafolo
 * Date: 16/1/3
 * Time: ионГ1:14
 */

@Service
@Transactional
public class SearchMovieServiceImp implements SearchMovieService {

    @Autowired
    private SearchMovieDAO searchMovieDAO;

    public List<AllMovie> getAllMovieByName(String name) {
        return searchMovieDAO.findByMovieName(name);
    }
}
