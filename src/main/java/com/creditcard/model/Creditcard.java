package com.creditcard.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


import lombok.Data;

@Data
@Entity
@Table(name="creditcard")
public abstract class Creditcard {

	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Brand brand;
	    
	@Column(name="card_number")
	private Integer card_number;
	
	@Column(name="cardholder")
	private String cardholder;
	
	@Column(name="expiration_date")
	private Date expiration_date;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Person person; 
	
	@OneToMany(cascade= {CascadeType.ALL},
			mappedBy = "creditcard")
	private Set<Purchase> cards = new HashSet<Purchase>();
	
	public Boolean validateCard() {
		
		Date actualdate = new Date();
		return actualdate.before(expiration_date);
	}
		
	public abstract Double calculateRate();
	

	public Creditcard()
	{
		
	}
}
