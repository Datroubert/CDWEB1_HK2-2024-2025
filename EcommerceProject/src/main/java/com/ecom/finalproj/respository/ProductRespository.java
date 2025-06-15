package com.ecom.finalproj.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ecom.finalproj.model.Product;

import jakarta.persistence.Entity;
import jakarta.transaction.Transactional;

@Repository
public interface ProductRespository extends JpaRepository<Product, Integer> {

	public Product findById(int productId);

	public List<Product> findByProductName(String productName);

	// Tìm các sản phẩm có tên chứa keyword (không phân biệt hoa thường)
	List<Product> findByProductNameContainingIgnoreCase(String productName);

}
