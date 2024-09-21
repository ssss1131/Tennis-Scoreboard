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

}
