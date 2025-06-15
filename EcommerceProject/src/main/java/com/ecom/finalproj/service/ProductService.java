package com.ecom.finalproj.service;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.tags.shaded.org.apache.bcel.generic.RETURN;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecom.finalproj.model.Product;
import com.ecom.finalproj.respository.ProductRespository;

@Service
@Transactional
public class ProductService {
	@Autowired(required = true)
	private ProductRespository resp;

	public void save(Product product) {
		resp.save(product);

	}

	public List<Product> findByProductNameContainingIgnoreCase(String productName) {
		return resp.findByProductNameContainingIgnoreCase(productName);
	}

	public List<Product> getAllProd() {
		return resp.findAll();
	}

	public List<Product> getAllProdByCategoryID(int id) {
		List<Product> temp = new ArrayList<>();
		List<Product> arr = getAllProd();
		for (Product product : arr) {
			if (product.getCategoryID() == id) {
				temp.add(product);
			}
		}
		return temp;
	}

	public Page<Product> getAllProd(int page) {
		Pageable pageable = PageRequest.of(page, 9);
		return resp.findAll(pageable);
	}

	public long totalProduct() {
		return resp.count();
	}

	public Product addProduct(Product product) {

		return resp.save(product);
	}

	public Product findById(int productId) {
		// TODO Auto-generated method stub
		return resp.findById(productId);
	}

	public void deleteById(int productId) {
		resp.deleteById(productId);
	}

//	// tim kiem san pham bang keyword
//	public List<Product> searchByKeyword(String productName) {
//		return resp.findByNameContainingIgnoreCase(productName);
//	}

}
