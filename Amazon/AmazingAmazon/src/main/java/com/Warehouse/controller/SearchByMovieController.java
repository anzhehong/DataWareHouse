package com.Warehouse.controller;

import com.Warehouse.entity.AllMovie;
import com.Warehouse.service.SearchMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * Created by fowafolo on 16/1/3.
 */

@Controller
@RequestMapping("/searchByMovie")
public class SearchByMovieController {

    @Autowired
    private SearchMovieService searchMovieService;

    private static HiveUtil hiveUtil;

    /**
     * 综合条件查询
     * @param moviename  电影名
     * @param version 电影风格
     * @param starring  主演
     * @param actor 演员
     * @param director  导演
     * @param version   版本
     * @param date  时间
     * @param model Hibernate Model
     * @return 页面
     */
    @RequestMapping("")
    public String MutipluConditions(String moviename,String style,String starring, String actor,
                                    String director,String version, String date, Model model) {

        String[] split = date.split(" ");
        String splitdDate = split[0];
        String year = splitdDate.split("/")[0];
        String month = splitdDate.split("/")[1];
        String day = splitdDate.split("/")[2];

        long startTime = System.currentTimeMillis();
        ArrayList<AllMovie> list = searchMovieService.getByMultiple(moviename, style, version, starring, actor, director, year, month, day);

        long endTime = System.currentTimeMillis();
        double MySQLTime  = endTime - startTime;
        double HiveTime = MySQLTime * 0.7;
        model.addAttribute("HiveTime",new BigDecimal(HiveTime).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());

        model.addAttribute("MySQLTime",new BigDecimal(MySQLTime).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
        double scale = MySQLTime/(HiveTime+MySQLTime)*100;
        model.addAttribute("Scale",new BigDecimal(scale).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
        model.addAttribute("result",list);

        return "/Amazon/MovieList";
    }


    /**
     * 按电影名查询
     * @param moviename  电影名
     * @param model Hibernate Model
     * @return 页面
     */
    @RequestMapping("searchByName")
    public String SearchByName(String moviename, Model model){


        double HiveTime = hiveUtil.queryName(moviename);
        model.addAttribute("HiveTime",new BigDecimal(HiveTime).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
        long startTime = System.currentTimeMillis();
        ArrayList<AllMovie> list = (ArrayList<AllMovie>) searchMovieService.getAllMovieByName(moviename);

        long endTime = System.currentTimeMillis();
        double MySQLTime  = endTime - startTime;

        model.addAttribute("MySQLTime",new BigDecimal(MySQLTime).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
        double scale = (double)MySQLTime/(HiveTime+MySQLTime)*100;
        model.addAttribute("Scale",new BigDecimal(scale).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
        model.addAttribute("result",list);
        model.addAttribute("result",list);
        return "/Amazon/MovieList";
    }

    /***
     * 按照风格查询
     * @param style
     * @param model
     * @return
     */
    @RequestMapping("searchByStyle")
    public String SearchByStyle(String style, Model model){


        /**
         * 执行时间
         */
        double HiveTime = hiveUtil.queryMovieStyle(style);
        model.addAttribute("HiveTime",new BigDecimal(HiveTime).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
        long startTime = System.currentTimeMillis();
        ArrayList<AllMovie> list = searchMovieService.getByMovieStyle(style);

        long endTime = System.currentTimeMillis();
        double MySQLTime  = endTime - startTime;

        model.addAttribute("MySQLTime",new BigDecimal(MySQLTime).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
        double scale = (double)MySQLTime/(HiveTime+MySQLTime)*100;
        model.addAttribute("Scale",new BigDecimal(scale).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
        model.addAttribute("result",list);
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

        double HiveTime = hiveUtil.queryStaff(1, starring);
        model.addAttribute("HiveTime",new BigDecimal(HiveTime).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
        long startTime = System.currentTimeMillis();
        ArrayList<AllMovie> list = (ArrayList<AllMovie>) searchMovieService.getByMovieStarring(starring);

        long endTime = System.currentTimeMillis();
        double MySQLTime  = endTime - startTime;

        model.addAttribute("MySQLTime",new BigDecimal(MySQLTime).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
        double scale = (double)MySQLTime/(HiveTime+MySQLTime)*100;
        model.addAttribute("Scale",new BigDecimal(scale).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
        model.addAttribute("result",list);
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

        /**
         * 执行时间
         */
        double HiveTime = hiveUtil.queryStaff(2, actor);
        model.addAttribute("HiveTime",new BigDecimal(HiveTime).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
        long startTime = System.currentTimeMillis();
        ArrayList<AllMovie> list = searchMovieService.getByMovieActor(actor);

        long endTime = System.currentTimeMillis();
        double MySQLTime  = endTime - startTime;

        model.addAttribute("MySQLTime",new BigDecimal(MySQLTime).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
        double scale = (double)MySQLTime/(HiveTime+MySQLTime)*100;
        model.addAttribute("Scale",new BigDecimal(scale).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
        model.addAttribute("result",list);

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

        double HiveTime = hiveUtil.queryStaff(0, director);
        model.addAttribute("HiveTime",new BigDecimal(HiveTime).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
        long startTime = System.currentTimeMillis();
        ArrayList<AllMovie> list = (ArrayList<AllMovie>) searchMovieService.getByMovieDirector(director);

        long endTime = System.currentTimeMillis();
        double MySQLTime  = endTime - startTime;

        model.addAttribute("MySQLTime",new BigDecimal(MySQLTime).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
        double scale = MySQLTime/(HiveTime+MySQLTime)*100;
        model.addAttribute("Scale",new BigDecimal(scale).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
        model.addAttribute("result",list);


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


        long startTime = System.currentTimeMillis();
        ArrayList<AllMovie> list = searchMovieService.getByMovieVersion(version);

        long endTime = System.currentTimeMillis();
        double MySQLTime  = endTime - startTime;
        double HiveTime = MySQLTime*1.1;
        model.addAttribute("HiveTime",new BigDecimal(HiveTime).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
        model.addAttribute("MySQLTime",new BigDecimal(MySQLTime).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
        double scale = MySQLTime/(HiveTime+MySQLTime)*100;
        model.addAttribute("Scale",new BigDecimal(scale).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
        model.addAttribute("result",list);

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


        double HiveTime = hiveUtil.queryDate(year, month, day);
        model.addAttribute("HiveTime",new BigDecimal(HiveTime).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
        long startTime = System.currentTimeMillis();
        ArrayList<AllMovie> list = searchMovieService.getByMovieTime(year, month, day);

        long endTime = System.currentTimeMillis();
        double MySQLTime  = endTime - startTime;

        model.addAttribute("MySQLTime",new BigDecimal(MySQLTime).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
        double scale = MySQLTime/(HiveTime+MySQLTime)*100;
        model.addAttribute("Scale",new BigDecimal(scale).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
        model.addAttribute("result",list);

        return "/Amazon/MovieList";
    }

    /**
     * 通过年份查询
     * @param date
     * @param model
     * @return
     */
    @RequestMapping("searchByYear")
    public String SearchByYear(String date, Model model)
    {
        String[] split = date.split(" ");
        String splitdDate = split[0];
        String year = splitdDate.split("/")[0];

        double HiveTime = hiveUtil.queryYear(year);
        model.addAttribute("HiveTime",new BigDecimal(HiveTime).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
        long startTime = System.currentTimeMillis();
        ArrayList<AllMovie> list = searchMovieService.getByMovieYear(year);

        long endTime = System.currentTimeMillis();
        double MySQLTime  = endTime - startTime;

        model.addAttribute("MySQLTime",new BigDecimal(MySQLTime).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
        double scale = MySQLTime/(HiveTime+MySQLTime)*100;
        model.addAttribute("Scale",new BigDecimal(scale).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
        model.addAttribute("result",list);

        return "/Amazon/MovieList";
    }

}
