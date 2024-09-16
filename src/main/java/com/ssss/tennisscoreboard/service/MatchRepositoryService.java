package com.ssss.tennisscoreboard.service;

import com.ssss.tennisscoreboard.entity.Match;
import com.ssss.tennisscoreboard.repository.MatchRepository;
import com.ssss.tennisscoreboard.util.HibernateUtils;
import lombok.Cleanup;
import org.hibernate.Session;

import java.util.List;

public class MatchRepositoryService {

    private MatchRepository matchRepository;

    //TODO попробовать убрать Cleanup ну почитать короч можно ли
    public Match createNewMatch(Match match) {
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        matchRepository = new MatchRepository(session);
        matchRepository.save(match);
        session.getTransaction().commit();
        return match;
    }

    public List<Match> getMatchesWithFilter(String filter, int pageSize, int page) {
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        matchRepository = new MatchRepository(session);
        List<Match> matches;
        page = page - 1;
        if (filter != null && !filter.trim().isEmpty()) {
            matches = matchRepository.findFilteredMatches(filter, pageSize, page);
        } else {
            matches = matchRepository.findFilteredMatches(pageSize, page);
        }
        session.getTransaction().commit();

        return matches;
    }

    public int getAllPagesCountWithPageSize(int pageSize) {
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        matchRepository = new MatchRepository(session);
        List<Match> matches = matchRepository.findAll();
        session.getTransaction().commit();
        return (matches.size() + pageSize - 1) / pageSize;
    }
}
