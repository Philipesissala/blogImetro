package com.springboot.blogimetro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springboot.blogimetro.repository.CategoryRepository;
import com.springboot.blogimetro.repository.ArticleRepository;

@Controller
@RequestMapping("")
public class homeController {
	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private ArticleRepository articleRepository;

	@GetMapping("")
	public String home(Model model) {
		model.addAttribute("categories", this.categoryRepository.findAll());
		model.addAttribute("articles", this.articleRepository.findAll());
		return "index";
	}

	@GetMapping("/")
	public String otherhome(Model model) {
		model.addAttribute("categories", this.categoryRepository.findAll());
		model.addAttribute("articles", this.articleRepository.findAll());
		return "index";
	}

	@GetMapping("/articles/{slug}")
	public String showArticlesByCategory(Model model, @PathVariable String slug) {
		model.addAttribute("categories", this.categoryRepository.findAll());
		model.addAttribute("articles", this.articleRepository.findAllByCategorySlug(slug));
		return "index";
	}

	@GetMapping("/article/{slug}")
	public String article(Model model, @PathVariable String slug) {
		model.addAttribute("categories", this.categoryRepository.findAll());
		model.addAttribute("article", this.articleRepository.findOneBySlug(slug));

		return "article";
	}

	@GetMapping("admin")
	public String homeAdmin() {
		return "admin/index";
	}
}
