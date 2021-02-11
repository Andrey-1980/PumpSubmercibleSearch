package com.kazachenko.pumpsearch.persistance.api;

import java.util.List;

public interface Repository<T> {

    List<T> loadAll();

    void save(T model);
}
