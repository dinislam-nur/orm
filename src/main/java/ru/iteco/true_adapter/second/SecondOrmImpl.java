package ru.iteco.true_adapter.second;

public class SecondOrmImpl implements SecondOrm {

    private final SecondOrmContext context;

    public SecondOrmImpl(SecondOrmContext context) {
        this.context = context;
    }

    @Override
    public SecondOrmContext getContext() {
        return context;
    }
}
