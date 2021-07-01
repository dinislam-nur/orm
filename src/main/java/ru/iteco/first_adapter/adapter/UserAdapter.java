package ru.iteco.first_adapter.adapter;

import ru.iteco.first_adapter.DbUserEntity;
import ru.iteco.first_adapter.DbUserInfoEntity;
import ru.iteco.first_adapter.factory.OrmFactory;
import ru.iteco.first_adapter.orm.second.SecondOrmContext;
import ru.iteco.first_adapter.user.User;
import ru.iteco.first_adapter.user.UserInfo;
import ru.iteco.first_adapter.orm.first.FirstOrm;
import ru.iteco.first_adapter.orm.second.SecondOrm;

import java.util.Map;
import java.util.Set;

import static java.util.function.Function.*;
import static java.util.stream.Collectors.*;
import static ru.iteco.first_adapter.utils.UserUtils.*;

public class UserAdapter implements UserRepository {

    private final FirstOrm<DbUserEntity> userFirstOrm;
    private final FirstOrm<DbUserInfoEntity> userInfoFirstOrm;
    private final SecondOrm secondOrm;


    public UserAdapter(OrmFactory ormFactory) {
        this.userFirstOrm = ormFactory.createUserFirstOrm();
        this.userInfoFirstOrm = ormFactory.createUserInfoFirstOrm();
        this.secondOrm = ormFactory.createSecondOrm();
    }


    @Override
    public void save(User user) {
        if (validCreate(user)) {
            userInfoFirstOrm.create(fromUserInfo(user.getUserInfo()));
            userFirstOrm.create(fromUser(user));
        } else {
            throw new IllegalStateException("user has already had existing state on db");
        }
    }

    private boolean validCreate(User user) {
        return userInfoFirstOrm.read(toInt(user.getUserInfo().getId())) == null &&
                secondOrm.getContext().getUsers().stream()
                        .noneMatch(userEntity -> userEntity.getId().equals(user.getId()) ||
                                userEntity.getLogin().equals(user.getLogin()));
    }

    @Override
    public User get(Long id) {
        return getUser(userFirstOrm.read(id.intValue()));
    }

    private User getUser(DbUserEntity userEntity) {
        User user = null;
        if (userEntity != null) {
            final DbUserInfoEntity userInfoEntity = userInfoFirstOrm.read(userEntity.getUserInfoId().intValue());
            if (userInfoEntity != null) {
                user = fromUserEntity(userEntity, userInfoEntity);
            }
        }
        return user;
    }

    @Override
    public User getByLogin(String login) {
        return secondOrm.getContext().getUsers().stream()
                .filter(userEntity -> userEntity.getLogin().equals(login))
                .findFirst()
                .map(this::getUser)
                .orElse(null);
    }

    @Override
    public Set<User> getByName(String name) {
        final SecondOrmContext context = secondOrm.getContext();
        final Map<Long, DbUserInfoEntity> userInfoEntityMap = context.getUserInfos().stream()
                .filter(userInfoEntity -> userInfoEntity.getName().equals(name))
                .collect(toMap(DbUserInfoEntity::getId, identity()));
        return context.getUsers().stream()
                .filter(userEntity -> userInfoEntityMap.containsKey(userEntity.getUserInfoId()))
                .map(userEntity -> fromUserEntity(userEntity, userInfoEntityMap.get(userEntity.getUserInfoId())))
                .collect(toSet());
    }

    @Override
    public UserInfo getUserInfo(Long id) {
        return fromUserInfoEntity(userInfoFirstOrm.read(id.intValue()));
    }

    @Override
    public void update(User user) {
        final DbUserInfoEntity userInfoEntityFromDb = userInfoFirstOrm.read(user.getUserInfo().getId().intValue());
        final DbUserInfoEntity userInfoEntity = fromUserInfo(user.getUserInfo());
        if (!userInfoEntity.equals(userInfoEntityFromDb)) {
            userInfoFirstOrm.update(userInfoEntity);
        }
        final DbUserEntity userEntityFromDb = userFirstOrm.read(user.getId().intValue());
        final DbUserEntity userEntity = fromUser(user);
        if (!userEntity.equals(userEntityFromDb)) {
            userFirstOrm.update(userEntity);
        }
    }

    @Override
    public void remove(User user) {
        userInfoFirstOrm.delete(userInfoFirstOrm.read(user.getUserInfo().getId().intValue()));
        userFirstOrm.delete(userFirstOrm.read(user.getId().intValue()));
    }
}
