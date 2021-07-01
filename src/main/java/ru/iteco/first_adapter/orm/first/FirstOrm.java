package ru.iteco.first_adapter.orm.first;

import ru.iteco.first_adapter.DbEntity;

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
