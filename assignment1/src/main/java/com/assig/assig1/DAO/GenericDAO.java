package com.assig.assig1.DAO;

import java.util.Map;

public interface GenericDAO<T> {
	T get(Integer id);

	Map<Integer, T> list();

	void add(T t);

	void update(T t);

	void delete(Integer id);
}