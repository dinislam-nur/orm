package ru.iteco.second_adapter.adapter;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.iteco.second_adapter.Db;
import ru.iteco.second_adapter.DbImpl;
import ru.iteco.second_adapter.DbUserEntity;
import ru.iteco.second_adapter.DbUserInfoEntity;
import ru.iteco.second_adapter.second.SecondOrmImpl;
import ru.iteco.second_adapter.user.User;
import ru.iteco.second_adapter.user.UserInfo;

import java.time.LocalDateTime;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static ru.iteco.second_adapter.utils.UserUtils.*;

class UserAdapterImplTest {

    private static final Db<DbUserEntity> userDb = new DbImpl<>();
    private static final Db<DbUserInfoEntity> userInfoDb = new DbImpl<>();
    private static final Adapter<User, DbUserEntity> userAdapter;
    private final User testUser;
    private final UserInfo testUserInfo;

    static {
        final SecondOrmImpl secondOrm = new SecondOrmImpl(userDb, userInfoDb);
        final UserInfoAdapterImpl userInfoAdapter = new UserInfoAdapterImpl(userInfoDb, secondOrm);
        userAdapter = new UserAdapterImpl(userDb, secondOrm, userInfoAdapter);
    }

    {
        testUserInfo = new UserInfo(1L, "Vasya", LocalDateTime.now());
        testUser = new User(1L, "login1", "password1", testUserInfo);
    }

    @BeforeEach
    void setUp() {
        userInfoDb.add(fromUserInfo(testUserInfo));
        userInfoDb.add(new DbUserInfoEntity(2L, "Ivan", LocalDateTime.now()));
        userInfoDb.add(new DbUserInfoEntity(3L, "Peter", LocalDateTime.now()));

        userDb.add(fromUser(testUser));
        userDb.add(new DbUserEntity(2L, "login2", "password2", 2L));
        userDb.add(new DbUserEntity(3L, "login3", "password3", 3L));
    }

    @AfterEach
    void tearDown() {
        userInfoDb.deleteAll();
        userDb.deleteAll();
    }

    @Test
    public void createTest() {
        final User user = new User(99L, "admin", "admin",
                new UserInfo(99L, "Sanya", LocalDateTime.now()));
        userAdapter.create(user);

        final DbUserInfoEntity actualUserInfo = userInfoDb.get(99L);
        assertNotNull(actualUserInfo);
        assertEquals("Sanya", actualUserInfo.getName());
        assertEquals(4, userInfoDb.getAll().size());

        final DbUserEntity actualUser = userDb.get(99L);
        assertEquals("admin", actualUser.getLogin());
        assertEquals("admin", actualUser.getPassword());
        assertEquals(99L, actualUser.getUserInfoId());
        assertEquals(4, userDb.getAll().size());
    }

    @Test
    public void readTest() {
        final User actual = userAdapter.read(1L);
        assertEquals(testUser, actual);
    }

    @Test
    public void updateTest() {
        final User updated = new User(1L, "super", "super",
                new UserInfo(1L, "Superman", LocalDateTime.now()));
        userAdapter.update(updated);

        final DbUserEntity userEntity = userDb.get(1L);
        assertNotNull(userEntity);
        assertEquals(updated.getLogin(), userEntity.getLogin());
        assertEquals(updated.getPassword(), userEntity.getPassword());

        final DbUserInfoEntity userInfoEntity = userInfoDb.get(1L);
        assertNotNull(userInfoEntity);
        assertEquals(updated.getUserInfo().getName(), userInfoEntity.getName());
    }

    @Test
    public void deleteTest() {
        userAdapter.delete(testUser);

        final Set<DbUserEntity> users = userDb.getAll();
        assertEquals(2, users.size());
        assertTrue(users.stream().noneMatch(user -> user.equals(fromUser(testUser))));

        final Set<DbUserInfoEntity> userInfos = userInfoDb.getAll();
        assertEquals(2, userInfos.size());
        assertTrue(userInfos.stream().noneMatch(userInfo -> userInfo.equals(fromUserInfo(testUserInfo))));
    }

    @Test
    public void getAllTest() {
        final Set<User> all = userAdapter.getAll();
        assertEquals(3, all.size());
    }
}