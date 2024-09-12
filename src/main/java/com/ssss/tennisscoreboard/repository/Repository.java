package com.ssss.tennisscoreboard.repository;

import com.ssss.tennisscoreboard.entity.BaseEntity;

import java.io.Serializable;

public interface Repository<K extends Serializable, E extends BaseEntity<K>> {

    E save(E entity);

    E findById(K id);


}
