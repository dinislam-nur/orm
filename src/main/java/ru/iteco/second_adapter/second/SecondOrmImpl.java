package ru.iteco.second_adapter.second;

import ru.iteco.second_adapter.Db;
import ru.iteco.second_adapter.DbUserEntity;
import ru.iteco.second_adapter.DbUserInfoEntity;

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
