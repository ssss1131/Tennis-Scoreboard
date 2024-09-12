package com.ssss.tennisscoreboard.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class JspPathFinder {
    private static final String JSP_FORMAT = "/WEB-INF/jsp/%s.jsp";


    public static String getPath(String name){
        return String.format(JSP_FORMAT, name);
    }

}
