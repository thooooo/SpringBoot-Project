package fr.efrei.pokemon_tcg.controllers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex,
			HttpHeaders headers,
			HttpStatusCode status,
			WebRequest request) {

		BindingResult bindingResult = ex.getBindingResult();
		List<Map<String, String>> errors = bindingResult.getFieldErrors()
				.stream()
				.map(fieldError -> {
					Map<String, String> response = new HashMap<>();
					response.put("message", fieldError.getDefaultMessage());
					response.put("field", fieldError.getField());
					response.put("value", fieldError.getRejectedValue().toString());
					return response;
				})
				.toList();
		return handleExceptionInternal(ex, errors, new HttpHeaders(), BAD_REQUEST, request);
	}
	// DataIntegrityViolationException --> HTTP 409
}
