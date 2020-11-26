package frontier.learning.web.controller;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import frontier.learning.repositories.BeerRepository;
import frontier.learning.web.mapper.BeerMapper;
import frontier.learning.web.model.BeerDTO;
import frontier.learning.web.model.BeerStyleEnum;
import lombok.RequiredArgsConstructor;

/*
 * @Validated - Its a Spring Framework annotation which performs validation on METHOD INPUT PARAMTERS 
 * 
 * Eg: @NotNUll in getBeerById()
 * Eg: @NOtNull in createBeer()
 */

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/beer")
public class BeerController {

    private final BeerRepository beerRepository;
    private final BeerMapper beerMapper;

	@GetMapping("/{beerId}")
	public ResponseEntity<BeerDTO> getBeerById(@PathVariable UUID beerId) {
		return new ResponseEntity<>(beerMapper.beerToBeerDTO(beerRepository.findById(beerId).get()), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<BeerDTO> createBeer(@Validated @RequestBody BeerDTO beerDTO) {
		 beerRepository.save(beerMapper.beerDTOToBeer(beerDTO));
		 return new ResponseEntity(HttpStatus.CREATED);
	}

	@PutMapping("/{beerId}")
	public ResponseEntity<BeerDTO> updateBeerById(@PathVariable UUID beerId, @Validated @RequestBody BeerDTO beerDTO) {
		beerRepository.findById(beerId).ifPresent(beer -> {
            beer.setBeerName(beerDTO.getBeerName());
            beer.setBeerStyle(beerDTO.getBeerStyleName().toString());
            beer.setPrice(beerDTO.getPrice());
            beer.setUpc(beerDTO.getUpc());

            beerRepository.save(beer);
        });
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	/*
	 * NOTE : (This validateExceptionhandler() has been moved to Advice class) This
	 * ExceptionHandler(), will be called from create/update beer API. When any
	 * exception is occurred in these API that will be cached by @Valid annotation
	 * which will internally call this API
	 */
//	@ExceptionHandler(ConstraintViolationException.class)
//	public ResponseEntity<List> validateErrorHandler(ConstraintViolationException e) {
//		List<String> errors = new ArrayList<String>(e.getConstraintViolations().size());
//		Consumer<ConstraintViolation> consumerAction = new Consumer<ConstraintViolation>() {
//
//			@Override
//			public void accept(ConstraintViolation constraintViolation) {
//				errors.add(constraintViolation.getPropertyPath() + ":" + constraintViolation.getMessage());
//			}
//		};
//		e.getConstraintViolations().forEach(consumerAction);
//		return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
//	}

}
