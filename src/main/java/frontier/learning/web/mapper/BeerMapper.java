package frontier.learning.web.mapper;

import org.mapstruct.Mapper;

import frontier.learning.domain.Beer;
import frontier.learning.web.model.BeerDTO;

@Mapper(uses = {DateMapper.class})
public interface BeerMapper {

	BeerDTO beerToBeerDTO(Beer beer);

	Beer beerDTOToBeer(BeerDTO beerDTO);

}
