package com.main.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
public class ResponseDto<T> {
	private int status;
	private String message;
	@JsonInclude(Include.NON_NULL)
	private List<T> data;
}
