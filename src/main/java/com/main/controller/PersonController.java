package com.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.main.entity.Message;
import com.main.service.PersonService;

@RestController
public class PersonController {
	@Autowired
	private PersonService personService;
	
	
	@RequestMapping(value = {"/search", "/search/{word}"}, method = RequestMethod.GET)
	public List<Message> MessagesByPerson(@RequestHeader("user_id") long userId,@RequestHeader("user_pass") long userpass,@PathVariable(required = false) String word){
		
		return personService.findAllMessagesByPersonId(userId, word);
	}

}
