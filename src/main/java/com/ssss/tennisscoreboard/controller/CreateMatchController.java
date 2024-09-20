package com.ssss.tennisscoreboard.controller;

import com.ssss.tennisscoreboard.dto.CurrentMatch;
import com.ssss.tennisscoreboard.entity.Player;
import com.ssss.tennisscoreboard.service.OnGoingMatchesService;
import com.ssss.tennisscoreboard.service.PlayerRepositoryService;
import com.ssss.tennisscoreboard.util.JspPathFinder;
import com.ssss.tennisscoreboard.util.UserInputValidator;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


@WebServlet("/new-match")
public class CreateMatchController extends HttpServlet {

    private PlayerRepositoryService playerRepositoryService;
    private OnGoingMatchesService onGoingMatchesService;

    @Override
    public void init() throws ServletException {
        super.init();
        playerRepositoryService = new PlayerRepositoryService();
        onGoingMatchesService = new OnGoingMatchesService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(JspPathFinder.getPath("NewMatch")).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //TODO null или нет надо чекнуть и впринципе валидацию сделать
        String firstPlayerName = req.getParameter("firstPlayer");
        String secondPlayerName = req.getParameter("secondPlayer");
        UserInputValidator.validate(firstPlayerName, secondPlayerName);
        Player firstPlayer = playerRepositoryService.createNewUser(firstPlayerName);
        Player secondPlayer = playerRepositoryService.createNewUser(secondPlayerName);
        CurrentMatch newMatch = onGoingMatchesService.createNewMatch(firstPlayer, secondPlayer);
        resp.sendRedirect(String.format("/match-score?uuid=%s", newMatch.getUuid()));
    }
}


