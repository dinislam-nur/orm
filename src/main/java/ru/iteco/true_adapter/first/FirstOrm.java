package ru.iteco.true_adapter.first;

import ru.iteco.true_adapter.entity.DbEntity;

/**
 * IFirstOrm.
 *
 * @author Ilya_Sukhachev
 */
public interface FirstOrm<T extends DbEntity> {

    void create(T entity);

    T read(int id);

    void update(T entity);

    void delete(T entity);
}
