package report.model.mapper;

import report.model.dto.CustomerViewModel;
import report.model.entity.Customer;

import javax.enterprise.context.ApplicationScoped;
import java.util.function.Function;

@ApplicationScoped
public class CustomerViewModelMapper implements Function<Customer, CustomerViewModel> {
    @Override
    public CustomerViewModel apply(Customer customer) {
        return CustomerViewModel.builder()
                .withId(customer.getId())
                .withName(customer.getName())
                .withAddress(customer.getAddress())
                .withPhoneNumber(customer.getPhoneNumber())
                .withCreatedAt(customer.getCreatedAt())
                .build();
    }
}
