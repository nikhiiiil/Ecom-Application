package com.ecom.DAO;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.entity.Person;
import com.ecom.repository.PersonRepository;

@Service
public class PersonDAO {

	@Autowired
	private PersonRepository personRepository;

	@Transactional
	public Person create(Person person) {
		return personRepository.save(person);
	}

	@Transactional
	public Optional<Person> getPerson(Long id) {
		return personRepository.findById(id);		
	}

	public List<Person> findAllPersons() {
		return personRepository.findAll();
	}

	public void deletePerson(Long id) {
		personRepository.deleteById(id);
	}
	
	public Person updatePerson(Person person) {
		return personRepository.save(person);
	}

}
