package com.ssss.tennisscoreboard.controller;

import com.ssss.tennisscoreboard.dto.CurrentMatch;
import com.ssss.tennisscoreboard.dto.TennisPlayerMatchInfo;
import com.ssss.tennisscoreboard.service.OnGoingMatchesService;
import com.ssss.tennisscoreboard.service.TennisScoreCalculatorService;
import com.ssss.tennisscoreboard.util.JspPathFinder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.UUID;

import static jakarta.servlet.http.HttpServletResponse.SC_OK;

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
        TennisPlayerMatchInfo firstPlayer = match.getFirstPlayer();
        TennisPlayerMatchInfo secondPlayer = match.getSecondPlayer();
        if(scoredId.equals(firstPlayer.getId())){
           calculatorService.calculate(firstPlayer.getScore(), secondPlayer.getScore());
           if(firstPlayer.getScore().getSets() == 2){
               req.setAttribute("winner", firstPlayer.getName());
               resp.setStatus(SC_OK);
               req.getRequestDispatcher(JspPathFinder.getPath("Winner")).forward(req, resp);
           }

        } else if (scoredId.equals(secondPlayer.getId())) {
            calculatorService.calculate(secondPlayer.getScore(), firstPlayer.getScore());
            if(secondPlayer.getScore().getSets() == 2){
                req.setAttribute("winner", secondPlayer.getName());
                resp.setStatus(SC_OK);
                req.getRequestDispatcher(JspPathFinder.getPath("Winner")).forward(req, resp);
            }
        }

        doGet(req, resp);

    }
}
