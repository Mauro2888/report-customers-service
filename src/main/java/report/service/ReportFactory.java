package report.service;

import report.config.ReportServiceConfig;
import report.model.dto.ReportRequestDto;
import report.model.entity.Customer;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.NotFoundException;

@ApplicationScoped
public record ReportFactory(ReportServiceConfig reportServiceConfig) {
    public String generateReport(ReportRequestDto request) {
        ReportTemplate<ReportRequestDto, Customer> getServiceByType =
                reportServiceConfig.servicesReportTypemap().getOrDefault(request.reportType(), null);
        if (getServiceByType == null) {
            throw new NotFoundException("No service available for %s".formatted(request.reportType().name()));
        }
        return getServiceByType.generateReport(request);
    }

}
