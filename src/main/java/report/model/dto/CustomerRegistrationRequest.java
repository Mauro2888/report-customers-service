package report.model.dto;

import javax.validation.constraints.NotBlank;

public record CustomerRegistrationRequest(

        @NotBlank
        String name,
        @NotBlank
        String address,
        @NotBlank
        String phoneNumber) {
}
