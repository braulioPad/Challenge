package com.main.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.main.dto.FollowerDto;
import com.main.dto.ResponseDto;
import com.main.entity.Follower;
import com.main.entity.Person;
import com.main.repository.FollowerRepository;
import com.main.repository.PersonRepository;
import com.main.service.FollowerService;

@Service
public class FollowerSerivceImpl implements FollowerService {

	private FollowerRepository followerRepository;

	private PersonRepository personRepository;

	public FollowerSerivceImpl(FollowerRepository followerRepository, PersonRepository personRepository) {
		this.followerRepository = followerRepository;
		this.personRepository = personRepository;
	}

	public ResponseDto<String> allFollowers(String user) {

		ResponseDto<String> response = new ResponseDto<>();
		// First, find the person by their ID
		Person person = personRepository.findByName(user).orElse(null);
		if (null == person) {
			// Return an empty list if the person is not found
			response.setStatus(404);
			response.setMessage("No user found");
			response.setData(Collections.emptyList());
			return response;
		} else {
			// If the person is found, retrieve all messages from that person
			List<Person> result = followerRepository.findByFollowers(person.getId());
			if (!result.isEmpty()) {
				response.setStatus(200);
				response.setMessage("Data retrieved successfully");
				response.setData(result.stream().map(Person::getName).collect(Collectors.toList()));
				return response;
			} else {
				// return an empty list if the person doesn't have followers
				response.setStatus(200);
				response.setMessage("No followers");
				response.setData(Collections.emptyList());
				return response;
			}
		}
	}

	public ResponseDto<String> addFollower(FollowerDto req, String username) {
		ResponseDto<String> response = new ResponseDto<>();
		Person person = personRepository.findByName(username).orElse(null);
		Person followerPerson = personRepository.findByName(req.getFollower()).orElse(null);

		if (person != null && followerPerson != null) {
			Follower follower = new Follower();
			follower.setPerson(person);
			follower.setFollowerPerson(followerPerson);
			followerRepository.save(follower);
			response.setStatus(200);
			response.setMessage("Follower added successfully");
			return response;
		} else {
			response.setStatus(200);
			response.setMessage("Invalid person IDs");
			return response;
		}

	}

	public ResponseDto<String> removeFollower(FollowerDto req, String username) {

		ResponseDto<String> response = new ResponseDto<>();
		Person person = personRepository.findByName(username).orElse(null);
		Person followerPerson = personRepository.findByName(req.getFollower()).orElse(null);

		if (person != null && followerPerson != null) {
			Follower follower = followerRepository.findFollowerByPersonIdAndFollowerId(person.getId(),
					followerPerson.getId());
			followerRepository.delete(follower);
			response.setStatus(200);
			response.setMessage("Follower remove successfully");
			return response;
		} else {
			response.setStatus(200);
			response.setMessage("Invalid person IDs");
			return response;
		}

	}

}
