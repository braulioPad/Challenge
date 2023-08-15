package com.main.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.main.dto.FollowerDto;
import com.main.dto.ResponseDto;
import com.main.service.FollowerService;

@RestController
public class FollowerController {

	private FollowerService followerService;

	public FollowerController(FollowerService followerService) {
		this.followerService = followerService;
	}

	@GetMapping(value = { "/followers" })
	public ResponseEntity<ResponseDto<String>> followers(@AuthenticationPrincipal UserDetails userDetails) {
		String username = userDetails.getUsername();
		ResponseDto<String> response = followerService.allFollowers(username);
		return ResponseEntity.ok(response);
	}

	@PostMapping("/followers/add")
	public ResponseEntity<ResponseDto<String>> addFollower(@RequestBody FollowerDto request,
			@AuthenticationPrincipal UserDetails userDetails) {
		String username = userDetails.getUsername();
		ResponseDto<String> response = followerService.addFollower(request, username);

		return ResponseEntity.ok(response);
	}

	@PostMapping("/followers/remove")
	public ResponseEntity<ResponseDto<String>> removeFollower(@RequestBody FollowerDto request,@AuthenticationPrincipal UserDetails userDetails) {
		String username = userDetails.getUsername();
		ResponseDto<String> response = followerService.removeFollower(request, username);

		return ResponseEntity.ok(response);
	}
}
