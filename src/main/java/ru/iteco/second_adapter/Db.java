package ru.iteco.second_adapter;

import java.util.Set;

public interface Db<T extends DbEntity> {

    void add(T value);

    T get(Long id);

    void delete(Long id);

    Set<T> getAll();

    void deleteAll();
}
