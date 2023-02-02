package report.model.dto;

import report.model.entity.Customer;

import javax.enterprise.context.ApplicationScoped;
import java.util.function.Function;

@ApplicationScoped
public class CustomerDTOMapper implements Function<Customer, CustomerDto> {

    @Override
    public CustomerDto apply(Customer entity) {
        return new CustomerDto(
                entity.getId(),
                entity.getName(),
                entity.getAddress(),
                entity.getPhoneNumber());
    }
}
