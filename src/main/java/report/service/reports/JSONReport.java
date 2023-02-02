package report.service.reports;

import report.model.ReportType;
import report.model.dto.ReportRequestDto;
import report.model.entity.Customer;
import report.repository.CustomerRepository;
import report.service.ReportTemplate;

import javax.enterprise.context.ApplicationScoped;
import javax.json.Json;
import javax.json.stream.JsonCollectors;
import java.util.List;

@ApplicationScoped
public class JSONReport extends ReportTemplate<ReportRequestDto, Customer> {

    private final CustomerRepository customerRepository;

    public JSONReport(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public ReportType reportType() {
        return ReportType.JSON;
    }

    @Override
    public String getErrorMessage() {
        return "error while generating report JSON...";
    }

    @Override
    public List<Customer> fetchDataFromUser(ReportRequestDto request) {
        return customerRepository.findByCreatedAtBetween(request.startDate(), request.endDate());
    }

    @Override
    public String generateContentFile(List<Customer> userData) {
        return userData.stream()
                .map(customerEntity -> Json.createObjectBuilder()
                        .add("id", customerEntity.getId().toString())
                        .add("name", customerEntity.getName())
                        .add("address", customerEntity.getAddress())
                        .add("phoneNumber", customerEntity.getPhoneNumber())
                        .add("createdAt", customerEntity.getCreatedAt().toString())
                        .build()).collect(JsonCollectors.toJsonArray()).toString();

    }
}
