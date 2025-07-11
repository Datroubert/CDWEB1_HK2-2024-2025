package com.ecom.finalproj.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ecom.finalproj.model.Account;
import com.ecom.finalproj.model.Cart;
import com.ecom.finalproj.service.CartService;
import com.ecom.finalproj.service.DiscountService;
import com.ecom.finalproj.service.MailService;

import jakarta.servlet.http.HttpSession;

@Controller
public class PayController {

	@Autowired
	CartService cartService;
	@Autowired
	DiscountService discountService;
	@Autowired
	MailService mailService;

	@GetMapping("/checkout")
	public String checkoutpage(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		List<Cart> carts = cartService.getCartByUsername(username);
		double TotalPrice = 0;
		int i = 0;
		for (Cart cart : carts) {
			TotalPrice += cart.getPrice() * cart.getAmount();
			i++;
		}
		System.out.println(i);
		model.addAttribute("ListCart", carts);
		model.addAttribute("TotalPrice", TotalPrice);

		return "checkout";
	}

	@PostMapping("/thanhtoan")
	public String payment(Model model, @RequestParam("mail") String mailTo) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		List<Cart> carts = cartService.getCartByUsername(username);
		double TotalPrice = 0;
		int i = 0;
		String content = "";

		for (Cart cart : carts) {
			TotalPrice += cart.getPrice() * cart.getAmount();
			content += cart.getProductName() + "\t" + "SL: " + cart.getAmount() + "\t" + "Đơn giá: " + cart.getPrice()
					+ "\t" + "Giá: " + cart.getAmount() * cart.getPrice() + "\n";
			i++;
		}
		content = content + "Tổng cộng: " + TotalPrice;
//		System.out.println(i);
		// 3. Gửi mail
		try {
			String subject = "Xác nhận đơn hàng " + username;
			mailService.sendEmail(mailTo, subject, content); // Hàm gửi mail bạn đã có
			model.addAttribute("mailSuccess", true); // Gửi biến báo hiệu thành công
		} catch (Exception e) {
			model.addAttribute("mailError", "Gửi mail thất bại: " + e.getMessage());
		}

		model.addAttribute("ListCart", carts);
		model.addAttribute("TotalPrice", TotalPrice);

		return "redirect:/checkout";
	}

	@GetMapping("/applyDiscount")
	public String applyDiscount(Model model, @RequestParam("code") String code) {
		int valueOfCode = 0;
		if (discountService.checkDiscount(code)) {
			valueOfCode = discountService.value(code);
		}
		model.addAttribute("ValueOfCode", valueOfCode);
		model.addAttribute(code, code);
		return "/checkout";
	}

}