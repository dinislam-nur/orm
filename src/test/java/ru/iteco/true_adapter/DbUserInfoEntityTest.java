package ru.iteco.true_adapter;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import ru.iteco.true_adapter.adapter.Adapter;
import ru.iteco.true_adapter.adapter.AdapterImpl;
import ru.iteco.true_adapter.db.Db;
import ru.iteco.true_adapter.db.DbImpl;
import ru.iteco.true_adapter.entity.DbUserInfoEntity;
import ru.iteco.true_adapter.first.FirstOrmImpl;
import ru.iteco.true_adapter.second.SecondOrmImpl;
import ru.iteco.true_adapter.second.UserInfoContext;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class DbUserInfoEntityTest {

    private final Db<DbUserInfoEntity> userInfoDb = new DbImpl<>();
    private final Adapter<DbUserInfoEntity> first = new AdapterImpl<>(new FirstOrmImpl<>(userInfoDb));
    private final Adapter<DbUserInfoEntity> second = new AdapterImpl<>(new SecondOrmImpl(new UserInfoContext(userInfoDb)));

    private DbUserInfoEntity entity;

    @BeforeEach
    void setUp() {
        entity = new DbUserInfoEntity(1L, "vasya", LocalDateTime.now());
        userInfoDb.add(entity);
    }

    @AfterEach
    void tearDown() {
        userInfoDb.deleteAll();
    }

    @Test
    public void createTest() {
        final DbUserInfoEntity userInfoEntity = new DbUserInfoEntity(99L, "sanya", LocalDateTime.now());
        assertNull(userInfoDb.get(99L));

        first.create(userInfoEntity);

        assertEquals(userInfoEntity, userInfoDb.get(99L));

        checkThrow(() -> second.create(userInfoEntity));
    }

    @Test
    public void updateTest() {
        entity = new DbUserInfoEntity(1L, "peter", LocalDateTime.now());
        assertNotEquals(entity, userInfoDb.get(1L));

        first.update(entity);
        assertEquals(entity, userInfoDb.get(1L));

        checkThrow(() -> second.create(entity));
    }

    @Test
    public void readTest() {
        assertEquals(entity, first.read(1L));
        assertEquals(entity, second.read(1L));
    }

    @Test
    public void deleteTest() {
        assertNotNull(userInfoDb.get(1L));

        first.delete(entity);

        assertNull(userInfoDb.get(1L));

        checkThrow(() -> second.delete(entity));
    }

    private void checkThrow(Executable executable) {
        assertThrows(UnsupportedOperationException.class, executable);
    }

}