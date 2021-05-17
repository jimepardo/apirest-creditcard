package com.creditcard.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.creditcard.model.Creditcard;

public interface CreditcardDAO extends JpaRepository<Creditcard, Long> {

}
