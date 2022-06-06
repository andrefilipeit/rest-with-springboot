package br.com.andrefilipeos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.andrefilipeos.data.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	//this interface provides all methods for Person's CRUD

	//implementing this interface using JPAQL
	@Query("SELECT u FROM User u WHERE u.userName = :username")
	User findByUsername(@Param("username") String userName);
	
}
