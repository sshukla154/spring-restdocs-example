package frontier.learning.repositories;

import java.util.UUID;

import org.springframework.data.repository.PagingAndSortingRepository;

import frontier.learning.domain.Beer;

public interface BeerRepository extends PagingAndSortingRepository<Beer, UUID> {

}
