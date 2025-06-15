package com.ecom.finalproj.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecom.finalproj.model.Cart;
import com.ecom.finalproj.respository.CartRespository;

@Service
@Transactional
public class CartService {

	@Autowired
	CartRespository cartRespository;

	public List<Cart> getCartByUsername(String username) {

		return cartRespository.findByUsername(username);
	}

	public int sumCart(String username) {
		int result = 0;
		List<Cart> list = getCartByUsername(username);
		for (Cart cart : list) {
			result += 1;
		}
		return result;
	}

	public void save(Cart newCart) {
		// TODO Auto-generated method stub
		cartRespository.save(newCart);
	}

	public void updateSoLuong(Long id_cart, int amount) {
		// TODO Auto-generated method stub
		cartRespository.update(id_cart, amount);
	}

	public boolean contain(List<Cart> carts, int productID) {
		for (Cart cart : carts) {
			if (cart.getProductID() == productID)
				return true;
		}
		return false;
	}

	public void deleteCart(Long idCart) {
		// TODO Auto-generated method stub
		cartRespository.deleteById(idCart);
	}

	public void increaseCart(Long idCart, int soluong) {
		// TODO Auto-generated method stub
		cartRespository.increaseCart(idCart, soluong);
	}
}
