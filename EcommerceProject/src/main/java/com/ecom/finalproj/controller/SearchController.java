package com.ecom.finalproj.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ecom.finalproj.model.Product;
import com.ecom.finalproj.respository.ProductRespository;
import com.ecom.finalproj.service.ProductService;

@Controller

public class SearchController {

	@Autowired
	private ProductRespository productRespository;

	@Autowired
	private ProductService productService;
	
//	@GetMapping("/searchProduct")
//	public String searchProduct(@RequestParam String keyword, Model model) {
//		List<Product> products = productRespository.findByProductNameContainingIgnoreCase(keyword);
//		model.addAttribute("products", products);
//		return "shop"; // hoặc tên file JSP hiển thị sản phẩm của bạn
//	}
//
//	@GetMapping("api/searchProduct")
//	
//	@ResponseBody
//	public List<Product> searchProduct(@RequestParam String keyword) {
//		return productService.findByProductNameContainingIgnoreCase(keyword);
//	}
}
