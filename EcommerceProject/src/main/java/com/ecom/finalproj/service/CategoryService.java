package com.ecom.finalproj.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.finalproj.model.Category;
import com.ecom.finalproj.respository.CategoryRepository;

@Service
public class CategoryService {
	@Autowired
	private CategoryRepository categoryRepository;

	public List<Category> findAll() {
		return categoryRepository.findAll();
	}

	public Category findById_Cate(Integer id) {
		return (Category) categoryRepository.getById(id);
	}

	public Category findCategoryByName(String name) {
	    return categoryRepository.findByNameCategory(name);
	}
	
	
}