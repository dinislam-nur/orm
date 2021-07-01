package ru.iteco.first_adapter.orm.second;

import ru.iteco.first_adapter.Db;
import ru.iteco.first_adapter.DbUserEntity;
import ru.iteco.first_adapter.DbUserInfoEntity;

public class SecondOrmImpl implements SecondOrm {

    private final SecondOrmContext context;

    public SecondOrmImpl(Db<DbUserEntity> userDb, Db<DbUserInfoEntity> userInfoDb) {
        context = new SecondOrmContextImpl(userDb, userInfoDb);
    }

    @Override
    public SecondOrmContext getContext() {
        return context;
    }
}
