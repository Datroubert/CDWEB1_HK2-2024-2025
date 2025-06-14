package com.ecom.finalproj.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "product")
@Getter
@Setter
public class Product {
	@Id
	@Column(name = "ProductID")
	int productID;
	@Column(name = "product_name")
	String productName;
	@Column(name = "categoryid")
	int categoryID;
	@Column(name = "Description")
	String description;
	@Column(name = "price")
	int price;
	@Column(name = "stock_quantity")
	int stockQuantity;
	@Column(name = "unit_price")
	String unit_price;
	@Column(name = "imgpath")
	String imgPath;
	@Column(name = "color")
	String color;

	public Product(int productID, String productName, int categoryID, String description, int price, int stockQuantity,
			String unit_price, String imgPath, String color) {

		this.productID = productID;
		this.productName = productName;
		this.categoryID = categoryID;
		this.description = description;
		this.price = price;
		this.stockQuantity = stockQuantity;
		this.unit_price = unit_price;
		this.imgPath = imgPath;
		this.color = color;
	}
	
	public Product() {}

	
	
}