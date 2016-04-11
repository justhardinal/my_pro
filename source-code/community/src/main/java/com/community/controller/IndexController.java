/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author hardinal
 */
@Controller
public class IndexController {
    
    @RequestMapping("/")
    String index(){
        return "index";
    }
    
    @RequestMapping("/login")
    String login(){
        return "login";
    }
    
}
