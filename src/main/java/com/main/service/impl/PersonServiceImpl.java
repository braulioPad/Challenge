package com.main.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.main.dto.PopularFollowerDTO;
import com.main.dto.ResponseDto;
import com.main.entity.Message;
import com.main.entity.Person;
import com.main.projection.PopularFollowerProjection;
import com.main.repository.FollowerRepository;
import com.main.repository.MessageRepository;
import com.main.repository.PersonRepository;
import com.main.service.PersonService;

@Service
public class PersonServiceImpl implements PersonService {

	private PersonRepository personRepository;

	private MessageRepository messageReporsitory;
	
	private FollowerRepository followerRepository;

	public PersonServiceImpl(PersonRepository personRepository, MessageRepository messageReporsitory,FollowerRepository followerRepository) {
		this.personRepository = personRepository;
		this.messageReporsitory = messageReporsitory;
		this.followerRepository = followerRepository;
	}

	public ResponseDto<String> findAllMessagesByPersonId(String personName, String word) {

		ResponseDto<String> response = new ResponseDto<>();

		// First, find the person by their ID
		Person person = personRepository.findByName(personName).orElse(null);

		// If the person is found, retrieve all messages from that person
		if (person != null) {
			List<Message> result = messageReporsitory.findAllMessagesByPersonIdAndSearchWord(person.getId(), word);
			if (!result.isEmpty()) {
				response.setStatus(200);
				response.setMessage("Data retrieved successfully");
				response.setData(result.stream().map(Message::getContent).collect(Collectors.toList()));
				return response;
			} else {
				response.setStatus(200);
				response.setMessage("No Data found");
				response.setData(Collections.emptyList());
				return response;
			}
		}
		response.setStatus(404);
		response.setMessage("No user found");
		// Return an empty list if the person is not found
		response.setData(Collections.emptyList());
		return response;
	}

	public ResponseDto<PopularFollowerDTO> popularsFollower() {
		ResponseDto<PopularFollowerDTO> response = new ResponseDto<>();
		List<PopularFollowerProjection> result = personRepository.findAllUsersWithMostPopularFollower();
		List<PopularFollowerDTO> followers= new ArrayList<>();
		for(int i=0;i<result.size();i++) {
			for(int j=0;j<result.size();j++) {
				if(i!=j) {
					if(followerRepository.findFollowerByPersonIdAndFollowerId(result.get(i).getPersonId(), result.get(j).getPersonId())!=null) {
						PopularFollowerDTO follower= new PopularFollowerDTO(result.get(i).getPersonName(), result.get(j).getPersonName(), result.get(i).getFollowerCount());
						followers.add(follower);
						break;
					}
				}
			}
		}
		response.setStatus(200);
		response.setMessage("Data retrieved successfully");
		response.setData(followers);
		return response;
	}
	
}
