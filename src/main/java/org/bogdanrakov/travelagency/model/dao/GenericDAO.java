package org.bogdanrakov.travelagency.model.dao;

import java.util.List;
import java.util.Optional;

public interface GenericDAO<T> extends AutoCloseable {
    List<T> findAll();
    Optional<T> findById(long id);
    int insert(T item);
    boolean update(T item);
    boolean delete(long id);
}
