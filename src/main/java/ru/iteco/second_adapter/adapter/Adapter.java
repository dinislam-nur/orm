package ru.iteco.second_adapter.adapter;

import ru.iteco.second_adapter.DbEntity;
import ru.iteco.second_adapter.first.FirstOrm;

import java.util.Set;

public interface Adapter<T, E extends DbEntity> extends FirstOrm<E> {

    void create(T entity);

    T read(Long id);

    void update(T entity);

    void delete(T entity);

    Set<T> getAll();

}
