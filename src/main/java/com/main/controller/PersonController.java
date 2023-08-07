package com.main.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.main.entity.Message;

@RestController
public class PersonController {
	
	
	@GetMapping
	public List<Message> MessagesByPerson(){
		return null;
	}

}
