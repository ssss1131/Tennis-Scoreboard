package com.ssss.tennisscoreboard.service;

import com.ssss.tennisscoreboard.model.Match;
import com.ssss.tennisscoreboard.repository.MatchRepository;
import com.ssss.tennisscoreboard.util.HibernateUtils;
import com.ssss.tennisscoreboard.util.UserInputValidator;
import org.hibernate.Session;

import java.util.List;

public class MatchRepositoryService {

    private MatchRepository matchRepository;

    public Match createNewMatch(Match match) {
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        matchRepository = new MatchRepository(session);
        matchRepository.save(match);
        session.getTransaction().commit();
        return match;
    }

    public List<Match> getMatchesWithFilter(String filter, int pageSize, String maybePage) {
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        matchRepository = new MatchRepository(session);
        int page = UserInputValidator.validatePage(maybePage);
        List<Match> matches = matchRepository.findFilteredMatches(filter, pageSize, page);
        session.getTransaction().commit();
        return matches;
    }

    public List<Match> getMatchesWithFilter(int pageSize, String maybePage) {
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        matchRepository = new MatchRepository(session);
        int page = UserInputValidator.validatePage(maybePage);
        List<Match> matches = matchRepository.findFilteredMatches(pageSize, page);
        session.getTransaction().commit();
        return matches;
    }

    public int getAllPages(int pageSize) {
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        matchRepository = new MatchRepository(session);
        int matches = matchRepository.findCountOfAll();
        session.getTransaction().commit();
        return (matches + pageSize - 1) / pageSize;
    }

    public int getAllFilteredPages(String filter, int pageSize) {
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        matchRepository = new MatchRepository(session);
        int matches = matchRepository.findCountOfFilteredPlayers(filter);
        session.getTransaction().commit();
        return (matches + pageSize - 1) / pageSize;
    }
}
