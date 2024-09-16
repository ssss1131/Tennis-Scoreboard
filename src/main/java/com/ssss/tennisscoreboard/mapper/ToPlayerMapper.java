package com.ssss.tennisscoreboard.mapper;

import com.ssss.tennisscoreboard.dto.TennisPlayerMatchInfo;
import com.ssss.tennisscoreboard.entity.Player;
import lombok.experimental.UtilityClass;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

@UtilityClass
public class ToPlayerMapper {
    private static final ModelMapper modelMapper = new ModelMapper();

    static {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    public static Player mapToPlayer(TennisPlayerMatchInfo playerMatchInfo){
        return modelMapper.map(playerMatchInfo, Player.class);
    }


}
