package br.com.andrefilipeos.converter;

import java.util.Date;

import org.springframework.stereotype.Service;

import br.com.andrefilipeos.data.model.Person;
import br.com.andrefilipeos.data.vo.v2.PersonVOV2;

@Service
public class PersonConverter {
	
	public PersonVOV2 convertEntityToVOV2(Person person) {
		
		PersonVOV2 vo = new PersonVOV2();
		vo.setId(person.getId());
		vo.setAddress(person.getAddress());
		vo.setFirstName(person.getFirstName());
		vo.setLastName(person.getLastName());
		vo.setGender(person.getGender());
		vo.setAddress(person.getAddress());
		vo.setBirthDay(new Date());
		return vo;
	}
	
	public Person convertVOV2ToEntity(PersonVOV2 personV2) {
		
		Person entity = new Person();
		entity.setId(personV2.getId());
		entity.setAddress(personV2.getAddress());
		entity.setFirstName(personV2.getFirstName());
		entity.setLastName(personV2.getLastName());
		entity.setGender(personV2.getGender());
		entity.setAddress(personV2.getAddress());
		return entity;
	}

}
