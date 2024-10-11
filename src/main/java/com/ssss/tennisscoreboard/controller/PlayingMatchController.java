package com.ssss.tennisscoreboard.controller;

import com.ssss.tennisscoreboard.service.match.CurrentMatch;
import com.ssss.tennisscoreboard.dto.PlayingMatchInfo;
import com.ssss.tennisscoreboard.service.OnGoingMatchesService;
import com.ssss.tennisscoreboard.service.TennisScoreCalculatorService;
import com.ssss.tennisscoreboard.util.JspPathFinder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@WebServlet("/match-score")
public class PlayingMatchController extends HttpServlet {

    private OnGoingMatchesService onGoingMatchesService;
    private TennisScoreCalculatorService calculatorService;

    @Override
    public void init() throws ServletException {
        super.init();
        onGoingMatchesService = new OnGoingMatchesService();
        calculatorService = new TennisScoreCalculatorService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UUID uuid = UUID.fromString(req.getParameter("uuid"));
        CurrentMatch match = onGoingMatchesService.getMatch(uuid);
        req.setAttribute("match", match);
        req.getRequestDispatcher(JspPathFinder.getPath("Match")).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long scoredId = Long.valueOf(req.getParameter("playerId"));
        UUID uuid = UUID.fromString(req.getParameter("uuid"));
        CurrentMatch match = onGoingMatchesService.getMatch(uuid);
        PlayingMatchInfo playingMatchInfo = PlayingMatchInfo.builder()
                .player1(match.getFirstPlayer())
                .player2(match.getSecondPlayer())
                .scoredId(scoredId)
                .build();
        calculatorService.calculate(playingMatchInfo);
        Optional<String> maybeWinner = onGoingMatchesService.processMatchCompletion(uuid);
        if(maybeWinner.isPresent()){
            req.setAttribute("winner", maybeWinner.get());
            req.getRequestDispatcher(JspPathFinder.getPath("Winner")).forward(req, resp);
        }else{
            resp.sendRedirect(String.format("%s/match-score?uuid=%s", req.getContextPath(), match.getUuid()));
        }
    }
}
