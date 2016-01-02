package com.Warehouse.controller;

import com.Warehouse.entity.AllMovie;
import com.Warehouse.service.SearchMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

/**
 * Created by fowafolo on 16/1/3.
 */

@Controller
@RequestMapping("/searchByMovie")
public class SearchByMovieController {

    @Autowired
    private SearchMovieService searchMovieService;

    /**
     * 综合条件查询
     * @param moviename  电影名
     * @param style 电影风格
     * @param starring  主演
     * @param actor 演员
     * @param direcotr  导演
     * @param version   版本
     * @param date  时间
     * @param model Hibernate Model
     * @return 页面
     */
    @RequestMapping("")
    public String MutipluConditions(String moviename,String style,String starring, String actor,
                                    String direcotr,String version, String date, Model model) {
        ArrayList<AllMovie> list = (ArrayList<AllMovie>) searchMovieService.getAllMovieByName(moviename);
        model.addAttribute("result",list);
        return "/Amazon/MovieList";
    }

    /**
     * 综合条件查询
     * @param moviename  电影名
     * @param model Hibernate Model
     * @return 页面
     */
    @RequestMapping("searchByName")
    public String SearchByName(String moviename, Model model){
        ArrayList<AllMovie> list = (ArrayList<AllMovie>) searchMovieService.getAllMovieByName(moviename);
        model.addAttribute("result",list);
        return "/Amazon/MovieList";
    }

    /***
     * 按照电影名查询
     * @param style
     * @param model
     * @return
     */
    @RequestMapping("searchByStyle")
    public String SearchByStyle(String style, Model model){
        return "/Amazon/MovieList";
    }

    /***
     * 按照主演查询
     * @param starring
     * @param model
     * @return
     */
    @RequestMapping("searchByStarring")
    public String SearchByStarring(String starring, Model model){
        return "/Amazon/MovieList";
    }

    /***
     * 按照演员查询
     * @param actor
     * @param model
     * @return
     */
    @RequestMapping("searchByActor")
    public String SearchByActor(String actor, Model model) {
        return "/Amazon/MovieList";
    }

    /***
     * 按照导演查询
     * @param director
     * @param model
     * @return
     */
    @RequestMapping("searchByDirector")
    public String SearchByDirector(String director, Model model) {
        return "/Amazon/MovieList";
    }

    /***
     * 按照版本查
     * @param version
     * @param model
     * @return
     */
    @RequestMapping("searchByVersion")
    public String SearchByVersion(String version, Model model) {
        return "/Amazon/MovieList";
    }

    /***
     * 按照时间查询
     * @param date
     * @param model
     * @return
     */
    @RequestMapping("searchByDate")
    public String SearchByDate(String date, Model model) {
        String[] split = date.split(" ");
        String splitdDate = split[0];
        String year = splitdDate.split("/")[0];
        String month = splitdDate.split("/")[1];
        String day = splitdDate.split("/")[2];
        System.out.println(year);
        System.out.println(month);
        System.out.println(day);

        return "/Amazon/MovieList";
    }

}
