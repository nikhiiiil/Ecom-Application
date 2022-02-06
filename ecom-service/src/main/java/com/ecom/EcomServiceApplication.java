package com.ecom;

import java.util.stream.LongStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;

import com.ecom.DAO.PersonDAO;
import com.ecom.entity.Person;
import com.ecom.utils.Utils;

@SpringBootApplication
public class EcomServiceApplication implements ApplicationListener<ApplicationReadyEvent> {

	@Autowired
	private PersonDAO personDAOImpl;
	
	private final Logger LOGGER = LoggerFactory.getLogger(EcomServiceApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(EcomServiceApplication.class, args);
	}
	
	@Override
	public void onApplicationEvent(ApplicationReadyEvent event) {
		LOGGER.info("Application started and generating few Persons");
		LongStream.range(1,50).forEach(num -> {
			Person p = new Person();
			p.setId(num);
			p.setAge((int)num + 20);
			p.setName("person_"+num);
			p.setNumber(Utils.generateRamdonNumber());
			p = personDAOImpl.create(p);
		});
	}

}
