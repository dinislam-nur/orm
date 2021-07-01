package ru.iteco.second_adapter.adapter;

import ru.iteco.second_adapter.Db;
import ru.iteco.second_adapter.DbUserEntity;
import ru.iteco.second_adapter.DbUserInfoEntity;
import ru.iteco.second_adapter.second.SecondOrm;
import ru.iteco.second_adapter.user.User;
import ru.iteco.second_adapter.user.UserInfo;

import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toMap;
import static ru.iteco.second_adapter.utils.UserUtils.*;

public class UserAdapterImpl extends AbstractAdapter<User, DbUserEntity> {

    private Adapter<UserInfo, DbUserInfoEntity> userInfoAdapter;

    protected UserAdapterImpl(Db<DbUserEntity> db, SecondOrm secondOrm,
                              Adapter<UserInfo, DbUserInfoEntity> userInfoAdapter) {
        super(db, secondOrm);
        this.userInfoAdapter = userInfoAdapter;
    }

    @Override
    public void create(User user) {
        userInfoAdapter.create(user.getUserInfo());
        create(fromUser(user));
    }

    @Override
    public User read(Long id) {
        final DbUserEntity userEntity = read(id.intValue());
        final UserInfo userInfo = userInfoAdapter.read(userEntity.getUserInfoId());
        return fromUserEntity(userEntity, userInfo);
    }

    @Override
    public void update(User user) {
        userInfoAdapter.update(user.getUserInfo());
        update(fromUser(user));
    }

    @Override
    public void delete(User user) {
        userInfoAdapter.delete(user.getUserInfo());
        delete(fromUser(user));
    }

    @Override
    public Set<User> getAll() {
        final Map<Long, UserInfo> userInfos = userInfoAdapter.getAll()
                .stream()
                .collect(toMap(UserInfo::getId, Function.identity()));
        return secondOrm.getContext()
                .getUsers()
                .stream()
                .map(entity -> fromUserEntity(entity, userInfos.get(entity.getUserInfoId())))
                .collect(Collectors.toSet());
    }
}
