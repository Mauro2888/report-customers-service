package report.service.reports;

import report.model.ReportType;
import report.model.dto.ReportRequestDto;
import report.model.entity.Customer;
import report.repository.CustomerRepository;
import report.service.ReportTemplate;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@ApplicationScoped
public class HTMLReport extends ReportTemplate<ReportRequestDto, Customer> {

    private final CustomerRepository customerRepository;

    public HTMLReport(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public ReportType reportType() {
        return ReportType.HTML;
    }

    @Override
    public String getErrorMessage() {
        return "error while generating report HTML...";
    }

    @Override
    public List<Customer> fetchDataFromUser(ReportRequestDto request) {
        return customerRepository.findByCreatedAtBetween(request.startDate(), request.endDate());
    }

    @Override
    public String generateContentFile(List<Customer> dataFromSource) {
        String tableContent = dataFromSource.stream()
                .map(userData -> Stream.of(
                        userData.getId().toString(),
                        userData.getName(),
                                userData.getAddress(),
                                userData.getPhoneNumber(),
                                userData.getCreatedAt().toString())
                        .collect(Collectors.joining("</td><td>", "<tr><td>", "</td></tr>")))
                .collect(Collectors.joining());

         return " <!DOCTYPE html>\n"
                + "                        <html>\n"
                + "                        <style>\n"
                + "                            table, th, td {\n"
                + "                                border:1px solid black;\n"
                + "                            }\n"
                + "                        </style>\n"
                + "                        <body>        \n"
                + "                        <h2>Customers</h2>\n"
                + "                        <table style=\"width:100%\">\n"
                + "                          <tr>\n"
                + "                            <th>Id</th>\n"
                + "                            <th>Full Name</th>\n"
                + "                            <th>Address</th>\n"
                + "                            <th>Phone number</th>\n"
                + "                            <th>Created at</th>\n"
                + "                          </tr>\n"
                + "                         " + tableContent + "\n"
                + "                        </table>\n"
                + "                        </body>\n"
                + "                        </html>";
    }
}
