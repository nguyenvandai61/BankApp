package com.github.nguyenvandai61.repositories;

import java.util.List;
import java.util.Optional;

public interface IBaseRepository<T, ID> {
    List<T> findAll();

    Optional<T> find(ID id);

    void save(T model);
    Optional<T> deleteById(ID id);
}
