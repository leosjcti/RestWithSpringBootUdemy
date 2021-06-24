package br.com.leonardo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.leonardo.data.vo.BookVO;
import br.com.leonardo.services.BookService;

@RestController
@RequestMapping("/api/book/v1")
public class BookController {
	
	@Autowired
	BookService service;
	
	@GetMapping(produces = { "application/json", "application/xml", "application/x-yaml" })
	public List<BookVO> findAll() {
		return service.findAll();		
	}
	
	@GetMapping(value= "/{id}", produces = { "application/json", "application/xml", "application/x-yaml" })
	public BookVO findById(@PathVariable("id") Long id) {
		return service.findById(id);
	}
	
	@PostMapping(produces = { "application/json", "application/xml" }, consumes = { "application/json", "application/xml" })
	public BookVO create(@RequestBody br.com.leonardo.data.vo.BookVO BookVO ) {
		return service.create(BookVO);		
	}
	
	
	@PutMapping(produces = { "application/json", "application/xml" }, consumes = { "application/json", "application/xml" })
	public BookVO update(@RequestBody BookVO BookVO ) {
		return service.update(BookVO);		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable(value="id") Long id ) {
		service.delete(id);
		return ResponseEntity.ok().build();
	}
	
}
