package com.example.assignment.model.dto;

import com.example.assignment.model.Player;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PlayersResponse {
    private List<Player> players;
    private Integer pageNumber;
    private Integer pageSize;
    private Integer totalPlayers;
    private Integer totalPages;
    private String error;

    public PlayersResponse(String error){
        this.error = error;
    }
}
