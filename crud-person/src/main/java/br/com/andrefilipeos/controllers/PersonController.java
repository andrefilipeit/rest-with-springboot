package br.com.andrefilipeos.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.andrefilipeos.model.Person;
import br.com.andrefilipeos.services.PersonServices;

@RestController
@RequestMapping("/person") // Main path endpoint
public class PersonController {

	@Autowired // this encapsules the same of new Object() instances
	private PersonServices services;

	@GetMapping
	public List<Person> findAll() throws Exception {
		return services.findAll();
	}
	
	@GetMapping(value = "/{id}")
	public Person findById(@PathVariable("id") Long id) throws Exception {
		return services.findById(id);
	}
	
	@PostMapping
	public Person create(@RequestBody Person person) throws Exception {
		return services.create(person);
	}
	
	@PutMapping
	public Person update(@RequestBody Person person) throws Exception {
		return services.update(person);
	}
	
	@DeleteMapping(value="/{id}")
	public void delete(@PathVariable("id") Long id) throws Exception {
		services.delete(id);
	}
	

}