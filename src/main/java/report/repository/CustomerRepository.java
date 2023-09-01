package report.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import report.model.entity.BaseEntity;
import report.model.entity.Customer;
import report.repository.custom.BaseRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@ApplicationScoped
public class CustomerRepository implements PanacheRepositoryBase<Customer, UUID> {

    private final BaseRepository<BaseEntity> baseRepository;

    @Inject
    public CustomerRepository(BaseRepository<BaseEntity> baseRepository) {
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
