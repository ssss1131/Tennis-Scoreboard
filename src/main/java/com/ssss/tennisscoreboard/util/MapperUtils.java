package com.ssss.tennisscoreboard.util;

import com.ssss.tennisscoreboard.dto.TennisPlayerMatchInfo;
import com.ssss.tennisscoreboard.model.Player;
import lombok.experimental.UtilityClass;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

@UtilityClass
public class MapperUtils {
    private static final ModelMapper modelMapper = new ModelMapper();

    static {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    public static Player mapTo(TennisPlayerMatchInfo playerMatchInfo){
        return modelMapper.map(playerMatchInfo, Player.class);
    }

}
