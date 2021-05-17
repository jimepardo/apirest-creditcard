package com.creditcard.model;


import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "person")
public class Person {
	
	 
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(name="name")
	private String name;
	@Column(name="lastname")
	private String lastname;
	
	@OneToMany(cascade= {CascadeType.ALL},
			mappedBy = "person")
	private Set<Creditcard> cards = new HashSet<Creditcard>();

	public void addCreditcard (Creditcard card) {
		this.cards.add(card);
	}
	
	public Person() {}
}
