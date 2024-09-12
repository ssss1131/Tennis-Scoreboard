package com.ssss.tennisscoreboard.repository;

import com.ssss.tennisscoreboard.entity.Player;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

public class PlayerRepository extends BaseRepository<Long, Player> {

    private final CriteriaBuilder cb;

    public PlayerRepository(EntityManager entityManager) {
        super(Player.class, entityManager);
        cb = getEntityManager().getCriteriaBuilder();
    }

    public boolean isNotExist(String name){
        return findByName(name) == null;
    }

    public Player findByName(String name){
        CriteriaQuery<Player> criteria = cb.createQuery(Player.class);
        Root<Player> player = criteria.from(Player.class);
        criteria.where(cb.equal(player.get("name"), name));
        try {
            return getEntityManager().createQuery(criteria).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }

    }

}
