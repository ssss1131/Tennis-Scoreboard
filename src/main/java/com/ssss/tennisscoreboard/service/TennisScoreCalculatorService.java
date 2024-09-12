package com.ssss.tennisscoreboard.service;

import com.ssss.tennisscoreboard.dto.TennisDeuceScore;
import com.ssss.tennisscoreboard.dto.TennisScore;

public class TennisScoreCalculatorService {
    //TODO разделить на методы
    //TODO убрать магические цифры

    public void calculate(TennisScore scoredPlayerScore, TennisScore opponentScore) {
        if (scoredPlayerScore.isTiebreak() && opponentScore.isTiebreak()) {
            calculateTieBreak(scoredPlayerScore, opponentScore);
        } else {
            if (scoredPlayerScore.isDeuce() || opponentScore.isDeuce()) {
                calculateDeuce(scoredPlayerScore, opponentScore);
                return;
            }

            int pointsOpponent = opponentScore.getPoints();
            int pointsScored = calculatePoints(scoredPlayerScore.getPoints());
            scoredPlayerScore.setPoints(pointsScored);

            int gamesOpponent = opponentScore.getGames();
            int gamesScored = scoredPlayerScore.getGames();

            if (pointsOpponent == 40 && pointsScored == 40) {
                scoredPlayerScore.setDeuce(true);
                opponentScore.setDeuce(true);
                scoredPlayerScore.setPoints(0);
                opponentScore.setPoints(0);
            } else if (pointsScored == 0) {
                if (!(gamesScored == 5 && gamesOpponent == 6)) {
                    if (gamesScored < 5 || (gamesScored==5 && gamesOpponent == 5)) {
                        scoredPlayerScore.setGames(gamesScored + 1);
                    } else {
                        scoredPlayerScore.setGames(0);
                        scoredPlayerScore.setSets(scoredPlayerScore.getSets() + 1);
                    }
                } else {
                    scoredPlayerScore.setGames(gamesScored + 1);
                    scoredPlayerScore.setTiebreak(true);
                    opponentScore.setTiebreak(true);
                }
            }
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
        if (tiebreakPointsScored >= 6 && tiebreakPointsScored >= tiebreakPointsOpponent + 1) {
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
        if (points < 30) {
            points += 15;
        } else if (points == 30) {
            points += 10;
        } else {
            points = 0;
        }
        return points;
    }
}
