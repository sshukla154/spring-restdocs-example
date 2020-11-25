package frontier.learning.repositories;

import java.util.UUID;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import frontier.learning.domain.Beer;

@Repository
public interface BeerRepository extends PagingAndSortingRepository<Beer, UUID> {

}
