package com.baev.todolist.controller.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * Utility controller class to handle exceptions.
 *
 * @author Maxim Baev
 */
@ControllerAdvice
public class GlobalExceptionHandler {

	static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler
	@ResponseStatus(HttpStatus.FORBIDDEN)
	@ResponseBody
	public Map<String, Object> handleCredentialException(BadCredentialsException exception) {
		log.error("BadCredentialsException exception", exception);
		return getErrorPage(exception);
	}

	@ExceptionHandler
	@ResponseStatus(HttpStatus.FORBIDDEN)
	@ResponseBody
	public Map<String, Object> handleAccessException(AccessDeniedException exception) {
		log.error("Access denied exception", exception);
		return getErrorPage(exception);
	}

	@ExceptionHandler
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ResponseBody
	public Map<String, Object> handleDeletedNoteException(EmptyResultDataAccessException exception) {
		log.error("Deleted note exception", exception);
		return getErrorPage(exception);
	}

	@ExceptionHandler
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public Map<String, Object> handleException(Exception exception) {
		log.error("Internal exception", exception);
		return getErrorPage(exception);
	}

	private Map<String, Object> getErrorPage(Exception exception) {
		Map<String, Object> res = new HashMap<>();
		Map<String, Object> errorMap = new HashMap<>();
		res.put("error", errorMap);

		errorMap.put("cause", exception.getCause());
		errorMap.put("message", exception.getMessage());
		errorMap.put("name", exception.getClass().getSimpleName());

		return res;
	}
}
