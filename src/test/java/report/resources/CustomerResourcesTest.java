package report.resources;

import com.github.javafaker.Faker;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.*;
import org.mockito.*;
import report.model.dto.CustomerDTOMapper;
import report.model.dto.CustomerDto;
import report.model.dto.CustomerRegistrationRequest;
import report.model.dto.CustomerUpdateDto;
import report.model.entity.Customer;
import report.repository.CustomerRepository;
import report.service.CustomerService;
import report.utils.GenerateData;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.UUID;

import static com.google.common.base.Predicates.equalTo;
import static io.restassured.RestAssured.given;
import static org.jboss.resteasy.reactive.RestResponse.Status.*;
import static org.mockito.ArgumentMatchers.any;


@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Tag("integration")
class CustomerResourcesTest {
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
    @Order(1)
    void reportFileTest() {
        String request = "{ \"reportType\": \"JSON\",\"startDate\":\"2015-12-31\",\"endDate\":\"2028-12-31\"}";
        given()
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                .body(request)
                .when()
                .post("/api/v1/customers/report")
                .then()
                .statusCode((OK.getStatusCode()));

    }

    @Test
    @Order(1)
    void getCustomers(){
        given()
                .when()
                .get("/api/v1/customers")
                .then()
                .statusCode(OK.getStatusCode());
    }

    @Test
    @Order(3)
    void updateAddressKOTest(){
        given()
                .contentType(MediaType.APPLICATION_JSON)
                .body("{\"address\":\"Via Cagliari\"}")
                .when()
                .patch("/api/v1/customers/ee27a333-0b1b-4b1b-9b1b-0b1b4b1b9b1b")
                .then()
                .statusCode(NOT_FOUND.getStatusCode());
    }

    @Test
    @Order(2)
    void persistCustomerTest(){
        CustomerRegistrationRequest request =
                new CustomerRegistrationRequest(GenerateData.generateStaticCustomer().getName(),
                        GenerateData.generateStaticCustomer().getAddress(),
                        GenerateData.generateStaticCustomer().getPhoneNumber());
        given()
                .contentType(MediaType.APPLICATION_JSON)
                .body(request)
                .when()
                .post("/api/v1/customers")
                .then()
                .statusCode(CREATED.getStatusCode());
    }

/*
    @Order(3)
    @Test
    void updateCustomerAddressTest(){
        CustomerUpdateDto.Request.UpdateCustomerAddress request = new CustomerUpdateDto.Request.UpdateCustomerAddress("Via Cagliari");
        Customer customer = GenerateData.generateCustomer();
        Mockito.when(customerRepository.findByIdOptional(any(UUID.class)))
                .thenReturn(Optional.of(customer));

        UUID id = GenerateData.generateStaticCustomer().getId();
                given()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(request)
                .when()
                    .patch("/api/v1/customers/{id}",id)
                    .peek()
                .then()
                    .statusCode(OK.getStatusCode())
                        .extract().response().body().prettyPrint();

    }*/

}