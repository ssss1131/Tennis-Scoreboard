package com.ssss.tennisscoreboard.repository;

import com.ssss.tennisscoreboard.entity.BaseEntity;

import java.io.Serializable;
import java.util.List;

public interface Repository<K extends Serializable, E extends BaseEntity<K>> {

    E save(E entity);

}
