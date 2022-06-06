package br.com.andrefilipeos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.andrefilipeos.data.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long>{
	//this interface provides all methods for Person's CRUD

	//implementing this interface with method using JPAQL
	@Modifying //this annotation is used to querys thats modify data
	@Query("UPDATE Person p SET p.enabled = false WHERE p.id =:id")
	void disablePerson(@Param("id") Long id);
	
}
