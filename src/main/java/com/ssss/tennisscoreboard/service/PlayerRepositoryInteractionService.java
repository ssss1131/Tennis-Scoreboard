package com.ssss.tennisscoreboard.service;

import com.ssss.tennisscoreboard.entity.Player;
import com.ssss.tennisscoreboard.repository.PlayerRepository;
import com.ssss.tennisscoreboard.util.HibernateUtils;
import lombok.Cleanup;
import org.hibernate.Session;

public class PlayerRepositoryInteractionService {

    public Player createNewUser(String name) {
        @Cleanup Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Player player = Player.builder()
                .name(name)
                .build();
        PlayerRepository playerRepository = new PlayerRepository(session);
        //TODO оптимизировать тут сделать все одним запросом
        if (playerRepository.isNotExist(name)) {
            playerRepository.save(player);
        }else{
            player = playerRepository.findByName(name);
        }
        session.getTransaction().commit();
        return player;
    }
}
