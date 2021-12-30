package com.example.javalab2.dao;

import com.example.javalab2.domain.Entity;

import java.util.List;

public abstract class AbstractDAO<K, T extends Entity> {
    public abstract List<T> findAll();
    public abstract T findById(K id);
    public abstract boolean delete(K id);
    public abstract T create(T entity);
    public abstract T update(T entity);
}
