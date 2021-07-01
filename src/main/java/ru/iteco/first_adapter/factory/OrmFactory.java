package ru.iteco.first_adapter.factory;

import ru.iteco.first_adapter.DbUserEntity;
import ru.iteco.first_adapter.DbUserInfoEntity;
import ru.iteco.first_adapter.orm.first.FirstOrm;
import ru.iteco.first_adapter.orm.second.SecondOrm;

public interface OrmFactory {

    FirstOrm<DbUserEntity> createUserFirstOrm();

    FirstOrm<DbUserInfoEntity> createUserInfoFirstOrm();

    SecondOrm createSecondOrm();
}
