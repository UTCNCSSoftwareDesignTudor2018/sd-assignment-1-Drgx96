package com.assig.assig1.DAO;

import java.util.Map;

public interface GenericDAO<T> {
    T get(Integer id);

    Map<Integer, T> list();

    int add(T t, boolean assignId);

    void update(T t);

    void delete(Integer id);
}
