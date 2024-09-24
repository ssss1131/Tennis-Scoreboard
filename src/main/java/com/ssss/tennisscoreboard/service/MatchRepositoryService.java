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
        try {
            startTransaction(session);
            matchRepository = new MatchRepository(session);
            matchRepository.save(match);
            commitTransaction(session);
            return match;
        } catch (Exception e) {
            rollbackTransaction(session);
            throw e;
        }
    }

    public List<Match> getMatchesWithFilter(String filter, int pageSize, String maybePage) {
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        try {
            startTransaction(session);
            matchRepository = new MatchRepository(session);
            int page = UserInputValidator.validatePage(maybePage);
            List<Match> matches = matchRepository.findFilteredMatches(filter, pageSize, page);
            commitTransaction(session);
            return matches;
        } catch (Exception e) {
            rollbackTransaction(session);
            throw e;
        }
    }

    public List<Match> getMatchesWithFilter(int pageSize, String maybePage) {
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        try {
            startTransaction(session);
            matchRepository = new MatchRepository(session);
            int page = UserInputValidator.validatePage(maybePage);
            List<Match> matches = matchRepository.findFilteredMatches(pageSize, page);
            commitTransaction(session);
            return matches;
        } catch (Exception e) {
            rollbackTransaction(session);
            throw e;
        }
    }

    public int getAllPages(int pageSize) {
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        try {
            startTransaction(session);
            matchRepository = new MatchRepository(session);
            int matches = matchRepository.findCountOfAll();
            commitTransaction(session);
            return (matches + pageSize - 1) / pageSize;
        } catch (Exception e) {
            rollbackTransaction(session);
            throw e;
        }
    }

    public int getAllFilteredPages(String filter, int pageSize) {
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        try {
            startTransaction(session);
            matchRepository = new MatchRepository(session);
            int matches = matchRepository.findCountOfFilteredPlayers(filter);
            commitTransaction(session);
            return (matches + pageSize - 1) / pageSize;
        } catch (Exception e) {
            rollbackTransaction(session);
            throw e;
        }
    }

    private void startTransaction(Session session) {
        if (!session.getTransaction().isActive()) {
            session.beginTransaction();
        }
    }

    private void commitTransaction(Session session) {
        if (session.getTransaction().isActive()) {
            session.getTransaction().commit();
        }
    }

    private void rollbackTransaction(Session session) {
        if (session.getTransaction().isActive()) {
            session.getTransaction().rollback();
        }
    }
}

