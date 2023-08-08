package com.main.dto;

import java.util.List;

import lombok.Data;


@Data
public class ResponseDto<T>{
	
	
	private int status;
    private String message;
    private List<T> data;

}
