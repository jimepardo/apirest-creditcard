package com.creditcard.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.creditcard.model.Person;

public interface PersonDAO extends JpaRepository<Person, Long> {

}
