package com.main.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.main.dto.ResponseDto;
import com.main.service.PersonService;

@RestController
public class MessageController {

	private PersonService personService;

	public MessageController(PersonService personService) {
		this.personService = personService;
	}

	@GetMapping(value = { "/search", "/search/{word}" })
	public ResponseEntity<ResponseDto<String>> MessagesByPerson(@PathVariable(required = false) String word) {

		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = "";
		if (principal instanceof UserDetails) {
			username = ((UserDetails) principal).getUsername();
		} else {
			username = principal.toString();
		}
		ResponseDto<String> response = personService.findAllMessagesByPersonId(username, word);
		return ResponseEntity.ok(response);
	}
}
