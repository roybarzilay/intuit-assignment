package com.example.assignment.model.dto;

import com.example.assignment.model.Player;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PlayerResponse {
    private Player player;
}
