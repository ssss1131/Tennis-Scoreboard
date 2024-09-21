package com.ssss.tennisscoreboard.service;

import com.ssss.tennisscoreboard.model.Player;
import com.ssss.tennisscoreboard.repository.PlayerRepository;
import com.ssss.tennisscoreboard.util.HibernateUtils;
import org.hibernate.Session;

import java.util.Optional;

public class PlayerRepositoryService {

    public Player createNewUser(String name) {
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        PlayerRepository playerRepository = new PlayerRepository(session);
        Optional<Player> maybePlayer = playerRepository.findByName(name);
        Player player;
        if (maybePlayer.isEmpty()) {
            player = Player.builder()
                    .name(name)
                    .build();
            playerRepository.save(player);
        }else {
            player = maybePlayer.get();
        }
        session.getTransaction().commit();
        return player;
    }
}
