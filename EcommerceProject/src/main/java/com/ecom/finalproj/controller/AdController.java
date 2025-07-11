package com.ecom.finalproj.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ecom.finalproj.model.Account;
import com.ecom.finalproj.model.Category;
import com.ecom.finalproj.model.Discount;
import com.ecom.finalproj.model.Product;
import com.ecom.finalproj.service.AccountService;
import com.ecom.finalproj.service.CategoryService;
import com.ecom.finalproj.service.DiscountService;
import com.ecom.finalproj.service.ProductService;

@Controller
@RequestMapping(value = "/admin")
public class AdController {
	@Autowired
	private AccountService accountService;
	@Autowired
	private DiscountService discountService;
	@Autowired
	private ProductService productService;
	@Autowired
	private CategoryService categoryService;

	@RequestMapping
	public String admin(ModelMap model, @ModelAttribute("discount") Discount discount) {
		long totalAccount = accountService.totalAccount();
		List<Account> list = accountService.getAll();
		List<Discount> listDiscount = discountService.getAllDiscount();
		List<Product> ListProduct = productService.getAllProd();
//		List<Category> categories = categoryService.findAll();
		
//		model.addAttribute("ListCategory", categories);
		model.addAttribute("totalProduct", productService.totalProduct());
		model.addAttribute("ListProduct", ListProduct);
		model.addAttribute("ListDiscount", listDiscount);
		model.addAttribute("ListAccount", list);
		model.addAttribute("TotalAccount", totalAccount);
		return "admin";
	}

	@GetMapping("/all")
	@ResponseBody
	public List<Discount> getAllDiscounts() {
		return discountService.getAllDiscounts();
	}

	@GetMapping("/{id}")
	@ResponseBody
	public Discount getDiscountById(@PathVariable String id) {
		return discountService.getDiscountById(id).orElse(null);
	}

	@PostMapping
	@ResponseBody
	public Discount createDiscount(@RequestBody Discount discount) {
		return discountService.saveDiscount(discount);
	}

	@GetMapping(value = "them")
	public String createDiscount(@RequestParam("code") String code, @RequestParam("ValueOfCode") int value) {
		discountService.saveDiscount(code, value);
		return "redirect:/admin";
	}

	@GetMapping("/delete")
	public String deleteDiscount(@RequestParam("code") String code, RedirectAttributes redirectAttributes) {
		discountService.deleteDiscount(code);
		return "redirect:/admin";
	}

	@GetMapping("/xoataikhoan")
	public String deleteAccount(@RequestParam String username, RedirectAttributes redirectAttributes) {
		accountService.deleteAccount(username);
		return "redirect:/admin";
	}

	@PostMapping("/themsanpham")
	public String themSanPham(@RequestParam("tensanpham") 
	String tensanpham, 
	  @RequestParam("tensanpham") String tenSanPham,
      @RequestParam("phanloai") String phanLoai,
      @RequestParam("mota") String moTa,
      @RequestParam("gia") String gia,
      @RequestParam("soluongkho") String soLuongKho,
      @RequestParam("mau") String mau,
      @RequestParam("img") MultipartFile img,
      Model model,
      RedirectAttributes redirect
) {
	    if (img == null || img.isEmpty()) {
	        model.addAttribute("error", "Vui lòng chọn hình ảnh!");
	        model.addAttribute("ListCategory", categoryService.findAll());
	        return "admin";
	    }
	    String uploadDir = "C:/Users/My Laptop/SpringBoot-workspace/CDWEB1_HK2-2024-2025/EcommerceProject/src/main/resources/static/img/"; // Thư mục ngoài code
	    File uploadDirFile = new File(uploadDir);
	    if (!uploadDirFile.exists()) {
	        uploadDirFile.mkdirs();
	    }
	    String storedFileName = img.getOriginalFilename();
	    try {
	        Path path = Paths.get(uploadDir + storedFileName);
	        img.transferTo(path.toFile());
	    } catch (IOException e) {
	        e.printStackTrace();
	        model.addAttribute("error", "Upload hình ảnh thất bại!");
	        model.addAttribute("ListCategory", categoryService.findAll());
	        return "redirect:/admin";
	    }
	    Product product = new Product();
	    product.setProductName(tensanpham);
	    
	    product.setCategoryID(categoryService.findCategoryByName(phanLoai).getIdCategory());
	    product.setDescription(moTa);
	    product.setPrice(Integer.parseInt(gia));
	    product.setStockQuantity(Integer.parseInt(soLuongKho));
	    product.setColor(mau);
	    product.setUnit_price("VND");
	    product.setImgPath(storedFileName);
	    productService.save(product);
	    redirect.addFlashAttribute("success", "Thêm sản phẩm thành công!");
	    return "redirect:/admin";
	
    }
	
	@GetMapping("/setrole")
	public String setRole(@RequestParam String username, RedirectAttributes redirectAttributes) {
		Account acc = accountService.getAcc(username);
		switch (acc.getRole()) {
		case 1:
			acc.setRole(0);
			break;
		case 0:
			acc.setRole(1);
			break;
		default:
			break;
		}
		accountService.setRole(username, acc.getRole());
		return "redirect:/admin";
	}

	@GetMapping("/suamagiamgia")
	public String editValueDiscount(@RequestParam("code") String code, @RequestParam("ValueOfCode") int valueOfCode) {
		discountService.updateValue(code, valueOfCode);
		return "redirect:/admin";
	}
	@GetMapping("/xoasanpham")
	public String deleteProduct(@RequestParam("ProductID") int idProduct) {
		productService.deleteById(idProduct);
		return "redirect:/admin";
	}
}