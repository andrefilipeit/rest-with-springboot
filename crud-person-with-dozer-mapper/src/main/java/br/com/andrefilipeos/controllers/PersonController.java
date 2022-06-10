package br.com.andrefilipeos.controllers;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.andrefilipeos.data.vo.PersonVO;
import br.com.andrefilipeos.data.vo.v2.PersonVOV2;
import br.com.andrefilipeos.services.PersonServices;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

//@CrossOrigin Here we can enable CORS with all endpoit of Person Controller with @CrossOrigin
@Api(value="Person Endpoints", description = "All endpoints for Person", tags = {"Person-endpoints"}) //Editting values from swagger UI
@RestController
@RequestMapping("/api/person/v1") // Main path endpoint
public class PersonController {

	@Autowired // this encapsules the same of new Object() instances
	private PersonServices services;
	
	@Autowired
	PagedResourcesAssembler<PersonVO> assembler;
	
	@ApiOperation(value = "Return all Persons recordeds in database") //Swagger endpoint description@ApiOperation(value = "Return all Books recordeds in database") //Swagger endpoint description
	@GetMapping(produces = { "application/json", "application/xml", "application/x-yaml" })
	public ResponseEntity<?> findAll(
			@RequestParam(value = "page", defaultValue = "0") int page, 
			@RequestParam(value = "limit", defaultValue = "12") int limit,
			@RequestParam(value = "direction", defaultValue = "asc") String direction) //SpringBoot params to configure pagination and HATEOAS to the application
					throws Exception { 
		
		var sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;
				
		Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "firstName")); //Sort return list by name 
		
		Page<PersonVO> persons = services.findAll(pageable);
		// Implements HATEOAS
		persons.stream().forEach(p -> {
			try {
				p.add(linkTo(methodOn(PersonController.class).findById(p.getKey())).withSelfRel());
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		return new ResponseEntity<>(this.assembler.toResource(persons), HttpStatus.OK);
	}
	
	@ApiOperation(value = "Return all Persons recordeds in database with name filtered") //Swagger endpoint description@ApiOperation(value = "Return all Books recordeds in database") //Swagger endpoint description
	@GetMapping(value = "/findPersonByName/{firstName}", produces = { "application/json", "application/xml", "application/x-yaml" })
	public ResponseEntity<?> findPersonByName(
			@PathVariable("firstName") String firstName,
			@RequestParam(value = "page", defaultValue = "0") int page, 
			@RequestParam(value = "limit", defaultValue = "12") int limit,
			@RequestParam(value = "direction", defaultValue = "asc") String direction) //SpringBoot params to configure pagination and HATEOAS to the application
					throws Exception { 
		
		var sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;
				
		Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "firstName")); //Sort return list by name 
		
		Page<PersonVO> persons = services.findPersonByName(firstName, pageable);
		// Implements HATEOAS
		persons.stream().forEach(p -> {
			try {
				p.add(linkTo(methodOn(PersonController.class).findById(p.getKey())).withSelfRel());
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		return new ResponseEntity<>(assembler.toResource(persons), HttpStatus.OK);
	}

	//@CrossOrigin(origins = "http://localhost:8080") Here we can enable CORS for this endpoit from Origin localhost:8080
	@ApiOperation(value = "Return Person recorded by id passed") //Swagger endpoint description
	@GetMapping(value = "/{id}", produces = { "application/json", "application/xml", "application/x-yaml" })
	public PersonVO findById(@PathVariable("id") Long id) throws Exception {
		PersonVO personVO = services.findById(id);
		// Implements HATEOAS
		personVO.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
		return personVO;
	}
	
	//@CrossOrigin(origins = {"http://localhost:8080", "https://github.com/andrefilipeit"}) Here we can enable CORS for this endpoit from Origin localhost:8080 *and* github/andrefilipeit
	@ApiOperation(value = "Record a Person in database") //Swagger endpoint description
	@PostMapping(produces = { "application/json", "application/xml", "application/x-yaml" }, consumes = {
			"application/json", "application/xml", "application/x-yaml" })
	public PersonVO create(@RequestBody PersonVO person) throws Exception {
		PersonVO personVO = services.create(person);
		// Implements HATEOAS
		personVO.add(linkTo(methodOn(PersonController.class).findById(personVO.getKey())).withSelfRel());
		return personVO;
	}

	@PostMapping("/v2") // Used to Endpoint-versioning
	public PersonVOV2 createV2(@RequestBody PersonVOV2 person) throws Exception {
		return services.createV2(person);
	}
	
	//@CrossOrigin(origins = "http://localhost:8080") Here we can enable CORS for this endpoit from Origin localhost:8080
	@ApiOperation(value = "Disable a specific Person by id passed") //Swagger endpoint description
	@PatchMapping(value = "/{id}", produces = { "application/json", "application/xml", "application/x-yaml" })
	public PersonVO disablePerson(@PathVariable("id") Long id) throws Exception {
		PersonVO personVO = services.disablePerson(id);
		// Implements HATEOAS
		personVO.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
		return personVO;
	}

	@ApiOperation(value = "Changes a person that already exists") //Swagger endpoint description
	@PutMapping(produces = { "application/json", "application/xml", "application/x-yaml" }, consumes = {
			"application/json", "application/xml", "application/x-yaml" })
	public PersonVO update(@RequestBody PersonVO person) throws Exception {
		PersonVO personVO = services.update(person);
		// Implements HATEOAS
		personVO.add(linkTo(methodOn(PersonController.class).findById(personVO.getKey())).withSelfRel());
		return personVO;

	}

	@ApiOperation(value = "Delete Person by id") //Swagger endpoint description
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) throws Exception {
		services.delete(id);
		return ResponseEntity.ok().build();
	}

}