package com.ssss.tennisscoreboard.controller.filter;


import com.ssss.tennisscoreboard.exception.SameNameException;
import com.ssss.tennisscoreboard.util.JspPathFinder;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter("/*")
public class ExceptionHandlerFilter extends HttpFilter {

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        try {
            chain.doFilter(req, res);
        } catch (SameNameException e) {
            req.setAttribute("error", e.getMessage());
            req.getRequestDispatcher(JspPathFinder.getPath("NewMatch")).forward(req, res);
        } catch (Exception e) {
            req.setAttribute("error", e.getMessage());
            req.getRequestDispatcher(JspPathFinder.getPath("Error")).forward(req, res);
        }
    }
}
