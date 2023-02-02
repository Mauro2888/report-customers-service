package report.config;

import report.model.ReportType;
import report.model.dto.ReportRequestDto;
import report.model.entity.Customer;
import report.service.ReportTemplate;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Instance;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@ApplicationScoped
public class ReportServiceConfig {

    @Inject
    Instance<ReportTemplate<ReportRequestDto, Customer>> listOfServices;

    @Produces
    public Map<ReportType, ReportTemplate<ReportRequestDto, Customer>> servicesReportTypemap() {
        return listOfServices.stream().collect(Collectors.toMap(ReportTemplate::reportType, Function.identity()));
    }
}
