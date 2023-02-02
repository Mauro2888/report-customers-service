package report.service;

import report.exception.UnhandledOperationException;
import report.model.ReportType;

import java.util.List;

public abstract class ReportTemplate<R, E> {

    public final String generateReport(R request) {
        try {
            List<E> dataFromUser = fetchDataFromUser(request);
            return generateContentFile(dataFromUser);
        } catch (Exception e) {
            throw new UnhandledOperationException(getErrorMessage());
        }

    }

    public abstract ReportType reportType();

    public abstract String getErrorMessage();

    public abstract List<E> fetchDataFromUser(R request);

    public abstract String generateContentFile(List<E> dataFromSource);
}
