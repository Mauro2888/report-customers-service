package report.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import report.model.entity.Customer;

import javax.enterprise.context.ApplicationScoped;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@ApplicationScoped
public class CustomerRepository implements PanacheRepositoryBase<Customer, UUID> {
    public List<Customer> findByCreatedAtBetween(LocalDate startDate, LocalDate endDate) {
        return find("createdAt BETWEEN :startDate AND :endDate",
                Map.of("startDate", startDate, "endDate", endDate)).list();
    }
}
