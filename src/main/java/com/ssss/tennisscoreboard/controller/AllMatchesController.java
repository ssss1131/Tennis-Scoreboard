package com.ssss.tennisscoreboard.controller;

import com.ssss.tennisscoreboard.entity.Match;
import com.ssss.tennisscoreboard.service.MatchRepositoryService;
import com.ssss.tennisscoreboard.util.JspPathFinder;
import com.ssss.tennisscoreboard.util.UserInputValidator;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/matches")
public class AllMatchesController extends HttpServlet {

    private MatchRepositoryService matchRepositoryService;
    private int pageSize;

    @Override
    public void init() throws ServletException {
        super.init();
        matchRepositoryService = new MatchRepositoryService();
        Object pageSizeAttr = getServletContext().getAttribute("pageSize");
        if (pageSizeAttr == null) {
            throw new ServletException("PageSize attribute is not initialized in ServletContext");
        }
        pageSize = (int) pageSizeAttr;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //TODO надо сделать так чтобы pages передавал не только общее ну и количество при фильтре если есть
        String maybePage = req.getParameter("page");
        String maybeFilter = req.getParameter("query");
        int page = maybePage != null ? UserInputValidator.validate(maybePage) : 1;
        List<Match> matches = matchRepositoryService.getMatchesWithFilter(maybeFilter, pageSize, page);
        int allPages = matchRepositoryService.getAllPagesCountWithPageSize(pageSize);
        req.setAttribute("matches", matches);
        req.setAttribute("pages", allPages);
        req.getRequestDispatcher(JspPathFinder.getPath("AllMatches")).forward(req, resp);
    }
}
