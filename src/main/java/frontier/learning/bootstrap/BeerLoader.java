package frontier.learning.bootstrap;

import java.math.BigDecimal;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import frontier.learning.domain.Beer;
import frontier.learning.repositories.BeerRepository;

/* Using CommandLineRunner */

@Component
public class BeerLoader implements CommandLineRunner {

	private final BeerRepository beerRepository;

	public BeerLoader(BeerRepository beerRepository) {
		this.beerRepository = beerRepository;
	}

	@Override
	public void run(String... args) throws Exception {
		loadBeerObjects();
	}

	private void loadBeerObjects() {
		if (beerRepository.count() == 0) {
			beerRepository.save(Beer.builder()
					.beerName("Mango Bobs")
					.beerStyle("IPA")
					.quantityToBrew(200)
					.upc(1234321324L)
					.price(new BigDecimal(12.94))
					.build());
			beerRepository.save(Beer.builder()
					.beerName("Galaxy Cat")
					.beerStyle("PALE_ALE")
					.quantityToBrew(100)
					.upc(1234124L)
					.price(new BigDecimal(14.94))
					.build());
			beerRepository.save(Beer.builder()
					.beerName("Budwiser")
					.beerStyle("LAGER")
					.quantityToBrew(500)
					.upc(121234L)
					.price(new BigDecimal(29.94))
					.build());
			beerRepository.save(Beer.builder()
					.beerName("Tuborg")
					.beerStyle("PILSNER")
					.quantityToBrew(100)
					.upc(123321324L)
					.price(new BigDecimal(22.94))
					.build());
		}
		
		System.out.println("Loaded Beers (BeerLoader.loadBeerObjects()) : " + beerRepository.count());
	}

}
