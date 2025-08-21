package com.bingo.api.bingo_manager.exception;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleException(Exception ex){
		ErrorResponse erro = new ErrorResponse(
				HttpStatus.INTERNAL_SERVER_ERROR.value(), 
				ex.getMessage(),
				LocalDateTime.now());
		logger.error("Ocorreu um erro: ", ex);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(erro);
	}
	
	@ExceptionHandler(NoResourceFoundException.class)
	public ResponseEntity<ErrorResponse> handleNoResourceFoundException(NoResourceFoundException ex,HttpServletRequest request){
		String path = request.getRequestURI();
        String mensagem = "Rota " + path + " não encontrada";
		ErrorResponse erro = new ErrorResponse(
				HttpStatus.NOT_FOUND.value(), 
				mensagem,
				LocalDateTime.now());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(erro);
	}
	
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleEntityNotFoundException(EntityNotFoundException ex){
		ErrorResponse erro = new ErrorResponse(
				HttpStatus.NOT_FOUND.value(), 
				ex.getMessage(),
				LocalDateTime.now());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
	}
}
