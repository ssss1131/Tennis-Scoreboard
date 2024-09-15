package com.sss.tennisscoreboard.service;

import com.ssss.tennisscoreboard.dto.PlayingMatchInfo;
import com.ssss.tennisscoreboard.dto.TennisPlayerMatchInfo;
import com.ssss.tennisscoreboard.dto.some.TennisDeuceScore;
import com.ssss.tennisscoreboard.dto.some.TennisScore;
import com.ssss.tennisscoreboard.entity.Player;
import com.ssss.tennisscoreboard.service.TennisScoreCalculatorService;
import org.h2.mode.ToDateParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TennisScoreCalculatorServiceTest {

    private TennisScoreCalculatorService tennisScoreCalculatorService;
    private PlayingMatchInfo match;
    private TennisPlayerMatchInfo player1;
    private TennisPlayerMatchInfo player2;

    @BeforeEach
    void init(){
        tennisScoreCalculatorService = new TennisScoreCalculatorService();

        player1 = TennisPlayerMatchInfo.builder()
                .id(1L)
                .score(new TennisScore())
                .build();

        player2 = TennisPlayerMatchInfo.builder()
                .id(2L)
                .score(new TennisScore())
                .build();

        match = PlayingMatchInfo.builder()
                .player1(player1)
                .player2(player2)
                .build();
    }

    @Test
    void shouldWinGame(){
        player1.getScore().setPoints(40);
        match.setScoredId(player1.getId());
        tennisScoreCalculatorService.calculate(match);

        assertEquals(1, player1.getScore().getGames());
        assertEquals(0, player1.getScore().getPoints());

        assertEquals(0, player2.getScore().getGames());
    }

    @Test
    void shouldWinSet(){
        player1.getScore().setGames(5);
        player1.getScore().setPoints(40);

        match.setScoredId(player1.getId());

        tennisScoreCalculatorService.calculate(match);

        assertEquals(1, player1.getScore().getSets());
        assertEquals(0, player1.getScore().getGames());
        assertEquals(0, player1.getScore().getPoints());
    }

    @Test
    void shouldCancelOpponentPoints(){
        player1.getScore().setPoints(40);
        player2.getScore().setPoints(30);
        match.setScoredId(player1.getId());
        tennisScoreCalculatorService.calculate(match);

        assertEquals(0, player2.getScore().getPoints());

    }

    @Test
    void shouldWinDeuce(){
        player1.getScore().setPoints(30);
        player2.getScore().setPoints(40);
        match.setScoredId(player1.getId());
        tennisScoreCalculatorService.calculate(match);

        assertTrue(player1.getScore().isDeuce());
        assertTrue(player2.getScore().isDeuce());

        tennisScoreCalculatorService.calculate(match);

        assertEquals(TennisDeuceScore.PLAYER_LEADING, player1.getScore().getDeucePointsPlayer());
        assertEquals(TennisDeuceScore.OPPONENT_LEADING, player2.getScore().getDeucePointsPlayer());

        tennisScoreCalculatorService.calculate(match);

        assertFalse(player1.getScore().isDeuce());
        assertFalse(player2.getScore().isDeuce());

        assertEquals(1, player1.getScore().getGames());
        assertEquals(0, player2.getScore().getGames());

        assertEquals(0, player1.getScore().getPoints());
        assertEquals(0, (player2.getScore().getPoints()));

    }

    @Test
    void shouldContinueBeDeuce(){
        player1.getScore().setDeuce(true);
        player2.getScore().setDeuce(true);
        match.setScoredId(player1.getId());
        tennisScoreCalculatorService.calculate(match);

        match.setScoredId(player2.getId());
        tennisScoreCalculatorService.calculate(match);

        assertEquals(TennisDeuceScore.EQUAL, player1.getScore().getDeucePointsPlayer());
        assertEquals(TennisDeuceScore.EQUAL, player2.getScore().getDeucePointsPlayer());

        tennisScoreCalculatorService.calculate(match);

        assertEquals(TennisDeuceScore.OPPONENT_LEADING, player1.getScore().getDeucePointsPlayer());
        assertEquals(TennisDeuceScore.PLAYER_LEADING, player2.getScore().getDeucePointsPlayer());

        match.setScoredId(player1.getId());
        tennisScoreCalculatorService.calculate(match);

        assertEquals(TennisDeuceScore.EQUAL, player1.getScore().getDeucePointsPlayer());
        assertEquals(TennisDeuceScore.EQUAL, player2.getScore().getDeucePointsPlayer());

    }

    @Test
    void shouldBeTieBreak(){
        player1.getScore().setGames(5);
        player1.getScore().setPoints(40);
        player2.getScore().setGames(6);
        match.setScoredId(player1.getId());

        tennisScoreCalculatorService.calculate(match);

        assertTrue(player1.getScore().isTiebreak());
        assertTrue(player2.getScore().isTiebreak());

        tennisScoreCalculatorService.calculate(match);
        tennisScoreCalculatorService.calculate(match);
        tennisScoreCalculatorService.calculate(match);

        assertEquals(6, player1.getScore().getGames());

        player2.getScore().setPoints(40);
        match.setScoredId(player2.getId());
        tennisScoreCalculatorService.calculate(match);
        assertEquals(6, player2.getScore().getGames());
    }

    @Test
    void shouldWinTieBreak(){
        player1.getScore().setTiebreak(true);
        player2.getScore().setTiebreak(true);
        match.setScoredId(player1.getId());
        for (int i = 0; i < 7; i++) {
            tennisScoreCalculatorService.calculate(match);
        }
        assertEquals(1, player1.getScore().getSets());
        assertEquals(0, player2.getScore().getSets());

    }

    @Test
    void shouldContinueBeTieBreak(){
        player1.getScore().setGames(6);
        player2.getScore().setGames(6);
        player1.getScore().setTiebreak(true);
        player2.getScore().setTiebreak(true);
        match.setScoredId(player1.getId());

        for (int i = 0; i < 6; i++) {
            tennisScoreCalculatorService.calculate(match);
        }

        match.setScoredId(player2.getId());

        for (int i = 0; i < 7; i++) {
            tennisScoreCalculatorService.calculate(match);
        }

        assertEquals(6, player1.getScore().getTiebreakPointsPlayer());
        assertEquals(7, player2.getScore().getTiebreakPointsPlayer());

        match.setScoredId(player1.getId());

        tennisScoreCalculatorService.calculate(match);
        tennisScoreCalculatorService.calculate(match);

        assertEquals(8, player1.getScore().getTiebreakPointsPlayer());
        assertEquals(7, player2.getScore().getTiebreakPointsPlayer());

        tennisScoreCalculatorService.calculate(match);

        assertFalse(player1.getScore().isTiebreak());
        assertFalse(player2.getScore().isTiebreak());

        assertEquals(1, player1.getScore().getSets());
        assertEquals(0, player2.getScore().getSets());

        assertEquals(0, player1.getScore().getGames());
        assertEquals(0, player2.getScore().getGames());




    }

}
