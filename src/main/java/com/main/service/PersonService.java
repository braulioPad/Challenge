package com.main.service;

import com.main.dto.PopularFollowerDTO;
import com.main.dto.ResponseDto;

public interface PersonService {

	public ResponseDto<String> findAllMessagesByPersonId(String personName,String word);
	
	public ResponseDto<PopularFollowerDTO> popularsFollower();
}
