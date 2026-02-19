package com.projet.repository;

import com.projet.database.Mysql;
import com.projet.entity.AbstractEntity;

import java.sql.Connection;
import java.util.ArrayList;

public abstract class AbstractRepository<T extends AbstractEntity> {
    protected Connection connection = Mysql.getConnexion();

    protected abstract T save(T entity);

    protected abstract T find(int id);

    protected abstract ArrayList<T> findAll();
}
