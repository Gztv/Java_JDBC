package org.solution.jdbc.repository;

import java.util.List;

public interface Repository<T>{

    List<T> findall();

    T findById(Long id);

    void save(T t);

    void delete(Long id);

}
