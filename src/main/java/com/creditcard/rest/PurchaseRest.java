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

import com.creditcard.dao.BrandDAO;
import com.creditcard.dao.CreditcardDAO;
import com.creditcard.dao.PersonDAO;
import com.creditcard.dao.PurchaseDAO;
import com.creditcard.model.Brand;
import com.creditcard.model.Creditcard;
import com.creditcard.model.Purchase;

@RestController
@RequestMapping("purchase")
public class PurchaseRest {

	@Autowired
	private PurchaseDAO purchaseDao;

	@Autowired
	private PersonDAO personDao;
	
	@Autowired
	private CreditcardDAO creditcardDao;
	
	@Autowired
	private BrandDAO brandDao;

	@GetMapping("/listar")
	public ResponseEntity<List<Purchase>> listar() {
		List<Purchase> p = purchaseDao.findAll();
		return ResponseEntity.ok(p);
	}

	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable("id") Long id) {
		purchaseDao.deleteById(id);
		return ResponseEntity.ok(null);
	}

	@PutMapping("/actualizar")
	public ResponseEntity<Purchase> updatePurchase(@RequestBody Purchase p) {
		Optional<Purchase> optionalP = purchaseDao.findById(p.getId());
		if (optionalP.isPresent()) {
			Purchase updateP = optionalP.get();
			updateP.setCreditcard(p.getCreditcard());
//			updateP.setPerson_id(p.getPerson_id());
			updateP.setPrice(p.getPrice());
			updateP.setProduct_name(p.getProduct_name());
			purchaseDao.save(updateP);
			return ResponseEntity.ok(updateP);
		}
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "{pId}") // localhost:8080/purchase/{pId}
	public ResponseEntity<Purchase> findById(@PathVariable("pId") Long pId) {
		Optional<Purchase> p = purchaseDao.findById(pId);
		if (p.isPresent()) {
			return ResponseEntity.ok(p.get());
		}
		return ResponseEntity.noContent().build();
	}

	@PostMapping("/crear")
	public ResponseEntity<String> createPurchase(@RequestBody Purchase p) {
		try {
			if (p.validatePricePurchase(p.getPrice())) {
				purchaseDao.save(p);
				return new ResponseEntity<String>(("Compra registrada"), HttpStatus.OK);
			} else {
				return new ResponseEntity<String>(("La compra no se registro, el precio no es valido"), HttpStatus.OK);
			}
		} catch (RuntimeException e) {
			System.out.println("Error" + e);
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
	}

//	@RequestMapping(value="/validate/{pId}") // localhost:8080/purchase/validate/{pId}
//	 public ResponseEntity<String> getOperation(@PathVariable("pId") Long pId){
//		  try {
//			  Optional<Purchase> p = purchaseDao.findById(pId);
//			  
//			  if(p.isPresent()) {
//				  Purchase pu = p.get();
//				  Creditcard card = creditcardDao.findById(pu.getCreditcard_id()); 
//				  Brand b = brandDao.findById(card.getBrand_type_id());
//				  
//				
//				  
//				  if( c.validateCard()) {
//					  return new ResponseEntity<String>(("Tarjeta valida"),HttpStatus.OK);
//				  }
//				  else {
//					  return new ResponseEntity<String>(("Tarjeta no valida"),HttpStatus.OK);
//				  }	
//			}else {
//				return new ResponseEntity<String>(("Tarjeta no encontrada"),HttpStatus.NOT_FOUND);
//			}
//		}catch (RuntimeException e) {
//			System.out.println("Error"+ e);
//			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
//		}
//	}
}
