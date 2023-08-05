package com.example.assignment.controller;

import com.example.assignment.model.dto.PlayerResponse;
import com.example.assignment.model.dto.PlayersResponse;
import com.example.assignment.service.data.load.players.PlayerFieldsEnum;
import com.example.assignment.service.data.load.players.PlayerService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
public class PlayersController extends BaseController{

  @Autowired
  private PlayerService playerService;

  @GetMapping(value = "/api/players")
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<PlayersResponse> getPlayers(
          @RequestParam(required = false, defaultValue = "0") Integer page,
          @RequestParam(required = false, defaultValue = "10") Integer size,
          @RequestParam(required = false) PlayerFieldsEnum sortBy
          ) {
    try {
      return ResponseEntity.ok(playerService.loadPlayersByPage(page, size, sortBy));
    } catch (Exception ex){
      log.error(ex.getMessage(), ex);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new PlayersResponse(ex.getMessage()));
    }
  }

  @GetMapping(value = "/api/players/{playerID}")
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<PlayerResponse> getPlayerById(@PathVariable String playerID) {
    log.info("Get player by playerID {}", playerID);
    try {
      var player = playerService.loadPlayersById(playerID);
      if (player == null) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new PlayerResponse(null, "User id " + playerID + " not " +
                "exists, please request valid ID"));
      }
      return ResponseEntity.ok(new PlayerResponse(player, null));
    } catch (Exception ex){
      log.error(ex.getMessage(), ex);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new PlayerResponse(null, ex.getMessage()));
    }
  }

}


