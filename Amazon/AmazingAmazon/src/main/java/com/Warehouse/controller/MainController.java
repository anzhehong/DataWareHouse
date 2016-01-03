package com.Warehouse.controller;

import com.Warehouse.service.SearchMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by fowafolo
 * Date: 16/1/3
 */

@Controller
@RequestMapping("")
public class MainController {

    @Autowired
    private SearchMovieService searchMovieService;

    @RequestMapping("")
    public String MovieSearch(Model model)
    {
        model.addAttribute("allMovieStyles",searchMovieService.getAllMovieStyles());

        return "Amazon/FindMovie";
    }
}
