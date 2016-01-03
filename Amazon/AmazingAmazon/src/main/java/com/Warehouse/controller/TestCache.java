package com.Warehouse.controller;

import com.Warehouse.entity.AllMovie;
import com.Warehouse.service.SearchMovieService;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;


/**
 * Created by fowafolo
 * Date: 16/1/4
 * Time: 上午3:49
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/WEB-INF/applicationContext.xml"})
public class TestCache {
    @Autowired
    SearchMovieService searchMovieService;

//    @After
    public void cleanUp() {
        Logger logger = Logger.getRootLogger();
        logger.setLevel(Level.ERROR);


    }
//    @Before
    public void AddToShowCacheWorking() {
        ArrayList<AllMovie> allMovieArrayList = searchMovieService.getByMovieStyle("Drama");
    }

//    @Before
    public void setup() {
        Logger logger = Logger.getRootLogger();
        Level level = logger.getLevel();
        logger.setLevel(level.ERROR);

        ArrayList<AllMovie> allMovieArrayList = searchMovieService.getByMovieStyle("Drama");
    }

    @Test
    public void testCache() {
        System.out.println("Test To Show Cache Faster");

        /**
         * First Test
         */
        System.out.println("Select: all movies whose style contains Drama...");
        System.out.println("First Test begins...");
        System.out.println("First time");
        long start1 = System.currentTimeMillis();
        ArrayList<AllMovie> allMovieArrayList = searchMovieService.getByMovieStyle("Drama");
        long end1 = System.currentTimeMillis();
        System.out.println("first time Drama size:\t"+ allMovieArrayList.size() + "time:\t" + (end1-start1));

        System.out.println("Second time");
        long start2 = System.currentTimeMillis();
        ArrayList<AllMovie> allMovieArrayList2 = searchMovieService.getByMovieStyle("Drama");
        long end2 = System.currentTimeMillis();
        System.out.println("second time Drama size:\t"+ allMovieArrayList2.size()+ "time:\t" + (end2-start2));

        /**
         * Second Test
         */
        System.out.println("Select: all movies whose director contains Ugo...");
        System.out.println("First Test begins...");
        System.out.println("First time");
        long start3 = System.currentTimeMillis();
        ArrayList<AllMovie> allMovieArrayList3 = searchMovieService.getByMovieDirector("Ugo");
        long end3 = System.currentTimeMillis();
        System.out.println("first time Ugo size:\t"+ allMovieArrayList3.size() + "time:\t" + (end3-start3));

        System.out.println("Second time");
        long start4 = System.currentTimeMillis();
        ArrayList<AllMovie> allMovieArrayList4 = searchMovieService.getByMovieDirector("Ugo");
        long end4 = System.currentTimeMillis();
        System.out.println("second time Ugo size:\t"+ allMovieArrayList4.size()+ "time:\t" + (end4-start4));
    }

}
