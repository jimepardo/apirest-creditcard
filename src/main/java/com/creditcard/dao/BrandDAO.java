package com.creditcard.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.creditcard.model.Brand;

public interface BrandDAO extends JpaRepository<Brand, Long>{

}
