package br.com.andrefilipeos.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.andrefilipeos.data.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
	// this interface provides all methods for Person's CRUD

	
	@Query("SELECT b FROM Book b WHERE b.title LIKE LOWER(CONCAT ('%', :bookTitle, '%'))")
	Page<Book> findBookByName(@Param("bookTitle") String bookTittle, Pageable pageable);
	
}