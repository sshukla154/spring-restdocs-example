package frontier.learning.service;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

import org.springframework.stereotype.Service;

import frontier.learning.web.model.BeerDTO;
import frontier.learning.web.model.BeerStyleEnum;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BeerServiceImpl implements BeerService {

	@Override
	public BeerDTO getBeerById(UUID beerId) {
		log.debug("Get a beer...");
		return BeerDTO.builder().id(UUID.randomUUID()).beerName("Galaxy Cat").beerStyleName(BeerStyleEnum.ALE)
				.version(123).lastModifiedDate(OffsetDateTime.now()).createDate(OffsetDateTime.now()).upc(1221340798L)
				.quantityOnHand(5).price(new BigDecimal(154.00)).build();
	}

	@Override
	public BeerDTO createBeer(BeerDTO beerDTO) {
		log.debug("Save a new beer...");
		return BeerDTO.builder().id(UUID.randomUUID()).beerName(beerDTO.getBeerName())
				.beerStyleName(beerDTO.getBeerStyleName()).version(beerDTO.getVersion())
				.lastModifiedDate(OffsetDateTime.now()).createDate(OffsetDateTime.now()).upc(beerDTO.getUpc())
				.quantityOnHand(beerDTO.getQuantityOnHand()).price(beerDTO.getPrice()).build();
	}

	@Override
	public void updateBeerById(UUID beerId, BeerDTO beerDTO) {
		log.debug("Updating a beer...");
		// TODO : Would add a real implementation to update beer
	}

	@Override
	public void deleteById(UUID beerId) {
		log.debug("Deleting a beer...");

	}
}
