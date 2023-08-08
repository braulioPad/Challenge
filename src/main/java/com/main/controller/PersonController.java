package com.main.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.main.dto.ResponseDto;
import com.main.service.PersonService;

@RestController
public class PersonController {
	
	private PersonService personService;
	
	public PersonController(PersonService personService) {
		this.personService = personService;
	}

	@RequestMapping(value = { "/search", "/search/{word}" }, method = RequestMethod.GET)
	public ResponseEntity<ResponseDto<String>> MessagesByPerson(@RequestHeader("user_id") long userId,
			@PathVariable(required = false) String word) {
		ResponseDto<String> response = personService.findAllMessagesByPersonId(userId, word);
		return ResponseEntity.ok(response);
	}

}
