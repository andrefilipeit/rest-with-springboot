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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.andrefilipeos.data.vo.BookVO;
import br.com.andrefilipeos.services.BookServices;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="Book Endpoints", description = "All endpoints for Book", tags = {"Book-endpoints"}) //Editting values from swagger UI
@RestController
@RequestMapping("/api/book/v1") // Main path endpoint
public class BookController {

	@Autowired // this encapsules the same of new Object() instances
	private BookServices services;
	
	@Autowired
	PagedResourcesAssembler<BookVO> assembler;

	@ApiOperation(value = "Return all Books recordeds in database") //Swagger endpoint description
	@GetMapping(produces = { "application/json", "application/xml", "application/x-yaml" })
	public ResponseEntity<?> findAll(
			@RequestParam(value = "page", defaultValue = "0") int page, 
			@RequestParam(value = "limit", defaultValue = "12") int limit,
			@RequestParam(value = "direction", defaultValue = "asc") String direction)  //SpringBoot params to configure pagination and HATEOAS to the application
					throws Exception {
		
		var sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;
		
		Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "title")); //Sort return list by name
		
		Page<BookVO> books = services.findAll(pageable);
		// Implements HATEOAS
		books.stream().forEach(p -> {
			try {
				p.add(linkTo(methodOn(BookController.class).findById(p.getKey())).withSelfRel());
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		return new ResponseEntity<>(this.assembler.toResource(books), HttpStatus.OK);
	}
	
	@ApiOperation(value = "Return all Books recordeds in database with name filtered") //Swagger endpoint description
	@GetMapping(value = "/findBookByName/{bookName}", produces = { "application/json", "application/xml", "application/x-yaml" })
	public ResponseEntity<?> findBookByName(
			@PathVariable("bookName") String bookName,
			@RequestParam(value = "page", defaultValue = "0") int page, 
			@RequestParam(value = "limit", defaultValue = "12") int limit,
			@RequestParam(value = "direction", defaultValue = "asc") String direction)  //SpringBoot params to configure pagination and HATEOAS to the application
					throws Exception {
		
		var sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;
		
		Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "title")); //Sort return list by name
		
		Page<BookVO> books = services.findBookByName(bookName, pageable);
		// Implements HATEOAS
		books.stream().forEach(p -> {
			try {
				p.add(linkTo(methodOn(BookController.class).findById(p.getKey())).withSelfRel());
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		return new ResponseEntity<>(this.assembler.toResource(books), HttpStatus.OK);
	}

	@ApiOperation(value = "Return Book recorded by id passed") //Swagger endpoint description 
	@GetMapping(value = "/{id}", produces = { "application/json", "application/xml", "application/x-yaml" })
	public BookVO findById(@PathVariable("id") Long id) throws Exception {
		BookVO personVO = services.findById(id);
		// Implements HATEOAS
		personVO.add(linkTo(methodOn(BookController.class).findById(id)).withSelfRel());
		return personVO;
	}

	@ApiOperation(value = "Record a Book in database") //Swagger endpoint description
	@PostMapping(produces = { "application/json", "application/xml", "application/x-yaml" }, consumes = {
			"application/json", "application/xml", "application/x-yaml" })
	public BookVO create(@RequestBody BookVO person) throws Exception {
		BookVO personVO = services.create(person);
		// Implements HATEOAS
		personVO.add(linkTo(methodOn(BookController.class).findById(personVO.getKey())).withSelfRel());
		return personVO;
	}

	@ApiOperation(value = "Changes a Book that already exists") //Swagger endpoint description
	@PutMapping(produces = { "application/json", "application/xml", "application/x-yaml" }, consumes = {
			"application/json", "application/xml", "application/x-yaml" })
	public BookVO update(@RequestBody BookVO person) throws Exception {
		BookVO personVO = services.update(person);
		// Implements HATEOAS
		personVO.add(linkTo(methodOn(BookController.class).findById(personVO.getKey())).withSelfRel());
		return personVO;

	}

	@ApiOperation(value = "Delete Book by id") //Swagger endpoint description
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) throws Exception {
		services.delete(id);
		return ResponseEntity.ok().build();
	}

}