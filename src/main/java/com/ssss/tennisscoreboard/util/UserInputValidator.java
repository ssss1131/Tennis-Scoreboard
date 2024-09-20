package com.ssss.tennisscoreboard.util;

import lombok.experimental.UtilityClass;

import java.util.Optional;

@UtilityClass
public class UserInputValidator {

    public static int validatePage(String maybePage) {
        int page;
        try{
            page = Integer.parseInt(maybePage) - 1;
        } catch (NumberFormatException e) {
            page = 0;
        }
        return page;
    }

    public static Optional<String> validateFilter(String maybeFilter){
        if(maybeFilter == null || maybeFilter.isEmpty()){
           return Optional.empty();
        }
        String filter = maybeFilter.trim();
        return Optional.of(filter);
    }
}
