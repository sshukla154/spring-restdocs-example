package frontier.learning.web.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/*This is the data model which will be shared by API clients*/

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BeerDTO {

	@Null
	private UUID id;

	@Null
	private Integer version;

	@Null
	private OffsetDateTime createDate;

	@Null
	private OffsetDateTime lastModifiedDate;

	@NotBlank
	@Size(min = 3, max = 100)
	private String beerName;

	@NotNull
	private BeerStyleEnum beerStyleName;

	@Positive
	@NotNull
	private Long upc;

	@Positive
	private Integer quantityOnHand;

	@Positive
	@NotNull
	private BigDecimal price;

}
