package com.ssss.tennisscoreboard.repository;

import com.ssss.tennisscoreboard.model.Player;
import jakarta.persistence.EntityManager;

import java.util.Optional;

public class PlayerRepository extends BaseRepository<Long, Player> {

    private static final String FIND_BY_NAME_SQL = "SELECT p FROM Player p WHERE name = :name";

    public PlayerRepository(EntityManager entityManager) {
        super(entityManager);
    }

    public Optional<Player> findByName(String name) {
        return getEntityManager()
                .createQuery(FIND_BY_NAME_SQL, Player.class)
                .setParameter("name", name)
                .getResultList()
                .stream()
                .findFirst();

    }

}
