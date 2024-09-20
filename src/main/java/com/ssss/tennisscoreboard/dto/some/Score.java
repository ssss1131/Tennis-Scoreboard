package com.ssss.tennisscoreboard.dto.some;

import lombok.Data;

@Data
public class Score {
    //TODO по сути надо чекнуть как тут по дефолту 0 сделать хотя вроде так и должно быть но там null странно
    private int sets;

    private int games;

    private int points;

}
