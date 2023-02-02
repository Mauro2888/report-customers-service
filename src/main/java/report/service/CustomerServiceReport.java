package report.service;

import report.model.dto.ReportRequestDto;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public record CustomerServiceReport(ReportFactory reportFactory) {

    public String createReport(ReportRequestDto reportRequest) {
        return reportFactory.generateReport(reportRequest);
    }
}
