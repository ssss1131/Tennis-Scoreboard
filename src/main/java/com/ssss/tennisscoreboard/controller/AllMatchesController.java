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
import java.util.Optional;

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
        String maybeFilter = req.getParameter("query");
        String page = req.getParameter("page");
        Optional<String> filter = UserInputValidator.validateFilter(maybeFilter);
        List<Match> matches;
        int allPagesCount;
        if(filter.isPresent()){
            matches = matchRepositoryService.getMatchesWithFilter(filter.get(), pageSize, page);
            allPagesCount = matchRepositoryService.getAllFilteredPages(filter.get(), pageSize);
        }else{
            matches = matchRepositoryService.getMatchesWithFilter(pageSize, page);
            allPagesCount = matchRepositoryService.getAllPages(pageSize);
        }
        req.setAttribute("matches", matches);
        req.setAttribute("pages", allPagesCount);
        req.getRequestDispatcher(JspPathFinder.getPath("AllMatches")).forward(req, resp);
    }
}
