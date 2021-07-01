package ru.iteco.first_adapter.factory;

import ru.iteco.first_adapter.Db;
import ru.iteco.first_adapter.DbUserEntity;
import ru.iteco.first_adapter.DbUserInfoEntity;
import ru.iteco.first_adapter.orm.first.FirstOrm;
import ru.iteco.first_adapter.orm.first.FirstOrmImpl;
import ru.iteco.first_adapter.orm.second.SecondOrm;
import ru.iteco.first_adapter.orm.second.SecondOrmImpl;

public class OrmFactoryImpl implements OrmFactory {

    private final Db<DbUserEntity> userDb;
    private final Db<DbUserInfoEntity> userInfoDb;

    public OrmFactoryImpl(Db<DbUserEntity> userDb, Db<DbUserInfoEntity> userInfoDb) {
        this.userDb = userDb;
        this.userInfoDb = userInfoDb;
    }

    @Override
    public FirstOrm<DbUserEntity> createUserFirstOrm() {
        return new FirstOrmImpl<>(userDb);
    }

    @Override
    public FirstOrm<DbUserInfoEntity> createUserInfoFirstOrm() {
        return new FirstOrmImpl<>(userInfoDb);
    }

    @Override
    public SecondOrm createSecondOrm() {
        return new SecondOrmImpl(userDb, userInfoDb);
    }
}
