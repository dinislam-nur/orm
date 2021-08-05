package ru.iteco.true_adapter.db;

import ru.iteco.true_adapter.entity.DbEntity;

import java.util.Set;

public interface Db<T extends DbEntity> {

    void add(T value);

    T get(Long id);

    void delete(Long id);

    Set<T> getAll();

    void deleteAll();
}
