package com.ssss.tennisscoreboard.service;

import com.ssss.tennisscoreboard.dto.CurrentMatch;
import com.ssss.tennisscoreboard.dto.TennisPlayerMatchInfo;
import com.ssss.tennisscoreboard.dto.TennisScore;
import com.ssss.tennisscoreboard.entity.Player;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class OnGoingMatchesService {
    private static final Map<UUID, CurrentMatch> currentMatches = new ConcurrentHashMap<>();

    public CurrentMatch getMatch(UUID uuid) {
        return currentMatches.get(uuid);
    }

    public CurrentMatch createNewMatch(Player firstPlayer, Player secondPlayer) {
        UUID uuid = UUID.randomUUID();
        CurrentMatch newMatch = CurrentMatch.builder()
                .uuid(uuid)
                .firstPlayer(TennisPlayerMatchInfo.builder()
                        .id(firstPlayer.getId())
                        .name(firstPlayer.getName())
                        .score(new TennisScore())
                        .build())
                .secondPlayer(TennisPlayerMatchInfo.builder()
                        .id(secondPlayer.getId())
                        .name(secondPlayer.getName())
                        .score(new TennisScore())
                        .build()
                )
                .build();
        currentMatches.put(uuid, newMatch);
        return newMatch;
    }


}
