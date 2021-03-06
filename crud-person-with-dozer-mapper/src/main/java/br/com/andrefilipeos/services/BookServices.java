package br.com.andrefilipeos.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.andrefilipeos.converter.DozerMapperConverter;
import br.com.andrefilipeos.data.model.Book;
import br.com.andrefilipeos.data.vo.BookVO;
import br.com.andrefilipeos.exception.ResourceNotFoundException;
import br.com.andrefilipeos.repository.BookRepository;

//Tells spring that the class is injected into every project to avoid instantiation works with @Autowired
@Service
public class BookServices {


	@Autowired
	BookRepository repository;
	
	public BookVO create(BookVO person) {
		
		var entity = DozerMapperConverter.parseObject(person, Book.class);
		var vo = DozerMapperConverter.parseObject(repository.save(entity), BookVO.class);
		
		return vo;
	}
	
	public BookVO update(BookVO book) {

		Book entity = repository.findById(book.getKey())
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this id!"));

		entity.setAuthor(book.getAuthor());
		entity.setLaunch_date(book.getLaunch_date());
		entity.setPrice(book.getPrice());
		entity.setTitle(book.getTitle());

		return DozerMapperConverter.parseObject(repository.save(entity), BookVO.class);
	}

	public void delete(Long id) {
		Book entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this id!"));
		
		repository.delete(entity);
	}

	// Returning a BookVO mock
	public BookVO findById(Long id) {
		
		var entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this id!"));
		
		return DozerMapperConverter.parseObject(entity, BookVO.class);
		
	}

	public Page<BookVO> findAll(Pageable pageable) {
		var books = repository.findAll(pageable); //Return http request with pagination
		return books.map(this::convertToBookVO);
	}
	
	public Page<BookVO> findBookByName(String bookName, Pageable pageable) {
		var books = repository.findBookByName(bookName, pageable); //Return http request with pagination and filter name
		return books.map(this::convertToBookVO); 
	}
	
	private BookVO convertToBookVO(Book book) {
		return DozerMapperConverter.parseObject(book, BookVO.class);
	}
	
}
