package guru.springframework.spring6reactive.services;

import guru.springframework.spring6reactive.model.CustomerDTO;
import reactor.core.publisher.Mono;

public interface CustomerService {

    Mono<CustomerDTO> getCustomerById(Integer id);
}
