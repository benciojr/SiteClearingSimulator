package com.simulator.app.factory;

import com.simulator.app.exception.FactoryException;

public interface AbstractFactory<T> {
    T create(String... parameters) throws FactoryException;
}
