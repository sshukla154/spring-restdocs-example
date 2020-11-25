package frontier.learning.service;

import java.util.UUID;

import frontier.learning.web.model.BeerDTO;

public interface BeerService {

	BeerDTO getBeerById(UUID beerId);

	BeerDTO createBeer(BeerDTO beerDTO);

	void updateBeerById(UUID beerId, BeerDTO beerDTO);

	void deleteById(UUID beerId);

}
