package com.ssss.tennisscoreboard.repository;

import com.ssss.tennisscoreboard.model.BaseEntity;

import java.io.Serializable;

public interface Repository<K extends Serializable, E extends BaseEntity<K>> {

    E save(E entity);

}
