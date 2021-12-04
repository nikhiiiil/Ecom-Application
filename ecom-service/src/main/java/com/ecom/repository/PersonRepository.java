package com.ecom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecom.entity.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long>{
	

}
