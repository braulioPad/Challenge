package com.main.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.dto.ResponseDto;
import com.main.entity.Message;
import com.main.entity.Person;
import com.main.repository.PersonRepository;
import com.main.service.PersonService;

@Service
public class PersonServiceImpl implements PersonService {

	@Autowired
	private PersonRepository personRepository;

	public ResponseDto<String> findAllMessagesByPersonId(Long personId, String word) {

		ResponseDto<String> response = new ResponseDto<>();

		// First, find the person by their ID
		Person person = personRepository.findById(personId).orElse(null);

		// If the person is found, retrieve all messages from that person
		if (person != null) {
			List<Message> result = personRepository.findAllMessagesByPersonIdAndSearchWord(personId, word);
			if (!result.isEmpty()) {
				response.setStatus(200);
				response.setMessage("Data retrieved successfully");
				response.setData(result.stream().map(Message::getContent).collect(Collectors.toList()));
				return response;
			} else {
				response.setStatus(204);
				response.setMessage("No Data found");
				response.setData(Collections.emptyList());
				return response;
			}
		}
		response.setStatus(204);
		response.setMessage("No user found");
		response.setData(Collections.emptyList());
		// Return an empty list if the person is not found
		return response;
	}

}
