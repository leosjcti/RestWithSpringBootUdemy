package br.com.leonardo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.leonardo.data.vo.PersonVO;
import br.com.leonardo.data.vo.v2.PersonVOV2;
import br.com.leonardo.services.PersonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Person Endpoint", description = "Description for Person", tags = {"PersonEndPoint"})
@RestController
@RequestMapping("/api/person/v1")
@CrossOrigin()
public class PersonController {
	
	@Autowired
	PersonService service;
	
	@GetMapping(produces = { "application/json", "application/xml", "application/x-yaml" })
	@ApiOperation(value = "Find All People")
	public List<PersonVO> findAll() {
		return service.findAll();		
	}
	
	@GetMapping(value= "/{id}", produces = { "application/json", "application/xml", "application/x-yaml" })
	public PersonVO findById(@PathVariable("id") Long id) {
		return service.findById(id);
	}
	
	@PostMapping(produces = { "application/json", "application/xml" }, consumes = { "application/json", "application/xml" })
	public PersonVO create(@RequestBody br.com.leonardo.data.vo.PersonVO PersonVO ) {
		return service.create(PersonVO);		
	}
	
	@PostMapping("/v2")
	public PersonVOV2 createV2(@RequestBody PersonVOV2 PersonVO ) {
		return service.createV2(PersonVO);		
	}
	
	@PutMapping(produces = { "application/json", "application/xml" }, consumes = { "application/json", "application/xml" })
	public PersonVO update(@RequestBody PersonVO PersonVO ) {
		return service.update(PersonVO);		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable(value="id") Long id ) {
		service.delete(id);
		return ResponseEntity.ok().build();
	}
	
}
