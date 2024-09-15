package com.ssss.tennisscoreboard.dto.some;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TennisScore extends Score {

    private boolean isDeuce;
    private TennisDeuceScore deucePointsPlayer = TennisDeuceScore.EQUAL;

    private boolean isTiebreak;
    private int tiebreakPointsPlayer;

    public TennisScore() {
    }

    //Only for jsp bcs Property cant find without get{VariableName} (((
    public boolean getIsDeuce() {
        return isDeuce();
    }

    public boolean getIsTieBreak(){
        return isTiebreak();
    }

    public boolean isDeuce(){
        return this.isDeuce;
    }

    public boolean isTiebreak(){
        return this.isTiebreak;
    }


}
