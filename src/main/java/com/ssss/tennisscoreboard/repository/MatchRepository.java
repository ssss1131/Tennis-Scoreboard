package com.ssss.tennisscoreboard.repository;

import com.ssss.tennisscoreboard.model.Match;
import jakarta.persistence.*;
import org.hibernate.graph.GraphSemantic;

import java.util.List;
import java.util.Optional;

public class MatchRepository extends BaseRepository<Long, Match> {

    private static final String FIND_ALL = "SELECT m FROM Match m";

    private static final String FILTER_BY_NAMES = " WHERE m.player1.name LIKE :filter " +
                                                  "OR m.player2.name LIKE :filter";

    private static final String FIND_WITH_FILTERS = FIND_ALL + FILTER_BY_NAMES;

    private static final String FIND_COUNT_ALL = "SELECT COUNT(m) FROM Match m ";

    private static final String FIND_COUNT_WITH_FILTERS = FIND_COUNT_ALL + FILTER_BY_NAMES;

    public MatchRepository(EntityManager entityManager) {
        super(entityManager);
    }

    public List<Match> findFilteredMatches(Optional<String> filter, int pageSize, int page) {
        EntityGraph<Match> graph = getEntityManager().createEntityGraph(Match.class);
        graph.addAttributeNodes("player1", "player2", "winner");

        var query = getEntityManager().createQuery(filter.map(f -> FIND_WITH_FILTERS).orElse(FIND_ALL), Match.class)
                .setHint(GraphSemantic.LOAD.getJakartaHintName(), graph)
                .setFirstResult(page * pageSize)
                .setMaxResults(pageSize);
        filter.ifPresent(f -> query.setParameter("filter", "%" + f + "%"));

        return query.getResultList();
    }

    public int findCountOfFilteredMatches(String filter) {
        String filterPattern = "%" + filter + "%";
        return getEntityManager()
                .createQuery(FIND_COUNT_WITH_FILTERS, Long.class)
                .setParameter("filter", filterPattern)
                .getSingleResult()
                .intValue();
    }


    public int findCountOfAll() {
        return getEntityManager()
                .createQuery(FIND_COUNT_ALL, Long.class)
                .getSingleResult()
                .intValue();

    }

}
