package com.ssss.tennisscoreboard.service;

import com.ssss.tennisscoreboard.entity.Match;
import com.ssss.tennisscoreboard.repository.MatchRepository;
import com.ssss.tennisscoreboard.util.HibernateUtils;
import lombok.Cleanup;
import org.hibernate.Session;

public class MatchRepositoryService {

    private MatchRepository matchRepository;

    //TODO мб передавать boolean чтобы если что не удалять из памяти там
    public Match createNewMatch(Match match){
        @Cleanup Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        MatchRepository matchRepository = new MatchRepository(session);
        matchRepository.save(match);
        session.getTransaction().commit();
        return match;
    }

}
