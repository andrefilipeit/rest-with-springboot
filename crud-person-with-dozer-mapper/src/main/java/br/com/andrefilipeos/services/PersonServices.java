package br.com.andrefilipeos.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

		Person entity = repository.findById(person.getId())
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

	public List<PersonVO> findAll() {
		
		return DozerMapperConverter.parseListObjects(repository.findAll(), PersonVO.class);
	}

}
