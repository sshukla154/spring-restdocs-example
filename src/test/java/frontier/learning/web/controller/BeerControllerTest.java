package frontier.learning.web.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
/* import should be from MockMvcRequestBuilders instead of MockMvcRequestBuilders*/
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.put;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;
import static org.springframework.restdocs.snippet.Attributes.key;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.constraints.ConstraintDescriptions;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import frontier.learning.domain.Beer;
import frontier.learning.repositories.BeerRepository;
import frontier.learning.web.model.BeerDTO;
import frontier.learning.web.model.BeerStyleEnum;

/* TODO: In createBeer() getting error :
 * 
 * javax.validation.NoProviderFoundException: Unable to create a Configuration, 
 * because no Bean Validation provider could be found. Add a provider like
 * Hibernate Validator (RI) to your classpath.
 *  
 */

@ExtendWith(RestDocumentationExtension.class)
@AutoConfigureRestDocs
@WebMvcTest(BeerController.class)
@ComponentScan(basePackages = "frontier.learning")
public class BeerControllerTest {

	@MockBean
	BeerRepository beerRespository;

	@Autowired
	MockMvc mockMvc;

	@Autowired
	ObjectMapper objectmapper;

	/* Example : "Documenting Path Parameter" */
	@Test
	public void getBeerById() throws Exception {
		given(beerRespository.findById(any())).willReturn(Optional.of(Beer.builder().build()));

		mockMvc.perform(get("/api/v1/beer/{beerId}", UUID.randomUUID().toString()).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andDo(document("/v1/beer",
						pathParameters(parameterWithName("beerId").description("UUID if desired beer to get"))));
	}

	/* Example : "Documenting Query Parameter" */
	@Test
	public void getBeerById_1() throws Exception {
		given(beerRespository.findById(any())).willReturn(Optional.of(Beer.builder().build()));

		mockMvc.perform(get("/api/v1/beer/{beerId}", UUID.randomUUID().toString()).param("iscold", "yes")
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andDo(document("/v1/beer",
						pathParameters(parameterWithName("beerId").description("UUID if desired beer to get")),
						requestParameters(parameterWithName("iscold").description("Is Beer Cold Query param"))));
	}

	/* Example : "Documenting Response Parameter" */
	@Test
	public void getBeerById_2() throws Exception {
		given(beerRespository.findById(any())).willReturn(Optional.of(Beer.builder().build()));

		mockMvc.perform(get("/api/v1/beer/{beerId}", UUID.randomUUID().toString()).param("iscold", "yes")
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andDo(document("/v1/beer",
						pathParameters(parameterWithName("beerId").description("UUID if desired beer to get")),
						requestParameters(parameterWithName("iscold").description("Is Beer Cold Query param")),
						responseFields(fieldWithPath("id").description("Id of beer"), //
								fieldWithPath("id").description("Id of beer"), //
								fieldWithPath("version").description("Version Number"), //
								fieldWithPath("createDate").description("Date Created"), //
								fieldWithPath("lastModifiedDate").description("Date Updated"), //
								fieldWithPath("beerName").description("Beer Name"), //
								fieldWithPath("beerStyleName").description("Beer Style"), //
								fieldWithPath("upc").description("UPC of beer"), //
								fieldWithPath("quantityOnHand").description("Quantity On Hand"), //
								fieldWithPath("price").description("Price of beer"))));
	}

	@Test
	public void createBeer() throws Exception {
		BeerDTO beerDTO = getValidBeerDTO();
		String beerDtoToJson = objectmapper.writeValueAsString(beerDTO);
		mockMvc.perform(post("/api/v1/beer/").contentType(MediaType.APPLICATION_JSON).content(beerDtoToJson))
		                                .andExpect(status().isCreated());

	}

	/* Example : "Documenting Constraints" */
//	@Test
//	public void createBeer() throws Exception {
//		BeerDTO beerDTO = getValidBeerDTO();
//		String beerDtoToJson = objectmapper.writeValueAsString(beerDTO);
//		ConstrainedFields fields = new ConstrainedFields(BeerDTO.class);
//		mockMvc.perform(post("/api/v1/beer/").contentType(MediaType.APPLICATION_JSON).content(beerDtoToJson))
//				.andExpect(status().isCreated())
//				.andDo(document("/v1/beer",//
//						requestFields(fieldWithPath("id").ignored(),//
//								fields.withPath("version").ignored(),//
//								fields.withPath("createDate").ignored(),//
//								fields.withPath("lastModifiedDate").ignored(),//
//								fields.withPath("beerName").description("Name of the beer"),//
//								fields.withPath("beerStyleName").description("Beer Style"),//
//								fields.withPath("upc").description("UPC of beer"),//
//								fields.withPath("quantityOnHand").description(""),//
//								fields.withPath("price").description("Price of beer"))));
//
//	}
	@Test
	public void updateBeerById() throws Exception {
		BeerDTO beerDTO = getValidBeerDTO();
		String beerDtoToJson = objectmapper.writeValueAsString(beerDTO);

		mockMvc.perform(put("/api/v1/beer/" + UUID.randomUUID().toString()).contentType(MediaType.APPLICATION_JSON)
				.content(beerDtoToJson)).andExpect(status().isNoContent());

	}

	BeerDTO getValidBeerDTO() {
		return BeerDTO.builder().beerName("Bud").id(UUID.randomUUID()).beerStyleName(BeerStyleEnum.PALE_ALE)
				.upc(123321L).build();
	}

	private static class ConstrainedFields {
		private final ConstraintDescriptions constraintDescriptions;

		ConstrainedFields(Class<?> input) {
			this.constraintDescriptions = new ConstraintDescriptions(input);
		}

		private FieldDescriptor withPath(String path) {
			return fieldWithPath(path).attributes(key("constraints").value(StringUtils
					.collectionToDelimitedString(this.constraintDescriptions.descriptionsForProperty(path), ". ")));
		}
	}

}
