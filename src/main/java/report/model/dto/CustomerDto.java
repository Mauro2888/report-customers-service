package report.model.dto;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

public record CustomerDto(

        @NotBlank
        UUID id,
        @NotBlank
        String name,
        @NotBlank
        String address,
        @NotBlank
        String phoneNumber) {
}
