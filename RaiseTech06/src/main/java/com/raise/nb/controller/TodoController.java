package com.raise.nb.controller;

import java.util.List;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.raise.nb.domain.Todo;
import com.raise.nb.mapper.TodoMapper;

@Controller
@RequestMapping("/todo")
@MapperScan(basePackages = {"com.raise.nb.mapper"})
public class TodoController {
	@Autowired
	TodoMapper todoMapper;

	@GetMapping
	public String index(Model model){
		List<Todo> list = todoMapper.selectAll();
		model.addAttribute("todolists",list);
		return "todo/index";
	}

	@GetMapping("new")
	public String newTest(Model model) {
		return "todo/new";
	}

	@GetMapping("{id}/edit")
	public String edit(@PathVariable int id, Model model) {
		Todo todo = todoMapper.selectByPrimaryKey(id);
		model.addAttribute("todo", todo);
		return "todo/edit";
	}

	@PostMapping
	public String create(@ModelAttribute Todo todo) {
		todoMapper.insertSelective(todo);
		return "redirect:/todo";
	}

	@PutMapping("{id}")
	public String update(@PathVariable int id, @ModelAttribute Todo todo){
		todo.setTodoId(id);
		todoMapper.updateByPrimaryKeySelective(todo);
		return "redirect:/todo";
	}

	@DeleteMapping("{id}")
	public String delete(@PathVariable int id) {
		todoMapper.deleteByPrimaryKey(id);
		return "redirect:/todo";
	}
}
