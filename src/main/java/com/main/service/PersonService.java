package com.main.service;

import com.main.dto.ResponseDto;

public interface PersonService {

	public ResponseDto<String> findAllMessagesByPersonId(Long personId,String word);
}
