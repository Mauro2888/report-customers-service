package report.repository.custom;

import java.util.List;
import java.util.UUID;

public interface BaseRepositoy<E> {

    <T extends E> T findById(Class<T> tClass, UUID id);

    <T extends E> List<T> findAll(Class<T> tClass);
}
