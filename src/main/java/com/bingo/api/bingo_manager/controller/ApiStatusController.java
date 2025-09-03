package com.bingo.api.bingo_manager.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bingo.api.bingo_manager.dto.ApiStatusDTO;
import com.bingo.api.bingo_manager.service.ApiStatusService;

@RestController
public class ApiStatusController {

	private final ApiStatusService statusService;
	
	public ApiStatusController(ApiStatusService statusService) {
		this.statusService = statusService;
	}

	@GetMapping("/api/status")
	public ApiStatusDTO ok() {
		return statusService.getStatus();
	}
}
