/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fahmi.hardinal.controller;

import com.fahmi.hardinal.dao.ArticleDao;
import com.fahmi.hardinal.entity.Article;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author JustHardinaL
 */
@Controller
@RequestMapping("/article")
public class ArticleController {
    
    @Autowired
    ArticleDao ad;
          
    @RequestMapping("/listarticle")
    public void listArticle(Model mdl) {
        mdl.addAttribute("listArticle", ad.findAll());
    }
    
    @RequestMapping("/deletearticle")
    public String delete(@RequestParam(value = "id") String id) {
        ad.delete(id);
        return "redirect:listarticle";
    }

    @RequestMapping(value = "/viewarticle{id}", method = RequestMethod.GET)
    public String viewForm(@RequestParam(value = "id") String id, 
            Model mdl){
        mdl.addAttribute("article",new Article());
        
        if (id!=null && !id.isEmpty()){
            Article a = ad.findOne(id);
            if (a != null){
                mdl.addAttribute("article", a);
            }
        }
        return "article/viewarticle";
    }
    
    @RequestMapping(value = "/updatearticle{id}", method = RequestMethod.GET)
    public String editForm(@RequestParam(value = "id") String id, 
            Model mdl){
        mdl.addAttribute("article",new Article());
        
        if (id!=null && !id.isEmpty()){
            Article a = ad.findOne(id);
            if (a != null){
                mdl.addAttribute("article", a);
            }
        }
        return "article/updatearticle";
    }
    
    @RequestMapping(value = "/updatearticle{id}", method = RequestMethod.POST)
    public String prosesUpdateForm(@RequestParam(value = "id") String id,
            @Valid Article a,
            BindingResult error) {
        if (error.hasErrors()) {
            return "article/updatearticle";
        }
        a.setId(id);
        ad.save(a);
        return "redirect:listarticle";
    }
    
    
    @RequestMapping(value = "/addarticle", method = RequestMethod.GET)
    public String addForm(Article article) {
        return "article/addarticle";
    }

    @RequestMapping(value = "/addarticle", method = RequestMethod.POST)
    public String addProsesForm(@Valid Article a, BindingResult error) {
        if (error.hasErrors()) {
            return "article/addarticle";
        }

        ad.save(a);
        return "redirect:listarticle";
    }
    
}
