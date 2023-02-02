package report.service.reports;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import report.model.ReportType;
import report.model.dto.ReportRequestDto;
import report.model.entity.Customer;
import report.repository.CustomerRepository;
import report.utils.GenerateData;

import java.time.Clock;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.UUID;

@QuarkusTest
class CSVReportTest {

    @InjectMock
    CustomerRepository customerRepository;
    private CSVReport csvReport;
    Customer customer = GenerateData.generateCustomer();
    private final static LocalDate DATE = LocalDate.of(2020, 12, 31);
    @BeforeEach
    void setUp() {
        csvReport = new CSVReport(customerRepository);
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void fetchDataFromUser() {
        Mockito.when(customerRepository.findByCreatedAtBetween(LocalDate.now(), LocalDate.now()))
                .thenReturn(List.of(customer));
        ReportRequestDto reportRequestDto = new ReportRequestDto(ReportType.CSV, LocalDate.now(), LocalDate.now());
        Assertions.assertEquals(1, csvReport.fetchDataFromUser(reportRequestDto).size());
    }
    @Test
    void getErrorMessage() {
        Assertions.assertEquals("error while generating report CSV...", csvReport.getErrorMessage());
    }
    @Test
    void reportType() {
        Assertions.assertEquals(ReportType.CSV, csvReport.reportType());
    }

    @Test
    void generateContentFile() {
        Clock clock = Clock.fixed(DATE.atStartOfDay(ZoneId.systemDefault()).toInstant(), ZoneId.systemDefault());
        Customer customer = new Customer();
        customer.setName("Fulvio");
        customer.setAddress("Via Roma 3");
        customer.setPhoneNumber("123456789");
        customer.setCreatedAt(clock.instant().atZone(ZoneId.systemDefault()).toLocalDate());
        customer.setId(UUID.randomUUID());

        String csv = csvReport.generateContentFile(List.of(customer));
        Assertions.assertEquals("Fulvio;Via Roma 3;123456789;2020-12-31\r".trim(), csv.trim());
    }
}