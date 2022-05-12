package br.com.andrefilipeos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.andrefilipeos.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long>{
	//this interface provides all methods for Person's CRUD

}
