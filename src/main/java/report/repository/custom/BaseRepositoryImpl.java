package report.repository.custom;

import report.model.entity.BaseEntity;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.ws.rs.NotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class BaseRepositoryImpl implements BaseRepositoy<BaseEntity> {

    private final EntityManager entityManager;

    @Inject
    public BaseRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public <T extends BaseEntity> T findById(Class<T> tClass, UUID id) {
        return Optional.ofNullable(entityManager.find(tClass,id)).orElseThrow(() -> new NotFoundException("Not found"));
    }

    @Override
    public <T extends BaseEntity> List<T> findAll(Class<T> tClass) {
        return Optional.ofNullable(createQuery(tClass))
                .map(TypedQuery::getResultList)
                .orElseGet(List::of);
    }

    private <T extends BaseEntity> TypedQuery<T> createQuery(Class<T> tClass) {
        return Optional.of(entityManager.createQuery("SELECT e FROM " + tClass.getSimpleName() + " e", tClass))
                .orElseThrow(() -> new NotFoundException("Not found"));
    }
}
