package com.ecom.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ecom.DAO.PersonDAO;
import com.ecom.entity.Person;

@Controller
public class PersonController {
	
	@Autowired
	private PersonDAO personDAOImpl;
	
	@PostMapping(value = "/persons")
	public ResponseEntity<Person> createPerson(@RequestBody Person person){
		Person p = personDAOImpl.create(person);
		if(p==null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(p, HttpStatus.CREATED);
	}

	@GetMapping(value = "/persons/{id}")
	public ResponseEntity<Person> getPerson(@PathVariable Long id){
		Optional<Person> optional = personDAOImpl.getPerson(id);
		if(optional.isPresent()) {
			return new ResponseEntity<>(optional.get(), HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@GetMapping(value = "/persons")
	public ResponseEntity<List<Person>> getAllPersons(){
		return new ResponseEntity<>(personDAOImpl.findAllPersons(), HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/persons/{id}")
	public ResponseEntity<HttpStatus> deletePerson(@PathVariable Long id){
		personDAOImpl.deletePerson(id);
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}
	
	@PutMapping(value = "/persons/{id}")
	public ResponseEntity<Person> updatePerson(@Valid @RequestBody Person person){
		return new ResponseEntity<>(personDAOImpl.updatePerson(person), HttpStatus.OK);
	}
}
