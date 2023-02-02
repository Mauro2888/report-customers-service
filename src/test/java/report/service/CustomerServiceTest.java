package report.service;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.smallrye.common.constraint.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import report.model.dto.CustomerDTOMapper;
import report.model.dto.CustomerDto;
import report.model.dto.CustomerRegistrationRequest;
import report.model.dto.CustomerUpdateDto;
import report.model.entity.Customer;
import report.repository.CustomerRepository;
import report.utils.GenerateData;

import javax.ws.rs.NotFoundException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static io.smallrye.common.constraint.Assert.assertNotNull;
import static org.jboss.resteasy.reactive.RestResponse.Status.NOT_FOUND;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class CustomerServiceTest {

    private CustomerService underTest;

    @Mock
    private CustomerRepository customerRepository;
    private final CustomerDTOMapper mapper = new CustomerDTOMapper();

   @BeforeEach
   void setup(){
       MockitoAnnotations.openMocks(this);
       underTest = new CustomerService(customerRepository, mapper);
   }
    @Test
    void customers() {
        Customer entity = GenerateData.generateCustomer();
        when(customerRepository.listAll()).thenReturn(List.of(entity));
        CustomerDto customerDto = mapper.apply(entity);
        assertEquals(customerDto, underTest.customers().get(0));
    }

    @Test
    void updateByAddressKO() {

        when(customerRepository.findByIdOptional(UUID.fromString(GenerateData.generateStaticCustomer().getId().toString())))
                .thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> underTest.updateCustomer(UUID.fromString(GenerateData.generateStaticCustomer().getId().toString()),
                new CustomerUpdateDto.Request.UpdateCustomerAddress("Via Cagliari")));
    }
    @Test
    void testUpdateCustomerAddress(){
        Customer customer = GenerateData.generateStaticCustomer();

        when(customerRepository.findByIdOptional(UUID.fromString(customer.getId().toString())))
                .thenReturn(Optional.of(customer));
        CustomerDto customerDto =
                underTest.updateCustomer(customer.getId(), new CustomerUpdateDto.Request.UpdateCustomerAddress("Via Cagliari"));
        assertEquals(customerDto.address(), "Via Cagliari");
        assertEquals(customerDto.name(), customer.getName());
        assertEquals(customerDto.phoneNumber(), customer.getPhoneNumber());

   }

    @Test
    void saveCustomer(){
        CustomerRegistrationRequest request = new CustomerRegistrationRequest(
                "Mauro",
                "Via Roma",
                "1234567890"
        );
        Mockito.doNothing().when(customerRepository).persist(any(Customer.class));
        //When
        underTest.saveCustomer(request);
        //Then
        Mockito.verify(customerRepository, Mockito.times(1))
                .persist(any(Customer.class));
        PanacheQuery<Customer> name = customerRepository.find("name", request.name());

        when(customerRepository.find("name", request.name())).thenReturn(name);
        Assertions.assertEquals(request.name(),"Mauro");
    }
}