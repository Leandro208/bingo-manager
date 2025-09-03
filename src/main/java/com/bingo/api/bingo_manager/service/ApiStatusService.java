package com.bingo.api.bingo_manager.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.bingo.api.bingo_manager.dto.ApiStatusDTO;
import com.bingo.api.bingo_manager.utils.NetworkUtils;

import jakarta.annotation.PostConstruct;

@Service
public class ApiStatusService {

	private ApiStatusDTO status;
	 
	@Autowired
    private Environment env;

    @PostConstruct
    public void init() {
        status = new ApiStatusDTO();
        status.setStarted(new Date());
        status.setAppName(env.getProperty("spring.application.name"));
        status.setHostName(NetworkUtils.getLocalName());
        status.setJavaVersion(System.getProperty("java.version"));
    }

    public ApiStatusDTO getStatus() {
        return status;
    }
}
