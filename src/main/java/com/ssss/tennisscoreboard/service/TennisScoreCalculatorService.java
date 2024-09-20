package com.ssss.tennisscoreboard.service;

import com.ssss.tennisscoreboard.dto.PlayingMatchInfo;
import com.ssss.tennisscoreboard.dto.some.TennisDeuceScore;
import com.ssss.tennisscoreboard.dto.some.TennisScore;

public class TennisScoreCalculatorService {

    private static final int POINTS_STEP_15 = 15;
    private static final int POINTS_STEP_10 = 10;
    private static final int THIRTY_POINTS = 30;
    private static final int MAX_POINTS_BEFORE_DEUCE = 40;
    private static final int MAX_GAMES_BEFORE_TIEBREAK = 6;
    private static final int ALMOST_TIEBREAK_GAMES = 5;

    public void calculate(PlayingMatchInfo info) {
        TennisScore scoredPlayerScore;
        TennisScore opponentScore;
        if(info.getScoredId().equals(info.getPlayer1().getId())){
            scoredPlayerScore = info.getPlayer1().getScore();
            opponentScore = info.getPlayer2().getScore();
        }else{
            scoredPlayerScore = info.getPlayer2().getScore();
            opponentScore = info.getPlayer1().getScore();
        }

        if (scoredPlayerScore.isTiebreak() && opponentScore.isTiebreak()) {
            calculateTieBreak(scoredPlayerScore, opponentScore);
        } else {
            calculateRegularGame(scoredPlayerScore, opponentScore);
        }
    }

    private void calculateRegularGame(TennisScore scoredPlayerScore, TennisScore opponentScore){
        if (scoredPlayerScore.isDeuce() || opponentScore.isDeuce()) {
            calculateDeuce(scoredPlayerScore, opponentScore);
            return;
        }
        int pointsOpponent = opponentScore.getPoints();
        int pointsScored = calculatePoints(scoredPlayerScore.getPoints());
        scoredPlayerScore.setPoints(pointsScored);

        if (isDeuce(pointsOpponent, pointsScored)) {
            scoredPlayerScore.setDeuce(true);
            opponentScore.setDeuce(true);
            scoredPlayerScore.setPoints(0);
            opponentScore.setPoints(0);
        } else if(pointsScored == 0){
            handleGameEnd(scoredPlayerScore, opponentScore);
        }

    }

    private void handleGameEnd(TennisScore scoredPlayerScore, TennisScore opponentScore) {
        int gamesScored = scoredPlayerScore.getGames();
        int gamesOpponent = opponentScore.getGames();

        if (isNotTieBreak(gamesScored, gamesOpponent)) {
            if (gamesScored < ALMOST_TIEBREAK_GAMES || (gamesScored==ALMOST_TIEBREAK_GAMES && gamesOpponent == ALMOST_TIEBREAK_GAMES)) {
                scoredPlayerScore.setGames(gamesScored + 1);
                opponentScore.setPoints(0);
            } else {
                scoredPlayerScore.setGames(0);
                scoredPlayerScore.setSets(scoredPlayerScore.getSets() + 1);
                opponentScore.setPoints(0);
            }
        } else {
            scoredPlayerScore.setGames(gamesScored + 1);
            scoredPlayerScore.setTiebreak(true);
            opponentScore.setTiebreak(true);
        }
    }

    private void calculateDeuce(TennisScore scoredPlayerScore, TennisScore opponentScore) {
        TennisDeuceScore deucePointsScored = scoredPlayerScore.getDeucePointsPlayer();
        TennisDeuceScore deucePointsOpponent = opponentScore.getDeucePointsPlayer();
        if (deucePointsScored.equals(TennisDeuceScore.PLAYER_LEADING)) {
            scoredPlayerScore.setGames(scoredPlayerScore.getGames() + 1);
            scoredPlayerScore.setDeuce(false);
            opponentScore.setDeuce(false);
        } else if (deucePointsScored.equals(TennisDeuceScore.EQUAL) && deucePointsOpponent.equals(TennisDeuceScore.EQUAL)) {
            scoredPlayerScore.setDeucePointsPlayer(TennisDeuceScore.PLAYER_LEADING);
            opponentScore.setDeucePointsPlayer(TennisDeuceScore.OPPONENT_LEADING);
            return;
        }
        scoredPlayerScore.setDeucePointsPlayer(TennisDeuceScore.EQUAL);
        opponentScore.setDeucePointsPlayer(TennisDeuceScore.EQUAL);
    }

    private void calculateTieBreak(TennisScore scoredPlayerScore, TennisScore opponentScore) {
        int tiebreakPointsScored = scoredPlayerScore.getTiebreakPointsPlayer();
        int tiebreakPointsOpponent = opponentScore.getTiebreakPointsPlayer();
        if (tiebreakPointsScored >= MAX_GAMES_BEFORE_TIEBREAK && tiebreakPointsScored >= tiebreakPointsOpponent + 1) {
            scoredPlayerScore.setSets(scoredPlayerScore.getSets() + 1);
            scoredPlayerScore.setGames(0);
            opponentScore.setGames(0);
            scoredPlayerScore.setTiebreak(false);
            opponentScore.setTiebreak(false);
        } else {
            scoredPlayerScore.setTiebreakPointsPlayer(scoredPlayerScore.getTiebreakPointsPlayer() + 1);
        }
    }

    private int calculatePoints(int points) {
        if (points < THIRTY_POINTS) {
            points += POINTS_STEP_15;
        } else if (points == THIRTY_POINTS) {
            points += POINTS_STEP_10;
        } else {
            points = 0;
        }
        return points;
    }

    private boolean isDeuce(int pointsOpponent, int pointsScored) {
        return pointsOpponent == MAX_POINTS_BEFORE_DEUCE  && pointsScored == MAX_POINTS_BEFORE_DEUCE ;
    }

    private static boolean isNotTieBreak(int gamesScored, int gamesOpponent) {
        return !(gamesScored == ALMOST_TIEBREAK_GAMES && gamesOpponent == MAX_GAMES_BEFORE_TIEBREAK);
    }
}
