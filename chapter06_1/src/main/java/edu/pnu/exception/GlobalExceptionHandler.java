package edu.pnu.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(BoardException.class)
	public String handleCustomException(BoardException exception, Model model) {
		model.addAttribute("exception", exception);
		return "/errors/boardError";
	}

	@ExceptionHandler(UserNameNotFoundException.class)
	public String handleUserNameNotFoundException(UserNameNotFoundException exception, Model model) {
		model.addAttribute("exception", exception);
		return "/errors/loginError";
	}

	@ExceptionHandler(IDExistException.class)
	public String handleIDExistException(IDExistException exception, Model model) {
		model.addAttribute("exception", exception);
		return "/errors/joinError";
	}

	@ExceptionHandler(Exception.class)
	public String handleException(Exception exception, Model model) {
		model.addAttribute("exception", exception);
		return "/errors/globalError";
	}
}
