package ru.iteco.second_adapter.adapter;

import ru.iteco.second_adapter.Db;
import ru.iteco.second_adapter.DbEntity;
import ru.iteco.second_adapter.first.FirstOrmImpl;
import ru.iteco.second_adapter.second.SecondOrm;

public abstract class AbstractAdapter<T, E extends DbEntity> extends FirstOrmImpl<E> implements Adapter<T, E> {

    protected final SecondOrm secondOrm;

    public AbstractAdapter(Db<E> db, SecondOrm secondOrm) {
        super(db);
        this.secondOrm = secondOrm;
    }
}
