package com.ssss.tennisscoreboard.service;

import com.ssss.tennisscoreboard.model.Match;
import com.ssss.tennisscoreboard.repository.MatchRepository;
import com.ssss.tennisscoreboard.util.HibernateUtils;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;

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

    public List<Match> getMatchesWithFilter(Optional<String> filter, int pageSize, int page) {
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        try {
            startTransaction(session);
            matchRepository = new MatchRepository(session);
            List<Match> matches = matchRepository.findFilteredMatches(filter, pageSize, page);
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
            int matches = matchRepository.findCountOfFilteredMatches(filter);
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

