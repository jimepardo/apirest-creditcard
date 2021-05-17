package com.creditcard.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.creditcard.dao.CreditcardDAO;
import com.creditcard.dao.PersonDAO;
import com.creditcard.model.Creditcard;
import com.creditcard.model.Person;

@RestController
@RequestMapping("creditcard")
public class CreditcardRest {

	@Autowired
	private CreditcardDAO creditcardDao;
	
	@Autowired
	private PersonDAO personDao;

	@PostMapping("/crear")
	public ResponseEntity<Creditcard> createCreditcard(@RequestBody Creditcard card) {
		System.out.println("HOLAAAAAAAAAAAAAAA");
		Optional<Person> opPerson = personDao.findById(card.getPerson().getId());
		Person person = opPerson.get();
		person.addCreditcard(card);
		System.out.println("chauuuuuuuuuuu");
		personDao.save(person);
	//	Creditcard newCard = creditcardDao.save(card);
		return ResponseEntity.ok(card);
	}

	@GetMapping("/listar")
	public ResponseEntity<List<Creditcard>> listar() {
		List<Creditcard> cards = creditcardDao.findAll();
		return ResponseEntity.ok(cards);
	}

	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable("id") Long id) {
		creditcardDao.deleteById(id);
		return ResponseEntity.ok(null);
	}

	@PutMapping("/actualizar")
	public ResponseEntity<Creditcard> updateCreditcard(@RequestBody Creditcard card) {
		Optional<Creditcard> optionalCard = creditcardDao.findById(card.getId());
		if (optionalCard.isPresent()) {
			Creditcard updateCredit = optionalCard.get();
			updateCredit.setBrand(card.getBrand());
			updateCredit.setCard_number(card.getCard_number());
			updateCredit.setCardholder(card.getCardholder());
			updateCredit.setExpiration_date(card.getExpiration_date());
			updateCredit.setPerson(card.getPerson());
			creditcardDao.save(updateCredit);
			return ResponseEntity.ok(updateCredit);
		}
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "{cardId}") // localhost:8080/creditcard/{cardId}
	public ResponseEntity<Creditcard> findById(@PathVariable("cardId") Long cardId) {
		Optional<Creditcard> card = creditcardDao.findById(cardId);
		if (card.isPresent()) {
			return ResponseEntity.ok(card.get());
		}
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value="/validate/{cardId}") // localhost:8080/creditcard/validate/{cardId}
	 public ResponseEntity<String> validate(@PathVariable("cardId") Long cardId){
		  try {
			  Optional<Creditcard> card = creditcardDao.findById(cardId);
			  if(card.isPresent()) {
				  Creditcard c = card.get();
				  if( c.validateCard()) {
					  return new ResponseEntity<String>(("Tarjeta valida"),HttpStatus.OK);
				  }
				  else {
					  return new ResponseEntity<String>(("Tarjeta no valida"),HttpStatus.OK);
				  }	
			}else {
				return new ResponseEntity<String>(("Tarjeta no encontrada"),HttpStatus.NOT_FOUND);
			}
		}catch (RuntimeException e) {
			System.out.println("Error"+ e);
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
	}

}
