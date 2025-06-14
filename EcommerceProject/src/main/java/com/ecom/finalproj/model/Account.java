package com.ecom.finalproj.model;

import java.beans.JavaBean;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "account")
@Getter
@Setter
public class Account {
	@Id
	@Column(name = "username")
	private String username;
	@Column(name = "password")
	private String password;

	@Column(name = "dob", nullable = false)
	private LocalDate dob;
	@Column(name = "email")
	private String email;
	@Column(name = "address")
	private String address;
	@Column(name = "role")
	private int role;

	public Account(String username, String password, LocalDate dob, String email, String address, int role) {
		super();
		this.username = username;
		this.password = password;
		this.dob = dob;
		this.email = email;
		this.address = address;
		this.role = role;
	}

	public Account() {

	}

	@Override
	public String toString() {
		return "Account [username=" + username + ", password=" + password + ", dob=" + dob + ", email=" + email
				+ ", address=" + address + ", role=" + role + "]";
	}

}