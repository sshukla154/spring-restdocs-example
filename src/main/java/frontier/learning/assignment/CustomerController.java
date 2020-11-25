package frontier.learning.assignment;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/api/assignment/customer")
public class CustomerController {

	private final CustomerService customerService;

	public CustomerController(CustomerService customerService) {
		this.customerService = customerService;
	}

	@GetMapping("/{customerId}")
	public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable UUID customerId) {
		log.debug("CustomerController.getCustomerById()...");
		return new ResponseEntity<>(customerService.getCustomerById(customerId), HttpStatus.OK);
	}

	@PostMapping("/{customerId}")
	public ResponseEntity<CustomerDTO> createCustomer(@PathVariable UUID customerId,
			@Valid @RequestBody CustomerDTO customerDTO) {
		log.debug("CustomerController.getCustomerById()...");
		CustomerDTO createdCustomer = customerService.createCustomer(customerId, customerDTO);
		// return new ResponseEntity<>(createdCustomer, HttpStatus.CREATED);
		HttpHeaders httpHeaders = new HttpHeaders();
		// Add hostname to URL
		httpHeaders.add("Location", "/api/v1/beer" + createdCustomer.getId().toString());
		return new ResponseEntity<CustomerDTO>(httpHeaders, HttpStatus.CREATED);
	}

	@PutMapping("/{customerId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateCustomer(@PathVariable UUID customerId, @Valid @RequestBody CustomerDTO customerDTO) {
		log.debug("CustomerController.getCustomerById()...");
		customerService.updateCustomer(customerId, customerDTO);
	}

	@DeleteMapping("/{customerId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteCustomerById(@PathVariable UUID customerId) {
		log.debug("CustomerController.getCustomerById()...");
		customerService.deleteCustomerById(customerId);
	}

//	@ExceptionHandler(ConstraintViolationException.class)
//	ResponseEntity<List> exceptionHandler(ConstraintViolationException e) {
//		List<String> errors = new ArrayList<>(e.getConstraintViolations().size());
//		Consumer<ConstraintViolation> consumerAction = new Consumer<ConstraintViolation>() {
//
//			@Override
//			public void accept(ConstraintViolation constraintViolation) {
//				errors.add(constraintViolation.getPropertyPath() + " : " + constraintViolation.getMessage());
//			}
//		};
//		e.getConstraintViolations().forEach(consumerAction);
//		return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
//	}

}
