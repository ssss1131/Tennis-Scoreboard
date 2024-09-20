package com.ssss.tennisscoreboard.service;

import com.ssss.tennisscoreboard.dto.CurrentMatch;
import com.ssss.tennisscoreboard.dto.TennisPlayerMatchInfo;
import com.ssss.tennisscoreboard.dto.some.TennisScore;
import com.ssss.tennisscoreboard.entity.Match;
import com.ssss.tennisscoreboard.entity.Player;
import com.ssss.tennisscoreboard.mapper.ToPlayerMapper;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class OnGoingMatchesService {
    private static final Map<UUID, CurrentMatch> currentMatches = new ConcurrentHashMap<>();
    private final MatchRepositoryService matchRepositoryService = new MatchRepositoryService();

    public CurrentMatch getMatch(UUID uuid) {
        return currentMatches.get(uuid);
    }

    public CurrentMatch createNewMatch(Player firstPlayer, Player secondPlayer) {
        Optional<CurrentMatch> ongoingMatchByPlayers = findOngoingMatchByPlayers(firstPlayer.getName().toLowerCase(), secondPlayer.getName().toLowerCase());
        if(ongoingMatchByPlayers.isPresent()){
            return ongoingMatchByPlayers.get();
        }

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

    public Optional<String> processMatchCompletion(UUID uuid) {
        CurrentMatch currentMatch = currentMatches.get(uuid);
        TennisPlayerMatchInfo firstPlayer = currentMatch.getFirstPlayer();
        TennisPlayerMatchInfo secondPlayer = currentMatch.getSecondPlayer();
        if (firstPlayer.getScore().getSets() == 2 || secondPlayer.getScore().getSets() == 2) {
            removeFromMap(uuid);

            Match match = Match.builder()
                    .player1(ToPlayerMapper.mapToPlayer(firstPlayer))
                    .player2(ToPlayerMapper.mapToPlayer(secondPlayer))
                    .build();

            if (firstPlayer.getScore().getSets() == 2) {
                match.setWinner(match.getPlayer1());
                matchRepositoryService.createNewMatch(match);
                return Optional.of(firstPlayer.getName());
            }

            match.setWinner(match.getPlayer2());
            matchRepositoryService.createNewMatch(match);
            return Optional.of(secondPlayer.getName());
        }
        return Optional.empty();

    }

    private Optional<CurrentMatch> findOngoingMatchByPlayers(String playerOneName, String playerTwoName) {
        for (CurrentMatch match : currentMatches.values()) {
            String firstPlayerNameInMatch = match.getFirstPlayer().getName().toLowerCase();
            String secondPlayerNameInMatch = match.getSecondPlayer().getName().toLowerCase();

            if ((firstPlayerNameInMatch.equals(playerOneName) && secondPlayerNameInMatch.equals(playerTwoName)) ||
                (firstPlayerNameInMatch.equals(playerTwoName) && secondPlayerNameInMatch.equals(playerOneName))) {
                return Optional.of(match);
            }
        }
        return Optional.empty();
    }

    private void removeFromMap(UUID uuid) {
        currentMatches.remove(uuid);
    }


}
