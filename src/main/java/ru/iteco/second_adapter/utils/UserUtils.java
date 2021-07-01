package ru.iteco.second_adapter.utils;

import ru.iteco.second_adapter.DbUserEntity;
import ru.iteco.second_adapter.DbUserInfoEntity;
import ru.iteco.second_adapter.user.User;
import ru.iteco.second_adapter.user.UserInfo;

public class UserUtils {

    public static DbUserEntity fromUser(User user) {
        return new DbUserEntity(user.getId(), user.getLogin(), user.getPassword(), user.getUserInfo().getId());
    }

    public static DbUserInfoEntity fromUserInfo(UserInfo userInfo) {
        return new DbUserInfoEntity(userInfo.getId(), userInfo.getName(), userInfo.getBirthDay());
    }

    public static User fromUserEntity(DbUserEntity userEntity, UserInfo userInfo) {
        return new User(userEntity.getId(), userEntity.getLogin(), userEntity.getPassword(), userInfo);
    }

    public static UserInfo fromUserInfoEntity(DbUserInfoEntity userInfoEntity) {
        return new UserInfo(userInfoEntity.getId(), userInfoEntity.getName(), userInfoEntity.getBirthday());
    }

    public static int toInt(Long value) {
        return (int) value.longValue();
    }

}
