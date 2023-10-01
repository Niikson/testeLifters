package com.example.demo.event;

import java.util.UUID;

import org.springframework.context.ApplicationEvent;

import jakarta.servlet.http.HttpServletResponse;

public class RecursoCriadoEvent extends ApplicationEvent {

	private static final long serialVersionUID = 1L;

	private HttpServletResponse response;
	private UUID id;

	public RecursoCriadoEvent(Object source, HttpServletResponse response, UUID id) {
		super(source);
		this.response = response;
		this.id = id;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public UUID getId() {
		return id;
	}

}
