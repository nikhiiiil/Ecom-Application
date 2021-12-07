package com.ecom.controller;

import java.util.List;
import java.util.Optional;

import javax.print.attribute.standard.Media;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.DAO.PersonDAO;
import com.ecom.entity.Person;

@RestController
public class PersonController {
	
	@Autowired
	private PersonDAO personDAOImpl;

	private final Logger LOGGER = LoggerFactory.getLogger(PersonController.class);
	
	@PostMapping(value = "/persons", headers = {"content-type=application/json"}, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Person> createPerson(@Valid @RequestBody Person person) {
		LOGGER.info("Inside createPerson method from PersonController");
		Person p = personDAOImpl.create(person);
		if(p==null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(p, HttpStatus.CREATED);
	}

	@GetMapping(value = "/persons/{id}")
	public ResponseEntity<Person> getPerson(@PathVariable Long id){
		LOGGER.info("Inside getPerson method from PersonController");
		Optional<Person> optional = personDAOImpl.getPerson(id);
		if(optional.isPresent()) {
			return new ResponseEntity<>(optional.get(), HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@GetMapping(value = "/persons")
	public ResponseEntity<List<Person>> getAllPersons(){
		LOGGER.info("Inside getAllPersons method from PersonController");
		return new ResponseEntity<>(personDAOImpl.findAllPersons(), HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/persons/{id}")
	public ResponseEntity<HttpStatus> deletePerson(@PathVariable Long id){
		LOGGER.info("Inside deletePerson method from PersonController");
		personDAOImpl.deletePerson(id);
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}
	
	@PutMapping(value = "/persons/{id}")
	public ResponseEntity<Person> updatePerson(@Valid @RequestBody Person person){
		LOGGER.info("Inside updatePerson method from PersonController");
		return new ResponseEntity<>(personDAOImpl.updatePerson(person), HttpStatus.OK);
	}
}
