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


    private final Instance<ReportTemplate<ReportRequestDto, Customer>> listOfServices;

    @Inject
    public ReportServiceConfig(Instance<ReportTemplate<ReportRequestDto, Customer>> listOfServices) {
        this.listOfServices = listOfServices;
    }

    @Produces
    public Map<ReportType, ReportTemplate<ReportRequestDto, Customer>> servicesReportTypemap() {
        return listOfServices.stream().collect(Collectors.toMap(ReportTemplate::reportType, Function.identity()));
    }
}
