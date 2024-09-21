package com.ssss.tennisscoreboard.dto;


import com.ssss.tennisscoreboard.model.score.TennisScore;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TennisPlayerMatchInfo {

    private Long id;

    private String name;

    private TennisScore score;

}
