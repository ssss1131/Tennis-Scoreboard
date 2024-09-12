package com.ssss.tennisscoreboard.repository;

import com.ssss.tennisscoreboard.entity.BaseEntity;
import jakarta.persistence.EntityManager;
import lombok.Getter;

import java.io.Serializable;

@Getter
public abstract class BaseRepository<K extends Serializable, E extends BaseEntity<K>> implements Repository<K, E>{

    private final Class<E> clazz;
    private final EntityManager entityManager;

    public BaseRepository(Class<E> clazz,EntityManager entityManager) {
        this.clazz = clazz;
        this.entityManager = entityManager;
    }

    @Override
    public E save(E entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Override
    public E findById(K id) {
        return entityManager.find(clazz, id);
    }
}
