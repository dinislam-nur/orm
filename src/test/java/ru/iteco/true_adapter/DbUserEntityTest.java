package ru.iteco.true_adapter;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import ru.iteco.true_adapter.adapter.Adapter;
import ru.iteco.true_adapter.adapter.AdapterImpl;
import ru.iteco.true_adapter.db.Db;
import ru.iteco.true_adapter.db.DbImpl;
import ru.iteco.true_adapter.entity.DbUserEntity;
import ru.iteco.true_adapter.first.FirstOrmImpl;
import ru.iteco.true_adapter.second.SecondOrmImpl;
import ru.iteco.true_adapter.second.UserContext;


import static org.junit.jupiter.api.Assertions.*;

class DbUserEntityTest {

    private final Db<DbUserEntity> userDb = new DbImpl<>();
    private final Adapter<DbUserEntity> first = new AdapterImpl<>(new FirstOrmImpl<>(userDb));
    private final Adapter<DbUserEntity> second = new AdapterImpl<>(new SecondOrmImpl(new UserContext(userDb)));

    private DbUserEntity entity;

    @BeforeEach
    void setUp() {
        entity = new DbUserEntity(1L, "test1", "test1", 1L);
        userDb.add(entity);
    }

    @AfterEach
    void tearDown() {
        userDb.deleteAll();
    }

    @Test
    public void createTest() {
        final DbUserEntity userEntity = new DbUserEntity(99L, "admin", "admin", 99L);
        assertNull(userDb.get(99L));

        first.create(userEntity);

        assertEquals(userEntity, userDb.get(99L));

        checkThrow(() -> second.create(userEntity));
    }

    @Test
    public void updateTest() {
        entity = new DbUserEntity(1L, "admin", "admin", 1L);
        assertNotEquals(entity, userDb.get(1L));

        first.update(entity);
        assertEquals(entity, userDb.get(1L));

        checkThrow(() -> second.create(entity));
    }

    @Test
    public void readTest() {
        assertEquals(entity, first.read(1L));
        assertEquals(entity, second.read(1L));
    }

    @Test
    public void deleteTest() {
        assertNotNull(userDb.get(1L));

        first.delete(entity);

        assertNull(userDb.get(1L));

        checkThrow(() -> second.delete(entity));
    }

    private void checkThrow(Executable executable) {
        assertThrows(UnsupportedOperationException.class, executable);
    }
}