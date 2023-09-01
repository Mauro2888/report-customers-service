package report.service.reports.mapper;

import report.model.entity.Customer;

import javax.enterprise.context.ApplicationScoped;
import java.util.function.Function;

@ApplicationScoped
public class CustomerViewModelMapper implements Function<Customer, CustomerViewModel> {
    @Override
    public CustomerViewModel apply(Customer customer) {
        return CustomerViewModel.builder()
                .withId(customer.getId())
                .withAddress(customer.getAddress())
                .withPhoneNumber(customer.getPhoneNumber())
                .withCreatedAt(customer.getCreatedAt())
                .build();
    }
}
