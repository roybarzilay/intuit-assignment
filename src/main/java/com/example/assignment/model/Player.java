package com.example.assignment.model;

import com.example.assignment.service.data.load.players.PlayerFieldsEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
public class Player {

    public Player(Map<String, Integer> header, List<String> line, int lineNumber) {
        this(
                lineNumber,
                setIndexValue(header.get(PlayerFieldsEnum.PLAYER_ID.PlayerFieldsEnum()), line),
                setIndexValue(header.get(PlayerFieldsEnum.BIRTH_YEAR.PlayerFieldsEnum()), line),
                setIndexValue(header.get(PlayerFieldsEnum.BIRTH_MONTH.PlayerFieldsEnum()), line),
                setIndexValue(header.get(PlayerFieldsEnum.BIRTH_DAY.PlayerFieldsEnum()), line),
                setIndexValue(header.get(PlayerFieldsEnum.BIRTH_COUNTRY.PlayerFieldsEnum()), line),
                setIndexValue(header.get(PlayerFieldsEnum.BIRTH_STATE.PlayerFieldsEnum()), line),
                setIndexValue(header.get(PlayerFieldsEnum.BIRTH_CITY.PlayerFieldsEnum()), line),
                setIndexValue(header.get(PlayerFieldsEnum.DEATH_YEAR.PlayerFieldsEnum()), line),
                setIndexValue(header.get(PlayerFieldsEnum.DEATH_MONTH.PlayerFieldsEnum()), line),
                setIndexValue(header.get(PlayerFieldsEnum.DEATH_DAY.PlayerFieldsEnum()), line),
                setIndexValue(header.get(PlayerFieldsEnum.DEATH_COUNTRY.PlayerFieldsEnum()), line),
                setIndexValue(header.get(PlayerFieldsEnum.DEATH_STATE.PlayerFieldsEnum()), line),
                setIndexValue(header.get(PlayerFieldsEnum.DEATH_CITY.PlayerFieldsEnum()), line),
                setIndexValue(header.get(PlayerFieldsEnum.NAME_FIRST.PlayerFieldsEnum()), line),
                setIndexValue(header.get(PlayerFieldsEnum.NAME_LAST.PlayerFieldsEnum()), line),
                setIndexValue(header.get(PlayerFieldsEnum.NAME_GIVEN.PlayerFieldsEnum()), line),
                setIndexValue(header.get(PlayerFieldsEnum.WEIGHT.PlayerFieldsEnum()), line),
                setIndexValue(header.get(PlayerFieldsEnum.HEIGHT.PlayerFieldsEnum()), line),
                setIndexValue(header.get(PlayerFieldsEnum.BATS.PlayerFieldsEnum()), line),
                setIndexValue(header.get(PlayerFieldsEnum.THROWS.PlayerFieldsEnum()), line),
                setIndexValue(header.get(PlayerFieldsEnum.DEBUT.PlayerFieldsEnum()), line),
                setIndexValue(header.get(PlayerFieldsEnum.FINAL_GAME.PlayerFieldsEnum()), line),
                setIndexValue(header.get(PlayerFieldsEnum.RETRO_ID.PlayerFieldsEnum()), line),
                setIndexValue(header.get(PlayerFieldsEnum.BBREF_iD.PlayerFieldsEnum()), line)
        );
    }

    private static String setIndexValue(int index, List<String> line) {
        if(index >= line.size() || index < 0){
            return "";
        } else {
            return line.get(index);
        }
    }

    private int line;
    private String playerID;
    private String birthYear;
    private String birthMonth;
    private String birthDay;
    private String birthCountry;
    private String birthState;
    private String birthCity;
    private String deathYear;
    private String deathMonth;
    private String deathDay;
    private String deathCountry;
    private String deathState;
    private String deathCity;
    private String nameFirst;
    private String nameLast;
    private String nameGiven;
    private String weight;
    private String height;
    private String bats;
    @JsonProperty("throws")
    private String playerThrows;
    private String debut;
    private String finalGame;
    private String retroID;
    private String bbrefID;
}
