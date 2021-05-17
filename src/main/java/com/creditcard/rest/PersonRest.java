package com.creditcard.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.creditcard.dao.PersonDAO;
import com.creditcard.model.Person;


@RestController
@RequestMapping("person")
public class PersonRest {

	@Autowired
	private PersonDAO personDao;
	
	@PostMapping("/crear")
	public ResponseEntity<Person> createPerson(@RequestBody Person person) {
		Person newPer = personDao.save(person);
		return ResponseEntity.ok(newPer);
	}
	
	 @GetMapping("/listar")
	 public ResponseEntity<List<Person>> listar(){
		 List<Person> persons = personDao.findAll();
		 return ResponseEntity.ok(persons);
     }
	
	 @DeleteMapping("/eliminar/{id}")
	 public ResponseEntity<Void> deleteById(@PathVariable("id") Long id ) {
		 personDao.deleteById(id);
		 return ResponseEntity.ok(null);
	 }
	 
	 @PutMapping("/actualizar")
	 public ResponseEntity<Person> actualizar(@RequestBody Person person) {
		 Optional<Person> optionalP = personDao.findById(person.getId());
		 if (optionalP.isPresent()) {
			 Person updateP = optionalP.get();
			 updateP.setName(person.getName());
			 updateP.setLastname(person.getLastname());
			 personDao.save(person);
			 return ResponseEntity.ok(updateP);
		 }
		 return ResponseEntity.noContent().build();
	 }
	 
	 @RequestMapping(value="{personId}") // localhost:8080/person/{personId}
	 public ResponseEntity<Person> findById(@PathVariable("personId") Long personId){
		 Optional<Person> person = personDao.findById(personId);
		 if(person.isPresent()) {
			 return ResponseEntity.ok(person.get());
		 }
		 return ResponseEntity.noContent().build();
	 }
}
