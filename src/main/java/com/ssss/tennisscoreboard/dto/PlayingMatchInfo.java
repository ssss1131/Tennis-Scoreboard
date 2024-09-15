package com.ssss.tennisscoreboard.dto;

import com.ssss.tennisscoreboard.dto.some.TennisScore;
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
