package report.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import report.model.entity.Customer;
import report.repository.custom.BaseRepositoryImpl;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@ApplicationScoped
public class CustomerRepository implements PanacheRepositoryBase<Customer, UUID> {

    private final BaseRepositoryImpl baseRepository;

    @Inject
    public CustomerRepository(BaseRepositoryImpl baseRepository) {
        this.baseRepository = baseRepository;
    }

    public List<Customer> findByCreatedAtBetween(LocalDate startDate, LocalDate endDate) {
        return find("createdAt BETWEEN :startDate AND :endDate",
                Map.of("startDate", startDate, "endDate", endDate)).list();
    }

    public List<Customer> all() {
        return baseRepository.findAll(Customer.class);
    }

}
