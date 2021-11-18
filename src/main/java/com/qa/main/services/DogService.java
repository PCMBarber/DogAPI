package com.qa.main.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.main.animals.Dog;
import com.qa.main.animals.DogRepository;

@Service
public class DogService {
	
	DogRepository repo;
	
	@Autowired
	public DogService(DogRepository repo) {
		super();
		this.repo = repo;
	}

	public void addDog(Dog dog) {
		repo.save(dog);
	}
	
	public List<Dog> getAll() {
		return repo.findAll();
	}
	
	public List<Dog> findByName(String name) {
		return getAll().stream()
				.filter(dog -> dog.getName().equals(name))
				.collect(Collectors.toList());
	}
	
	public List<Dog> findByNameQuery(String name) {
		return repo.findDogByName(name);
	}
	
	public Dog readById(long id) {
		Optional<Dog> option = repo.findById(id);
		if(option.isPresent()) {
			return option.get();
		} 
		return null;
	}
	
	public void delete(Dog dog) {
		repo.deleteById(dog.getId());
	}
}
