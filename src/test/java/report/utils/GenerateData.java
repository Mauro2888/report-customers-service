package report.utils;

import com.github.javafaker.Faker;
import report.model.dto.CustomerDto;
import report.model.entity.Customer;

import java.time.LocalDate;
import java.util.Locale;
import java.util.UUID;

public class GenerateData {
    private final static LocalDate DATE = LocalDate.of(2020, 12, 31);

    public static Customer generateCustomer() {
        Faker faker = new Faker(Locale.ITALY);
        Customer customer = new Customer();
        customer.setName(faker.name().firstName());
        customer.setAddress(faker.address().fullAddress());
        customer.setPhoneNumber(faker.phoneNumber().cellPhone());
        customer.setCreatedAt(DATE);
        return customer;
    }

    public static Customer generateStaticCustomer() {
        Customer customer = new Customer();
        customer.setName("Mauro");
        customer.setAddress("Via Roma");
        customer.setPhoneNumber("3484964326");
        customer.setCreatedAt(DATE);
        return customer;
    }
}
