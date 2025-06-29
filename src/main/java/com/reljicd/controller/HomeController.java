package com.reljicd.controller;

import com.reljicd.model.Product;
import com.reljicd.service.ProductService;
import com.reljicd.util.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class HomeController {

    private static final int INITIAL_PAGE = 0;
    private static final int PAGE_SIZE = 5;

    private final ProductService productService;

    @Autowired
    public HomeController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/home")
    public ModelAndView home(@RequestParam("page") Optional<Integer> page) {
        int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;

        Page<Product> products = productService.findAll(
                PageRequest.of(evalPage, PAGE_SIZE, Sort.by("name"))
        );

        Pager pager = new Pager(products.getTotalPages(), products.getNumber(), BUTTONS_TO_SHOW);

        ModelAndView modelAndView = new ModelAndView("home");
        modelAndView.addObject("products", products);
        modelAndView.addObject("pager", pager);

        return modelAndView;
    }

    private static final int BUTTONS_TO_SHOW = 5;
}
