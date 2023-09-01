package report.service.reports;

import report.model.ReportType;
import report.model.dto.ReportRequestDto;
import report.model.entity.Customer;
import report.repository.CustomerRepository;
import report.service.ReportTemplate;
import report.model.mapper.CustomerViewModelMapper;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class JSONReport extends ReportTemplate<ReportRequestDto, Customer> {
    private final CustomerRepository customerRepository;
    private final CustomerViewModelMapper customerViewModelMapper;
    private final Jsonb jsonb;

    @Inject
    public JSONReport(CustomerRepository customerRepository, CustomerViewModelMapper customerViewModelMapper) {
        this.customerRepository = customerRepository;
        this.customerViewModelMapper = customerViewModelMapper;
        this.jsonb = JsonbBuilder.create();
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
        var output = userData.stream()
                .map(customerViewModelMapper)
                .collect(Collectors.toList());
        return jsonb.toJson(output);

    }


}
