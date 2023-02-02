package report.service.reports;

import report.model.ReportType;
import report.model.dto.ReportRequestDto;
import report.model.entity.Customer;
import report.repository.CustomerRepository;
import report.service.ReportTemplate;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@ApplicationScoped
public class CSVReport extends ReportTemplate<ReportRequestDto, Customer> {

    private final CustomerRepository customerRepository;

    public CSVReport(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public ReportType reportType() {
        return ReportType.CSV;
    }

    @Override
    public java.lang.String getErrorMessage() {
        return "error while generating report CSV...";
    }

    @Override
    public List<Customer> fetchDataFromUser(ReportRequestDto request) {
        return customerRepository.findByCreatedAtBetween(request.startDate(), request.endDate());
    }

    public String getFileName(){
        return "csv-report-" + UUID.randomUUID() + ".csv";
    }

    @Override
    public java.lang.String generateContentFile(List<Customer> dataFromSource) {
        return dataFromSource.stream()
                .map(userData -> Stream.of(userData.getName(),
                                userData.getAddress(),
                                userData.getPhoneNumber(),
                                userData.getCreatedAt().toString())
                        .collect(Collectors.joining(";", "", "\r\n")))
                .collect(Collectors.joining());
    }
}
