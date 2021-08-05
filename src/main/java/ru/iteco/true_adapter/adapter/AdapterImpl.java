package ru.iteco.true_adapter.adapter;

import ru.iteco.true_adapter.entity.DbEntity;
import ru.iteco.true_adapter.first.FirstOrm;
import ru.iteco.true_adapter.second.SecondOrm;
import ru.iteco.true_adapter.second.SecondOrmContext;
import ru.iteco.true_adapter.second.UserContext;
import ru.iteco.true_adapter.second.UserInfoContext;

import java.util.Set;
import java.util.function.Function;

public class AdapterImpl<O, DB extends DbEntity> implements Adapter<DB> {

    private final O orm;

    public AdapterImpl(O orm) {
        this.orm = orm;
    }

    @Override
    public void create(DB entity) {
        chooseAction(orm -> {
            orm.create(entity);
            return null;
        }, this::unsupported);
    }

    @Override
    public DB read(Long id) {
        return chooseAction(orm -> orm.read(id.intValue()),
                orm -> defineContext(orm.getContext(), id));
    }

    @Override
    public void update(DB entity) {
        chooseAction(orm -> {
            orm.update(entity);
            return null;
        }, this::unsupported);
    }

    @Override
    public void delete(DB entity) {
        chooseAction(orm -> {
            orm.delete(entity);
            return null;
        }, this::unsupported);
    }

    private DB chooseAction(Function<FirstOrm<DB>, DB> first, Function<SecondOrm, DB> second) {
        if (orm instanceof FirstOrm) {
            return first.apply((FirstOrm<DB>) orm);
        }
        if (orm instanceof SecondOrm) {
            return second.apply((SecondOrm) orm);
        }
        return null;
    }

    private DB unsupported(SecondOrm orm) {
        throw new UnsupportedOperationException();
    }

    private DB defineContext(SecondOrmContext context, Long id) {
        if (context instanceof UserContext) {
            return findElement(context.getUsers(), id);
        }
        if (context instanceof UserInfoContext) {
            return findElement(context.getUserInfos(), id);
        }
        return null;
    }

    private DB findElement(Set<? extends DbEntity> elements, Long id) {
        return (DB) elements.stream()
                .filter(entity -> id.equals(entity.getId()))
                .findFirst()
                .orElse(null);
    }
}
