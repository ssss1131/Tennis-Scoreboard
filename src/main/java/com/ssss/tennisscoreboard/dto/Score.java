package com.ssss.tennisscoreboard.dto;

import lombok.Data;

@Data
public class Score {
    //TODO по сути надо чекнуть как тут по дефолту 0 сделать хотя вроде так и должно быть но там null странно
    private int sets = 0;

    private int games = 0;

    private int points = 0;

}
