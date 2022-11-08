package com.fonsecaworks.fonsecalogistics.api.exception.handler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fonsecaworks.fonsecalogistics.api.exception.BadRequestException;

import lombok.AllArgsConstructor;

@ControllerAdvice
@AllArgsConstructor
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

	private MessageSource messageSource;
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		List<BadRequestException.InvalidField> invalidFields = new ArrayList<>();
		
		for (ObjectError error : ex.getAllErrors()) {
			
			String fieldName = ((FieldError) error).getField();
			String errorMessage = messageSource.getMessage(error, LocaleContextHolder.getLocale());
			
			invalidFields.add(new BadRequestException.InvalidField(fieldName, errorMessage));
			
		}
		
		BadRequestException badRequestException = new BadRequestException();
		badRequestException.setStatus(status.value());
		badRequestException.setDateTime(LocalDateTime.now());
		badRequestException.setTitle("One or more fields are invalid");
		badRequestException.setFields(invalidFields);
		
		return handleExceptionInternal(ex, badRequestException, headers, status, request);
	}
	
}
