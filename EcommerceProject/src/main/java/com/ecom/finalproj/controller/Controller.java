package com.ecom.finalproj.controller;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ecom.finalproj.model.Account;
import com.ecom.finalproj.model.Category;
import com.ecom.finalproj.model.Discount;
import com.ecom.finalproj.model.Product;
import com.ecom.finalproj.service.AccountService;
import com.ecom.finalproj.service.CartService;
import com.ecom.finalproj.service.CategoryService;
import com.ecom.finalproj.service.DiscountService;
import com.ecom.finalproj.service.ProductService;
import com.ecom.finalproj.service.UserService;

import jakarta.servlet.http.HttpSession;

import org.springframework.security.core.context.SecurityContextHolder;

@org.springframework.stereotype.Controller

public class Controller {
	@Autowired
	private AccountService service;
	@Autowired
	private DiscountService discountService;
	@Autowired
	private ProductService productService;
	@Autowired
	private CategoryService categoryService;

	@Autowired
	private UserService userService;
	
	@Autowired 
	private CartService cartService;

	@GetMapping("/home")
	public String getHomePage(Model model,HttpSession session) {
		// Cách lấy user đang đăng nhập (tự set vào sử dụng)
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();
		Account account = service.getAcc(currentPrincipalName);
		session.setAttribute("acc", account);
		System.out.print(currentPrincipalName);
		///
		System.out.print(false);
		List<Category> ListCategory = categoryService.findAll();
		List<Product> ListProduct = productService.getAllProd();
		int cartCount = cartService.sumCart(account.getUsername());

		model.addAttribute("cartCount", cartCount);
		model.addAttribute("ListProduct", ListProduct);
		model.addAttribute("ListCategory", ListCategory);
		return "index";
	}

	@GetMapping("/admin")
	public String admin(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();
		if (userService.getUser(currentPrincipalName).getRole() == 1) {
			long totalAccount = service.totalAccount();
			List<Account> list = service.getAll();
			List<Discount> listDiscount = discountService.getAllDiscount();
			List<Product> ListProduct = productService.getAllProd();
			List<Category> categories = categoryService.findAll();
			
			model.addAttribute("ListCategory", categories);
			model.addAttribute("totalProduct", productService.totalProduct());
			model.addAttribute("ListProduct", ListProduct);
			model.addAttribute("ListDiscount", listDiscount);
			model.addAttribute("ListAccount", list);
			model.addAttribute("TotalAccount", totalAccount);
			return "admin";
		}
		return "index";
	}

	@GetMapping("/shop")
	public String shop(Model model, @RequestParam(defaultValue = "0") int page) {
		List<Category> ListCategory = categoryService.findAll();

		Page<Product> ListProduct = productService.getAllProd(page);

		model.addAttribute("ListProduct", ListProduct.getContent());
		model.addAttribute("totalPage", ListProduct.getTotalPages());
		model.addAttribute("pageNo", page);
		model.addAttribute("ListCategory", ListCategory);

		return "shop";
	}
	@GetMapping("shopp")
	public String shop2(Model model, @RequestParam(defaultValue = "0") int page, @RequestParam("id") String id) {
		int id2= Integer.parseInt(id);
		List<Category> ListCategory = categoryService.findAll();

//		Page<Product> ListProduct = productService.getAllProd(page);
		
		List<Product> ListProduct = productService.getAllProdByCategoryID(id2);
		model.addAttribute("ListProduct", ListProduct);
//		model.addAttribute("totalPage", ListProduct.getTotalPages());
		model.addAttribute("pageNo", page);
		model.addAttribute("ListCategory", ListCategory);

		return "shop";
	}

	@GetMapping("/shop/{page}")
	public @ResponseBody List<Product> shop1(Model model, @RequestParam int page) {

		List<Category> ListCategory = categoryService.findAll();
		Page<Product> ListProduct = productService.getAllProd(page);
		List<Product> response = ListProduct.getContent();

		return response;
	}

	@GetMapping("/shop/page")
	public ModelAndView shopPage(@RequestParam(defaultValue = "0") int page) {
		int pageSize = 10;
		Page<Product> productsPage = productService.getAllProd(page);
		List<Category> ListCategory = categoryService.findAll();
		ModelAndView modelAndView = new ModelAndView("productList");
		modelAndView.addObject("ListProduct", productsPage.getContent());
		modelAndView.addObject("pageNo", page);
		modelAndView.addObject("totalPage", productsPage.getTotalPages());
		modelAndView.addObject("ListCategory", ListCategory);
		return modelAndView;
	}

	@GetMapping("/ajax")
	public String ajax() {
		return "ajax";
	}

	@GetMapping("/error/403")
	public String error() {
		return "403";
	}

	@GetMapping("/test")
	public @ResponseBody String test() {
		return "s";
	}
	
	@GetMapping("/search")
	public ModelAndView searchProducts(@RequestParam("search") String keyword) {
		ModelAndView view = new ModelAndView("shop");
		List<Product> ListProduct = productService.findByProductNameContainingIgnoreCase(keyword);
		List<Category> ListCategory = categoryService.findAll();

		view.addObject("ListCategory", ListCategory);
		view.addObject("ListProduct", ListProduct);
		productService.findByProductNameContainingIgnoreCase(keyword);

		return view;
	}

}
