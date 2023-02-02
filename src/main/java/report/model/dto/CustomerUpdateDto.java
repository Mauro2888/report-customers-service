package report.model.dto;

import javax.validation.constraints.NotBlank;

public enum CustomerUpdateDto {;

    private interface Address {
        @NotBlank
        String address();
    }

    public enum Request {;
        public record UpdateCustomerAddress(String address) implements Address {}
    }
}
