package com.creditcard.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="purchase")
public class Purchase {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(name="price")
	private Double price;
	
	@Column(name="product_name")
	private String product_name;
		
	@ManyToOne(fetch = FetchType.LAZY)
	private Creditcard creditcard;
	
	public Boolean validatePricePurchase (Double price) {
		return price < 1000;
	}
	
	public String getRateBrandPrice (Purchase p) {
		
		return null;
	}
	
	public Purchase() {}
}
