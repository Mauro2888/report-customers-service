package report.service.reports;

import report.model.ReportType;
import report.model.dto.ReportRequestDto;
import report.model.entity.Customer;
import report.repository.CustomerRepository;
import report.service.ReportTemplate;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class XMLReport extends ReportTemplate<ReportRequestDto, Customer> {

    private final CustomerRepository customerRepository;

    public XMLReport(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public ReportType reportType() {
        return ReportType.XML;
    }

    @Override
    public String getErrorMessage() {
        return "error while generating report XML...";
    }

    @Override
    public List<Customer> fetchDataFromUser(ReportRequestDto request) {
        return customerRepository.findByCreatedAtBetween(request.startDate(), request.endDate());
    }

    @Override
    public String generateContentFile(List<Customer> dataFromSource) {
        String tableContent = dataFromSource.stream()
                .map(userData -> "<customer>\n" + String.join("\n",
                        "<id>" + userData.getId().toString() + "</id>",
                        "<name>" + userData.getName() + "</name>",
                        "<address>" + userData.getAddress() + "</address>",
                        "<phoneNumber>" + userData.getPhoneNumber() + "</phoneNumber>",
                        "<createdAt>" + userData.getCreatedAt().toString() + "</createdAt>") + "\n</customer>")
                .collect(Collectors.joining("\n"));

        return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
                + "<customers>\n"
                + tableContent
                + "\n</customers>";

    }
}
