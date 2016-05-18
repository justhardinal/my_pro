/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fahmi.hardinal.dao;

import com.fahmi.hardinal.entity.Article;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author JustHardinaL
 */
public interface ArticleDao extends PagingAndSortingRepository<Article, String>{
    
}
