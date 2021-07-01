package ru.iteco.second_adapter.second;

import ru.iteco.second_adapter.DbUserEntity;
import ru.iteco.second_adapter.DbUserInfoEntity;

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
