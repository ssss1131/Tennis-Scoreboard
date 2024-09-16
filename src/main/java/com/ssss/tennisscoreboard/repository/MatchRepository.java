package com.ssss.tennisscoreboard.repository;

import com.ssss.tennisscoreboard.entity.Match;
import jakarta.persistence.*;
import org.hibernate.graph.GraphSemantic;

import java.util.List;
import java.util.Map;

public class MatchRepository extends BaseRepository<Long, Match> {

    private static final String FIND_ALL = "SELECT m FROM Match m";

    private static final String FIND_WITH_FILTERS = FIND_ALL +
                                                    " WHERE m.player1.name LIKE :filter " +
                                                    "OR m.player2.name LIKE :filter ";


    public MatchRepository(EntityManager entityManager) {
        super(Match.class, entityManager);
    }

    public List<Match> findFilteredMatches(String filter, int pageSize, int page) {
        String filterPattern = "%" + filter + "%";
        EntityGraph<Match> graph = getEntityManager().createEntityGraph(Match.class);
        graph.addAttributeNodes("player1", "player2", "winner");
        return getEntityManager().createQuery(FIND_WITH_FILTERS, Match.class)
                .setHint(GraphSemantic.LOAD.getJakartaHintName(), graph)
                .setParameter("filter", filterPattern)
                .setFirstResult(page * pageSize)
                .setMaxResults(pageSize)
                .getResultList();
    }

    public List<Match> findFilteredMatches(int pageSize, int page) {
        EntityGraph<Match> graph = getEntityManager().createEntityGraph(Match.class);
        graph.addAttributeNodes("player1", "player2", "winner");
        return getEntityManager().createQuery(FIND_ALL, Match.class)
                .setHint(GraphSemantic.LOAD.getJakartaHintName(), graph)
                .setFirstResult(page * pageSize)
                .setMaxResults(pageSize)
                .getResultList();
    }


    public List<Match> findAll() {
        return getEntityManager()
                .createQuery(FIND_ALL, Match.class)
                .getResultList();
    }

}
