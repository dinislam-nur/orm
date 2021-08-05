package ru.iteco.true_adapter.first;

import ru.iteco.true_adapter.db.Db;
import ru.iteco.true_adapter.entity.DbEntity;

public class FirstOrmImpl<T extends DbEntity> implements FirstOrm<T> {

    protected final Db<T> db;

    public FirstOrmImpl(Db<T> db) {
        this.db = db;
    }

    @Override
    public void create(T entity) {
        db.add(entity);
    }

    @Override
    public T read(int id) {
        return db.get((long) id);
    }

    @Override
    public void update(T entity) {
        create(entity);
    }

    @Override
    public void delete(T entity) {
        db.delete(entity.getId());
    }
}
