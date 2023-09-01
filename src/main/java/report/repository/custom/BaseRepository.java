package report.repository.custom;

import java.util.List;
import java.util.UUID;

public interface BaseRepository<E> {

    <T extends E> T findById(Class<T> tClass, UUID id);

    <T extends E> List<T> findAll(Class<T> tClass);

    <T extends E> T save(T entity);

}
