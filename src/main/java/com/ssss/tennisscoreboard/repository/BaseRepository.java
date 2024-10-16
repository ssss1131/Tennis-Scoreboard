package com.ssss.tennisscoreboard.repository;

import com.ssss.tennisscoreboard.model.BaseEntity;
import jakarta.persistence.EntityManager;
import lombok.Getter;

import java.io.Serializable;

@Getter
public abstract class BaseRepository<K extends Serializable, E extends BaseEntity<K>> implements Repository<K, E>{

    private final EntityManager entityManager;

    public BaseRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public E save(E entity) {
        entityManager.persist(entity);
        return entity;
    }
}
