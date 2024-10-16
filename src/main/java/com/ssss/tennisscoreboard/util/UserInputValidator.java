package com.ssss.tennisscoreboard.util;

import com.ssss.tennisscoreboard.exception.SameNameException;
import lombok.experimental.UtilityClass;

import java.util.Optional;

@UtilityClass
public class UserInputValidator {


    public static void validate(String playerOneName, String playerTwoName){
        if(playerOneName == null || playerTwoName == null || playerOneName.equalsIgnoreCase(playerTwoName))
            throw new SameNameException("Players name must be different and not null");
    }

    public static int validatePage(String maybePage) {
        int page;
        try{
            page = Integer.parseInt(maybePage) - 1;
            return Math.max(page, 0);
        } catch (NumberFormatException e) {
            page = 0;
        }
        return page;
    }

    public static Optional<String> validateFilter(String maybeFilter) {
        return Optional.ofNullable(maybeFilter)
                .map(String::trim)
                .filter(f -> !f.isEmpty());
    }
}
