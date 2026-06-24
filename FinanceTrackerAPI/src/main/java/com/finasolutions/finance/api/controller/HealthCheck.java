package com.finasolutions.finance.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponents;

import com.finasolutions.finance.api.dto.HealthCheckResponse;

@RestController
@RequestMapping("/")
public class HealthCheck {
	
	@GetMapping
	public ResponseEntity<HealthCheckResponse> healthCheck() {
		UriComponents uriComponents 
			= ServletUriComponentsBuilder
				.fromCurrentRequest()
				.build();

		String hostname = uriComponents.getHost();
		final HealthCheckResponse response 
			= new HealthCheckResponse(hostname, "health endpoint");

		return ResponseEntity
			.status(200)
			.body(response); 
	}
}