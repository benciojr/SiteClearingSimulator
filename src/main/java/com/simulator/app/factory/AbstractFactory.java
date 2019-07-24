package com.simulator.app.factory;

import com.simulator.app.exception.InvalidSiteMapException;

public interface AbstractFactory<T> {
    T create(String type) throws InvalidSiteMapException;
}
