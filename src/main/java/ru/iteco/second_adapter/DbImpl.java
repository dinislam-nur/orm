package ru.iteco.second_adapter;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class DbImpl<T extends DbEntity> implements Db<T> {

    private final Map<Long, T> data = new HashMap<>();

    @Override
    public void add(T entity) {
        data.put(entity.getId(), entity);
    }

    @Override
    public T get(Long id) {
        return data.get(id);
    }

    @Override
    public void delete(Long id) {
        data.remove(id);
    }

    @Override
    public Set<T> getAll() {
        return new HashSet<>(data.values());
    }

    @Override
    public void deleteAll() {
        data.clear();
    }
}
