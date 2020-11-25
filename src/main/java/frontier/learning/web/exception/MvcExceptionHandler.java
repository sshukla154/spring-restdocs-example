package frontier.learning.web.exception;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/*This exception handler will be by-default added to all the controller class and will be used where-ever required.*/

@ControllerAdvice
public class MvcExceptionHandler {

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<List> validateErrorHandler(ConstraintViolationException e) {
		List<String> errors = new ArrayList<String>(e.getConstraintViolations().size());
		Consumer<ConstraintViolation> consumerAction = new Consumer<ConstraintViolation>() {

			@Override
			public void accept(ConstraintViolation constraintViolation) {
				errors.add(constraintViolation.getPropertyPath() + ":" + constraintViolation.getMessage());
			}
		};
		e.getConstraintViolations().forEach(consumerAction);
		return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(BindException.class)
	public ResponseEntity<List> bindExceptionHandler(BindException bindException) {
		return new ResponseEntity<>(bindException.getAllErrors(), HttpStatus.BAD_REQUEST);
	}

}
