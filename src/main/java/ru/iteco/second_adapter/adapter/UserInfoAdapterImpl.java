package ru.iteco.second_adapter.adapter;

import ru.iteco.second_adapter.Db;
import ru.iteco.second_adapter.DbUserInfoEntity;
import ru.iteco.second_adapter.second.SecondOrm;
import ru.iteco.second_adapter.user.UserInfo;
import ru.iteco.second_adapter.utils.UserUtils;

import java.util.Set;
import java.util.stream.Collectors;

import static ru.iteco.second_adapter.utils.UserUtils.*;

public class UserInfoAdapterImpl extends AbstractAdapter<UserInfo, DbUserInfoEntity> {

    public UserInfoAdapterImpl(Db<DbUserInfoEntity> db, SecondOrm secondOrm) {
        super(db, secondOrm);
    }

    @Override
    public void create(UserInfo userInfo) {
        create(fromUserInfo(userInfo));
    }

    @Override
    public UserInfo read(Long id) {
        return fromUserInfoEntity(read(id.intValue()));
    }

    @Override
    public void update(UserInfo userInfo) {
        update(fromUserInfo(userInfo));
    }

    @Override
    public void delete(UserInfo userInfo) {
        delete(fromUserInfo(userInfo));
    }

    @Override
    public Set<UserInfo> getAll() {
        return secondOrm.getContext()
                .getUserInfos()
                .stream()
                .map(UserUtils::fromUserInfoEntity)
                .collect(Collectors.toSet());
    }
}
