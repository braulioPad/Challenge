package com.main.service;

import com.main.dto.FollowerDto;
import com.main.dto.ResponseDto;

public interface FollowerService {

	public ResponseDto<String> allFollowers(String user);

	public ResponseDto<String> addFollower(FollowerDto req, String user);

	public ResponseDto<String> removeFollower(FollowerDto req, String user);
}
