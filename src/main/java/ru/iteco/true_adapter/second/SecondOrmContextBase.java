package ru.iteco.true_adapter.second;

import ru.iteco.true_adapter.db.Db;
import ru.iteco.true_adapter.entity.DbEntity;
import ru.iteco.true_adapter.entity.DbUserEntity;
import ru.iteco.true_adapter.entity.DbUserInfoEntity;

import java.util.Set;

import static java.util.Collections.*;

public abstract class SecondOrmContextBase<E extends DbEntity> implements SecondOrmContext {

    protected final Db<E> db;

    protected SecondOrmContextBase(Db<E> db) {
        this.db = db;
    }

    @Override
    public Set<DbUserEntity> getUsers() {
        return emptySet();
    }

    @Override
    public Set<DbUserInfoEntity> getUserInfos() {
        return emptySet();
    }

}
