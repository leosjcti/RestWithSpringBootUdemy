package br.com.leonardo.services;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import br.com.leonardo.converter.DozerConverter;
import br.com.leonardo.data.model.Book;
import br.com.leonardo.data.vo.BookVO;
import br.com.leonardo.exception.ResourceNotFoundExcption;
import br.com.leonardo.repository.BookRepository;

@Service
public class BookService {
	
	@Autowired
	BookRepository repository;
	
	//Simula um ID novo no BD
	private final AtomicLong counter = new AtomicLong();
	
	public BookVO findById(Long id) {
		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		return DozerConverter.parseObject(entity, BookVO.class);
	}
	
	public List<BookVO> findAll() {
		return DozerConverter.parseListObjects(repository.findAll(), BookVO.class);
	}
	
	public BookVO create(BookVO book) {
		var entity = DozerConverter.parseObject(book, Book.class);
		var vo = DozerConverter.parseObject(repository.save(entity), BookVO.class);
		return vo;
	}
	
	
	public BookVO update(BookVO book) {
		var entity = repository.findById(book.getKey())
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		
		entity.setAuthor(book.getAuthor());
		entity.setLauchDate(book.getLauchDate());
		entity.setPrice(book.getPrice());
		entity.setTitle(book.getTitle());
		
		var vo = DozerConverter.parseObject(repository.save(entity), BookVO.class);
		return vo;
	}
	
	public void delete(Long id) {
		Book entity =  repository.findById(id).orElseThrow(()-> new ResourceNotFoundExcption("Registro não encontrado"));
		repository.delete(entity);
	}

}
