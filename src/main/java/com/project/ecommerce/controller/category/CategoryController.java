package com.project.ecommerce.controller.category;

import com.project.ecommerce.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/list")
    public ModelAndView getAllCategory(){
        ModelAndView mv =  new ModelAndView("admin/category/categoryList");
        mv.addObject("categoryList", categoryService.getAll());
        return mv;
    }
}
