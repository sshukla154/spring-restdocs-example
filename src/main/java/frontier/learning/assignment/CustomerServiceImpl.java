package frontier.learning.assignment;

import java.util.UUID;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {

	@Override
	public CustomerDTO getCustomerById(UUID customerId) {
		log.debug("CustomerServiceImpl.getCustomerById()...");
		return CustomerDTO.builder().id(UUID.randomUUID()).name("SShukla154").build();
	}

	@Override
	public CustomerDTO createCustomer(UUID customerId, CustomerDTO customerDTO) {
		log.debug("CustomerServiceImpl.createCustomer()...");
		return CustomerDTO.builder().id(UUID.randomUUID()).build();
	}

	@Override
	public void updateCustomer(UUID customerId, CustomerDTO customerDTO) {
		log.debug("CustomerServiceImpl.updateCustomer()...");
	}

	@Override
	public void deleteCustomerById(UUID customerId) {
		log.debug("CustomerServiceImpl.deleteCustomerById()...");
	}

}
