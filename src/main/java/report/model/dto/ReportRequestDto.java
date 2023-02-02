package report.model.dto;

import io.smallrye.common.constraint.NotNull;
import report.model.ReportType;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;


public record ReportRequestDto(

        @NotNull
        ReportType reportType,
        @PastOrPresent
        LocalDate startDate,
        @FutureOrPresent
        LocalDate endDate) {
}
