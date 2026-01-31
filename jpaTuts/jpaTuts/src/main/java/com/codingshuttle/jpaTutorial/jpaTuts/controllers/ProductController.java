package com.codingshuttle.jpaTutorial.jpaTuts.controllers;

import com.codingshuttle.jpaTutorial.jpaTuts.entities.ProductEntity;
import com.codingshuttle.jpaTutorial.jpaTuts.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/products")
public class ProductController {

    private final int PAGE_SIZE = 5;

    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping
    public List<ProductEntity> getAllProducts(
            @RequestParam(defaultValue = "") String title,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "0") Integer pageNumber){

        
        return productRepository.findByTitleContainingIgnoreCase(title,
                PageRequest.of(pageNumber,PAGE_SIZE,Sort.by(sortBy)));

        //return productRepository.findBy(Sort.by(Sort.Direction.DESC,sortBy,"price","quantity"));
//        return productRepository.findBy(Sort.by(
//                Sort.Order.desc(sortBy),
//                Sort.Order.asc("title")
//        ));




//        Pageable pageable = PageRequest.of(pageNumber,
//                PAGE_SIZE,
//                Sort.by(sortBy));
//        return productRepository.findAll(pageable).getContent();

    }
}
