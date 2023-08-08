package com.main.service;

import java.util.List;

import com.main.entity.Message;

public interface PersonService {

	public List<Message> findAllMessagesByPersonId(Long personId,String word);
}
