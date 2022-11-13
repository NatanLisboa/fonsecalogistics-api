package com.fonsecaworks.fonsecalogistics.api.exception;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
public class BadRequestException {

	private Integer status;
	private LocalDateTime dateTime;
	private String title;
	private List<InvalidField> fields;
		
	@Getter
	@AllArgsConstructor
	public static class InvalidField {
		
		private String name;
		private String message;
		
	}
	
}
