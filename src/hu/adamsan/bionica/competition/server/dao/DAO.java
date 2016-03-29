package hu.adamsan.bionica.competition.server.dao;

import java.util.List;

public interface DAO<T> {
    List<T> findAll();

    boolean delete(T entity);

    T find(long id);

    boolean save(T entity);
}
