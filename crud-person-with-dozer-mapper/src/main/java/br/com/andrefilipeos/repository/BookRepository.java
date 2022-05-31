package br.com.andrefilipeos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.andrefilipeos.data.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
	// this interface provides all methods for Person's CRUD

}