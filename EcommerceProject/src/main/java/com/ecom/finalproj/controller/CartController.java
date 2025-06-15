package com.ecom.finalproj.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;

import com.ecom.finalproj.model.Account;
import com.ecom.finalproj.model.Cart;
import com.ecom.finalproj.model.Product;
import com.ecom.finalproj.service.CartService;
import com.ecom.finalproj.service.ProductService;

@Controller
public class CartController  {

	@Autowired
	private CartService cartService;
	
	@Autowired 
	private ProductService service;
	
//	@GetMapping("/cart")
//	public @ResponseBody List<Cart> getCartsByUserName(@RequestParam String username){
//		System.out.println(username);
//		return cartService.getCartByUsername(username);
//	}
	
	@GetMapping("/cart")
	public ModelAndView getCartByUserName() {
		ModelAndView modelAndView = new ModelAndView("cart");
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		List<Cart> carts = cartService.getCartByUsername(username);
		double TotalPrice = 0;
		for (Cart cart : carts) {
			TotalPrice += cart.getPrice();
		}
		modelAndView.addObject("totalPrice", TotalPrice);
		
		List<Cart> liCarts = cartService.getCartByUsername(username);
		modelAndView.addObject("ListCart", liCarts);
		return modelAndView;
	}
	
	@PostMapping("/cart")
	public ModelAndView addtoCart(@RequestParam("ProductID") int ProductID, @RequestParam("soluong") int soluong, HttpSession session) {
		ModelAndView modelAndView = new ModelAndView("cart");
		Product product = service.findById(ProductID);
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		Cart newCart = new Cart(username, ProductID, "ip", soluong, product.getPrice(), product.getImgPath());
////		cartService.save(newCart);
		List<Cart> liCarts = cartService.getCartByUsername(username);
		if(!liCarts.isEmpty()) {
			if(cartService.contain(liCarts, ProductID)) {
				for (Cart cart : liCarts) {
					if(newCart.getProductID()== cart.getProductID()) {
						cart.setAmount(cart.getAmount() + newCart.getAmount());
						cartService.updateSoLuong(cart.getIdCart(),cart.getAmount());
						System.out.println("SoLuong: " +cart.getAmount());
					
					
						System.out.println("list: " + liCarts.size());
						
					}
				}
			}else {
				cartService.save(newCart);
			}
//		
//		
		} else {
			cartService.save(newCart);
			
		}
		
		List<Cart> liCarts2 = cartService.getCartByUsername(username);
		modelAndView.addObject("ListCart", liCarts2);
		return modelAndView;

	
	}
	
	@GetMapping("/cart/deleteProduct")
	public String deleteAccount(@RequestParam Long idCart,RedirectAttributes redirectAttributes) {
		cartService.deleteCart(idCart);
		return "redirect:/cart";
	}
	
	
	@GetMapping("/cart/tangsoluong")
	public String increaseAccount(@RequestParam Long idCart, @RequestParam int soluong,RedirectAttributes redirectAttributes) {
		if(soluong == 0) {
			
		return	deleteAccount(idCart, redirectAttributes);
		}
		cartService.increaseCart(idCart,soluong);
		return "redirect:/cart";
	}
	@PostMapping("cart/add")
	@ResponseBody
	public Map<String, Object> addToCart(
	        @RequestParam int productId,
	        @RequestParam(defaultValue = "1") int quantity,
	        HttpSession session) {
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    String username = authentication.getName();

	    Product product = service.findById(productId);
	    Cart newCart = new Cart(username, productId, product.getProductName(), quantity, product.getPrice(), product.getImgPath());

	    List<Cart> liCarts = cartService.getCartByUsername(username);
	    if(!liCarts.isEmpty()) {
	        if(cartService.contain(liCarts, productId)) {
	            for (Cart cart : liCarts) {
	                if(newCart.getProductID() == cart.getProductID()) {
	                    cart.setAmount(cart.getAmount() + newCart.getAmount());
	                    cartService.updateSoLuong(cart.getIdCart(), cart.getAmount());
	                }
	            }
	        } else {
	            cartService.save(newCart);
	        }
	    } else {
	        cartService.save(newCart);
	    }
	    // Đếm tổng số sản phẩm trong giỏ
	    int cartCount = cartService.getCartByUsername(username)
	        .stream().mapToInt(Cart::getAmount).sum();
	    Map<String, Object> result = new HashMap<>();
	    result.put("success", true);
	    result.put("cartCount", cartCount);
	    return result;
	}
}