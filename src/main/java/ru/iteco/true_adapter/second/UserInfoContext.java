package ru.iteco.true_adapter.second;

import ru.iteco.true_adapter.db.Db;
import ru.iteco.true_adapter.entity.DbUserInfoEntity;

import java.util.Set;

public class UserInfoContext extends SecondOrmContextBase<DbUserInfoEntity> {

    public UserInfoContext(Db<DbUserInfoEntity> db) {
        super(db);
    }

    @Override
    public Set<DbUserInfoEntity> getUserInfos() {
        return db.getAll();
    }
}
