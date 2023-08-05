package com.example.assignment.service.data.load.players;

public enum PlayerFieldsEnum {
    PLAYER_ID("playerID"),
    BIRTH_YEAR("birthYear"),
    BIRTH_MONTH("birthMonth"),
    BIRTH_DAY("birthDay"),
    BIRTH_COUNTRY("birthCountry"),
    BIRTH_STATE("birthState"),
    BIRTH_CITY("birthCity"),
    DEATH_YEAR("deathYear"),
    DEATH_MONTH("deathMonth"),
    DEATH_DAY("deathDay"),
    DEATH_COUNTRY("deathCountry"),
    DEATH_STATE("deathState"),
    DEATH_CITY("deathCity"),
    NAME_FIRST("nameFirst"),
    NAME_LAST("nameLast"),
    NAME_GIVEN("nameGiven"),
    WEIGHT("weight"),
    HEIGHT("height"),
    BATS("bats"),
    THROWS("throws"),
    DEBUT("debut"),
    FINAL_GAME("finalGame"),
    RETRO_ID("retroID"),
    BBREF_iD("bbrefID");

    private String field;

    public String PlayerFieldsEnum() {
        return this.field;
    }

    PlayerFieldsEnum(String field) {
        this.field = field;
    }
}
