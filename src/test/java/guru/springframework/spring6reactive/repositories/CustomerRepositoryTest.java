package guru.springframework.spring6reactive.repositories;

import guru.springframework.spring6reactive.config.DatabaseConfig;
import guru.springframework.spring6reactive.domain.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.r2dbc.test.autoconfigure.DataR2dbcTest;
import org.springframework.context.annotation.Import;
import tools.jackson.databind.ObjectMapper;

@DataR2dbcTest
@Import({DatabaseConfig.class})
public class CustomerRepositoryTest {

    @Autowired
    CustomerRepository customerRepository;

    @Test
    void testCreateJson() {
        ObjectMapper objectMapper = new ObjectMapper();

        System.out.println(objectMapper.writeValueAsString(getTestCustomer()));
    }

    @Test
    void TestSaveNewBeer() {
        customerRepository.save(getTestCustomer())
                .subscribe(System.out::println);

    }

    public static Customer getTestCustomer() {
        return Customer.builder()
                .customerName("Jandier")
                .build();
    }
}