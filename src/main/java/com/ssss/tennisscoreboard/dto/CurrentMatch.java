package com.ssss.tennisscoreboard.dto;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class CurrentMatch {

    private UUID uuid;

    private TennisPlayerMatchInfo firstPlayer;

    private TennisPlayerMatchInfo secondPlayer;

}
