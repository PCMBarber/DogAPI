package com.qa.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.qa.main.animals.Dog;
import com.qa.main.services.DogService;

@RestController
public class DogController {
	
	private DogService service;
	
	@Autowired
	public DogController(DogService service) {
		super();
		this.service = service;
	}

	@PostMapping("/createDog")
	public String create(@RequestBody Dog dog) {
		service.addDog(dog);
		return "Dog Added";
	}
	
	@GetMapping("/readAll")
	public List<Dog> read() {
		return service.getAll();
	}
	
	@GetMapping("/readByID/{id}")
	public Dog readById(@PathVariable long id) {
		return service.readById(id);
	}
	
	@GetMapping("/readByName/{name}")
	public List<Dog> readByName(@PathVariable String name) {
		return service.findByNameQuery(name);
	}
	
	@PostMapping("/updateDog/{id}")
	public String update(@PathVariable long id, @RequestBody Dog dog) {
		service.delete(service.readById(id));
		dog.setId(id);
		service.addDog(dog);
		return "Dog updated";
	}
	
	@PostMapping("/deleteDog/{id}")
	public String delete(@PathVariable long id) {
		service.delete(service.readById(id));
		return "Dog deleted";
	}
}
