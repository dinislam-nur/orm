package ru.iteco.true_adapter.adapter;

import ru.iteco.true_adapter.entity.DbEntity;

public interface Adapter<T extends DbEntity> {

    void create(T entity);

    T read(Long id);

    void update(T entity);

    void delete(T entity);

}
