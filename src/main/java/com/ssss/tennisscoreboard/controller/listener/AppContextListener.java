package com.ssss.tennisscoreboard.controller.listener;

import com.ssss.tennisscoreboard.util.HibernateUtils;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class AppContextListener implements ServletContextListener {

    private static final int PAGE_SIZE = 5;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContextListener.super.contextInitialized(sce);
        sce.getServletContext().setAttribute("pageSize", PAGE_SIZE);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContextListener.super.contextDestroyed(sce);
        HibernateUtils.getSessionFactory().close();
    }
}
