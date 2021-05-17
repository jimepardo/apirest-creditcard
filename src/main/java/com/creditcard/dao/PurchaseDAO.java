package com.creditcard.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.creditcard.model.Purchase;

public interface PurchaseDAO extends JpaRepository<Purchase, Long>{

}
