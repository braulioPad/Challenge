package com.main.service.impl;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.entity.Message;
import com.main.entity.Person;
import com.main.repository.PersonRepository;
import com.main.service.PersonService;

@Service
public class PersonServiceImpl implements PersonService {

	@Autowired
	private PersonRepository personRepository;
	
	
	 public List<Message> findAllMessagesByPersonId(Long personId,String word) {
	        // First, find the person by their ID
	        Person person = personRepository.findById(personId).orElse(null);

	        // If the person is found, retrieve all messages from that person
	        if (person != null) {
	            return personRepository.findAllMessagesByPersonIdAndSearchWord(personId,word);
	        }

	        // Return an empty list if the person is not found
	        return Collections.emptyList();
	    }
	
	
}
