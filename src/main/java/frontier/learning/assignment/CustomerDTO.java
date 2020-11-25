package frontier.learning.assignment;

import java.util.UUID;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerDTO {

	@Null
	private UUID id;

	/*
	 * Cannot use @Min and @Max because it is used for number and we have String for
	 * name. So we need Size
	 */
	@NotNull
	@Size(min = 3, max = 100)
	private String name;
}
