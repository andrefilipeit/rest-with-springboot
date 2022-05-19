package br.com.andrefilipeos.controllers;

import java.util.List;

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

import br.com.andrefilipeos.data.vo.PersonVO;
import br.com.andrefilipeos.data.vo.v2.PersonVOV2;
import br.com.andrefilipeos.services.PersonServices;

@RestController
@RequestMapping("/person") // Main path endpoint
public class PersonController {

	@Autowired // this encapsules the same of new Object() instances
	private PersonServices services;

	
	@GetMapping
	public List<PersonVO> findAll() throws Exception {
		return services.findAll();
	}
	
	@GetMapping(value = "/{id}")
	public PersonVO findById(@PathVariable("id") Long id) throws Exception {
		return services.findById(id);
	}
	
	@PostMapping
	public PersonVO create(@RequestBody PersonVO person) throws Exception {
		return services.create(person);
	}
	
	@PostMapping("/v2")
	public PersonVOV2 createV2(@RequestBody PersonVOV2 person) throws Exception {
		return services.createV2(person);
	}
	
	@PutMapping
	public PersonVO update(@RequestBody PersonVO person) throws Exception {
		return services.update(person);
	}
	
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) throws Exception {
		services.delete(id);
		return ResponseEntity.ok().build();
	}
	

}