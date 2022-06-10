package br.com.andrefilipeos.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.andrefilipeos.converter.DozerMapperConverter;
import br.com.andrefilipeos.converter.PersonConverter;
import br.com.andrefilipeos.data.model.Person;
import br.com.andrefilipeos.data.vo.PersonVO;
import br.com.andrefilipeos.data.vo.v2.PersonVOV2;
import br.com.andrefilipeos.exception.ResourceNotFoundException;
import br.com.andrefilipeos.repository.PersonRepository;

//Tells spring that the class is injected into every project to avoid instantiation works with @Autowired
@Service
public class PersonServices {

	@Autowired
	PersonRepository repository;
	
	@Autowired
	PersonConverter converter;

	public PersonVO create(PersonVO person) {
		
		var entity = DozerMapperConverter.parseObject(person, Person.class);
		var vo = DozerMapperConverter.parseObject(repository.save(entity), PersonVO.class);
		
		return vo;
	}
	
	public PersonVOV2 createV2(PersonVOV2 person) {
		
		var entity = converter.convertVOV2ToEntity(person);
		var vo = converter.convertEntityToVOV2(repository.save(entity));
		
		return vo;
	}

	public PersonVO update(PersonVO person) {

		Person entity = repository.findById(person.getKey())
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this id!"));

		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setAddress(person.getAddress());
		entity.setGender(person.getGender());

		return DozerMapperConverter.parseObject(repository.save(entity), PersonVO.class);
	}

	public void delete(Long id) {
		Person entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this id!"));
		
		repository.delete(entity);
	}

	// Returning a PersonVO mock
	public PersonVO findById(Long id) {
		
		var entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this id!"));
		
		return DozerMapperConverter.parseObject(entity, PersonVO.class);
		
	}
	

	@Transactional
	public PersonVO disablePerson(Long id) {
		repository.disablePerson(id); //Use JPQL
		var entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this id!"));
		
		return DozerMapperConverter.parseObject(entity, PersonVO.class);
		
	}

	public Page<PersonVO> findAll(Pageable pageable) {
		var entities = repository.findAll(pageable); //Return http request with pagination
		return entities.map(this::convertToPersonVO); 
	}
	
	public Page<PersonVO> findPersonByName(String firstName, Pageable pageable) {
		var entities = repository.findPersonByName(firstName, pageable); //Return http request with pagination and filter name
		return entities.map(this::convertToPersonVO); 
	}
	
	private PersonVO convertToPersonVO(Person entity) {
		return DozerMapperConverter.parseObject(entity, PersonVO.class);
	}

}
