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
     * �ۺ�������ѯ
     * @param moviename  ��Ӱ��
     * @param style ��Ӱ���
     * @param starring  ����
     * @param actor ��Ա
     * @param direcotr  ����
     * @param version   �汾
     * @param date  ʱ��
     * @param model Hibernate Model
     * @return ҳ��
     */
    @RequestMapping("")
    public String MutipluConditions(String moviename,String style,String starring, String actor,
                                    String direcotr,String version, String date, Model model) {
        ArrayList<AllMovie> list = (ArrayList<AllMovie>) searchMovieService.getAllMovieByName(moviename);
        model.addAttribute("result",list);
        return "/Amazon/MovieList";
    }

    /**
     * �ۺ�������ѯ
     * @param moviename  ��Ӱ��
     * @param model Hibernate Model
     * @return ҳ��
     */
    @RequestMapping("searchByName")
    public String SearchByName(String moviename, Model model){
        ArrayList<AllMovie> list = (ArrayList<AllMovie>) searchMovieService.getAllMovieByName(moviename);
        model.addAttribute("result",list);
        return "/Amazon/MovieList";
    }

    /***
     * ���յ�Ӱ����ѯ
     * @param style
     * @param model
     * @return
     */
    @RequestMapping("searchByStyle")
    public String SearchByStyle(String style, Model model){
        return "/Amazon/MovieList";
    }

    /***
     * �������ݲ�ѯ
     * @param starring
     * @param model
     * @return
     */
    @RequestMapping("searchByStarring")
    public String SearchByStarring(String starring, Model model){
        return "/Amazon/MovieList";
    }

    /***
     * ������Ա��ѯ
     * @param actor
     * @param model
     * @return
     */
    @RequestMapping("searchByActor")
    public String SearchByActor(String actor, Model model) {
        return "/Amazon/MovieList";
    }

    /***
     * ���յ��ݲ�ѯ
     * @param director
     * @param model
     * @return
     */
    @RequestMapping("searchByDirector")
    public String SearchByDirector(String director, Model model) {
        return "/Amazon/MovieList";
    }

    /***
     * ���հ汾��
     * @param version
     * @param model
     * @return
     */
    @RequestMapping("searchByVersion")
    public String SearchByVersion(String version, Model model) {
        return "/Amazon/MovieList";
    }

    /***
     * ����ʱ���ѯ
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
