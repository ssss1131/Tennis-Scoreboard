package com.ssss.tennisscoreboard.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PlayingMatchInfo {

    private TennisPlayerMatchInfo player1;

    private TennisPlayerMatchInfo player2;

    private Long scoredId;


}
