package com.tiy.hack;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

/**
 * Created by RdDvls on 9/30/16.
 */
@org.springframework.stereotype.Controller
public class Controller {

    @RequestMapping(path="/home", method = RequestMethod.GET)
    public String home(Model model, HttpSession session){
        return "home";
    }
    @RequestMapping(path="/test", method = RequestMethod.GET)
    public String test(Model model, HttpSession session){
        return "test";
    }




}
