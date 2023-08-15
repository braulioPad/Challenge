package com.main.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.main.dto.PopularFollowerDTO;
import com.main.dto.ResponseDto;
import com.main.service.PersonService;

@RestController
public class PersonController {

	private PersonService personService;

	public PersonController(PersonService personService) {
		super();
		this.personService = personService;
	}

	@GetMapping("/users/popular-follower")
	public ResponseEntity<ResponseDto<PopularFollowerDTO>> getAllUsersWithMostPopularFollower() {
		ResponseDto<PopularFollowerDTO> response = personService.popularsFollower();
		return ResponseEntity.ok(response);
	}
}
