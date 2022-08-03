package com.springboot.blogimetro.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springboot.blogimetro.entity.User;
import com.springboot.blogimetro.repository.UserRepository;

@Controller
@RequestMapping("/users/")
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@GetMapping("showForm")
	public String showUserForm(User user) {
		return "addUser";
	}

	@GetMapping("list")
	public String articles(Model model) {
		model.addAttribute("users", this.userRepository.findAll());
		return "index";
	}

	@PostMapping("add")
	public String addUsers(@Valid User user, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "addUser";
		}
		this.userRepository.save(user);
		return "redirect:list";
	}

	@GetMapping("edit/{id}")
	public String showUpdateForm(@PathVariable("id") long id, Model model) {
		User user = this.userRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid user id:" + id));

		model.addAttribute("user", user);
		return "updateArticle";
	}

	@GetMapping("update/{id}")
	public String updateArticle(@PathVariable("id") long id, @Valid User user, BindingResult result, Model model) {
		if (result.hasErrors()) {
			user.setId(id);
			return "updateUser";
		}
		userRepository.save(user);
		model.addAttribute("articles", this.userRepository.findAll());
		return "index";
	}

	@DeleteMapping("delete{id}")
	public String deleteUser(@PathVariable("id") long id, Model model) {
		User user = this.userRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid user id: " + id));

		this.userRepository.delete(user);
		model.addAttribute("articles", this.userRepository.findAll());
		return "index";
	}
}
