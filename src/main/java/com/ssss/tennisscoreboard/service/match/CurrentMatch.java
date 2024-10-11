package com.ssss.tennisscoreboard.service.match;

import com.ssss.tennisscoreboard.dto.TennisPlayerMatchInfo;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class CurrentMatch {

    private UUID uuid;

    private TennisPlayerMatchInfo firstPlayer;

    private TennisPlayerMatchInfo secondPlayer;


    public boolean isMatchComplete(){
        return firstPlayer.getScore().getSets() == 2 || secondPlayer.getScore().getSets() == 2;
    }

}
