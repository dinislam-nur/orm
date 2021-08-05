package ru.iteco.true_adapter.second;

import ru.iteco.true_adapter.entity.DbUserEntity;
import ru.iteco.true_adapter.entity.DbUserInfoEntity;

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
