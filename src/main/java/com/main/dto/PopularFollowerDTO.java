package com.main.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PopularFollowerDTO {
	private String userNamePerson;
	private String popularFollower;
	private long numberOfFollowers;
}
