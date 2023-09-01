package report.service;


import report.model.dto.CustomerDTOMapper;
import report.model.dto.CustomerDto;
import report.model.dto.CustomerRegistrationRequest;
import report.model.dto.CustomerUpdateDto;
import report.model.entity.Customer;
import report.repository.CustomerRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.NotFoundException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@ApplicationScoped
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerDTOMapper customerDTOMapper;


    @Inject
    public CustomerService(CustomerRepository customerRepository, CustomerDTOMapper customerDTOMapper) {
        this.customerRepository = customerRepository;
        this.customerDTOMapper = customerDTOMapper;
    }

    @Transactional
    public CustomerDto updateCustomer(UUID id, CustomerUpdateDto.Request.UpdateCustomerAddress customerUpdateDto) {
        Customer customer = customerRepository.findByIdOptional(id).orElseThrow(() ->
                new NotFoundException("User not found"));
        customer.setAddress(customerUpdateDto.address());
        customerRepository.persist(customer);
        return customerDTOMapper.apply(customer);

    }

    @Transactional
    public void saveCustomer(CustomerRegistrationRequest requestDto) {
        Customer customer = new Customer(requestDto.name(),
                requestDto.address(),
                requestDto.phoneNumber());
        customerRepository.persist(customer);
    }

    public List<CustomerDto> customers() {
        return customerRepository.all()
                .stream().map(customerDTOMapper)
                .toList();
    }
}
