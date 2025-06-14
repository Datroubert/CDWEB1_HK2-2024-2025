package com.ecom.finalproj.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecom.finalproj.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

	  // Thêm phương thức này để tìm category theo tên
    Category findByNameCategory(String nameCategory);
}
