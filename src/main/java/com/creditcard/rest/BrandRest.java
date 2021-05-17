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

import com.creditcard.dao.BrandDAO;
import com.creditcard.model.Brand;

@RestController
@RequestMapping("brand")
public class BrandRest {

	@Autowired
	private BrandDAO brandDao;
	
	@PostMapping("/crear") //localhost:8080/brand/crear
	public ResponseEntity<Brand> createBrand(@RequestBody Brand brand) {
		Brand newBrand= brandDao.save(brand);
		return ResponseEntity.ok(newBrand);
	}
	
	 @GetMapping("/listar")
	 public ResponseEntity<List<Brand>> listar(){
		 List<Brand> brands = brandDao.findAll();
		 return ResponseEntity.ok(brands);
     }
	
	 @DeleteMapping("/eliminar/{id}")
	 public ResponseEntity<Void> deleteById(@PathVariable("id") Long id ) {
		 brandDao.deleteById(id);
		 return ResponseEntity.ok(null);
	 }
	 
	 @PutMapping("/actualizar")
	 public ResponseEntity<Brand> updateBrand(@RequestBody Brand brand) {
		Optional<Brand> optionalB = brandDao.findById(brand.getId());
		if (optionalB.isPresent()) {
			Brand updateB = optionalB.get();
			updateB.setName(brand.getName());
			brandDao.save(updateB);
			return ResponseEntity.ok(updateB);
		}
		return ResponseEntity.noContent().build();	 
	}
	 
	 @RequestMapping(value="{brandId}") // localhost:8080/brand/{brandId}
	 public ResponseEntity<Brand> findById(@PathVariable("brandId") Long brandId){
		 Optional<Brand> brand = brandDao.findById(brandId);
		 if(brand.isPresent()) {
			 return ResponseEntity.ok(brand.get());
		 }
		 return ResponseEntity.noContent().build();
	 }
}
