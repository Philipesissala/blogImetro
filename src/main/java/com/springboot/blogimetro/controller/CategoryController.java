package com.springboot.blogimetro.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springboot.blogimetro.entity.Category;
import com.springboot.blogimetro.repository.CategoryRepository;

@Controller
@RequestMapping("/admin/categories/")
public class CategoryController {
	
	@Autowired
	private CategoryRepository categoryRepository;

	@GetMapping("showForm")
	public String showCategoryForm(Category category) {
		return "admin/category/addCategory";
	}

	@GetMapping("list")
	public String categories(Model model) {
		model.addAttribute("categories", this.categoryRepository.findAll());
		return "admin/category/listCategories";
	}
	
	@PostMapping("add")
	public String addCategory(@Valid Category category, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "admin/category/addCategory";
		}
		this.categoryRepository.save(category);
		return "redirect:list";
	}
	
	@GetMapping("edit/{id}")
	public String showUpdateForm(@PathVariable("id") long id, Model model) {
		Category category = this.categoryRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid category id:" + id));

		model.addAttribute("category", category);
		return "admin/category/updateCategory";
	}
	
	@PostMapping("update/{id}")
	public String updateCategory(@PathVariable("id") long id, @Valid Category category, BindingResult result,
			Model model) {
		if (result.hasErrors()) {
			category.setId(id);
			return "admin/category/updateCategory";
		}
		categoryRepository.save(category);
		model.addAttribute("categories", this.categoryRepository.findAll());
		return "admin/category/listCategories";
	}
	
	@GetMapping("delete/{id}")
	public String deleteCategory(@PathVariable("id") long id, Model model) {
		Category category = this.categoryRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid category id: " + id));

		this.categoryRepository.delete(category);
		model.addAttribute("categories", this.categoryRepository.findAll());
		return "admin/category/listCategories";
	}

}
