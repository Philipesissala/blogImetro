package com.springboot.blogimetro.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.springboot.blogimetro.entity.Article;
import com.springboot.blogimetro.entity.Category;
import com.springboot.blogimetro.repository.ArticleRepository;
import com.springboot.blogimetro.repository.CategoryRepository;

@Controller
@RequestMapping("/admin/articles/")
public class ArticleController {

	@Autowired
	private ArticleRepository articleRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@GetMapping("showForm")
	public String showArticleForm(Article article, Model model) {
		model.addAttribute("categories", this.categoryRepository.findAll());
		return "admin/article/addArticle";
	}

	@GetMapping("list")
	public String articles(Model model) {
		model.addAttribute("articles", this.articleRepository.findAll());
		return "admin/article/listArticle";
	}
	
	@PostMapping("add")
	public String addArticles(@RequestParam(name = "category") String idCategory, @Valid Article article,
			BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "admin/article/addArticle";
		}
		
		Optional<Category> category = this.categoryRepository.findById(Long.parseLong(idCategory));
		System.out.println(category);
		this.articleRepository.save(article);
		return "redirect:list";
	}

	@GetMapping("edit/{id}")
	public String showUpdateForm(@PathVariable("id") long id, Model model) {
		Article article = this.articleRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid article id:" + id));
		model.addAttribute("categories", this.categoryRepository.findAll());
		model.addAttribute("article", article);
		return "admin/article/updateArticle";
	}

	@PostMapping("update/{id}")
	public String updateArticle(@PathVariable("id") long id, @Valid Article article, BindingResult result,
			Model model) {
		if (result.hasErrors()) {
			article.setId(id);
			return "admin/article/updateArticle";
		}

		articleRepository.save(article);
		model.addAttribute("articles", this.articleRepository.findAll());
		return "admin/article/listArticle";
	}

	@GetMapping("delete/{id}")
	public String deleteArticle(@PathVariable("id") long id, Model model) {
		Article article = this.articleRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid article id: " + id));

		this.articleRepository.delete(article);
		model.addAttribute("articles", this.articleRepository.findAll());
		return "admin/article/listArticle";
	}
}
