package ru.iteco.first_adapter.orm.second;

import ru.iteco.first_adapter.DbUserEntity;
import ru.iteco.first_adapter.DbUserInfoEntity;

import java.util.Set;

/**
 * ISecondOrmContext.
 *
 * @author Ilya_Sukhachev
 */
public interface SecondOrmContext {

    Set<DbUserEntity> getUsers();

    Set<DbUserInfoEntity> getUserInfos();

}
