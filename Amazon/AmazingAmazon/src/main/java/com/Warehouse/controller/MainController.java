package com.Warehouse.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by fowafolo
 * Date: 16/1/3
 * Time: ионГ3:46
 */

@Controller
@RequestMapping("")
public class MainController {

    @RequestMapping("")
    public String MovieSearch()
    {
        return "Amazon/FindMovie";
    }
}
