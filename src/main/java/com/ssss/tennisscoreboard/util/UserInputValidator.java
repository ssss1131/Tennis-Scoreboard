package com.ssss.tennisscoreboard.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class UserInputValidator {

    public static int validate(String maybePage) {
        int page;
        try{
            page = Integer.parseInt(maybePage);
        } catch (NumberFormatException e) {
            page = 1;
        }
        return page;
    }
}
