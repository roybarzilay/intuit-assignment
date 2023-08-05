package com.example.assignment.service.data.load.players;

import com.example.assignment.model.Player;
import com.example.assignment.model.dto.PlayersResponse;
import com.example.assignment.service.data.load.BaseDataLoadService;
import com.example.assignment.service.data.load.CacheService;
import com.example.assignment.service.data.load.FileDataLoader;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@Log4j2
public class PlayerService extends BaseDataLoadService {

    @Autowired
    private List<FileDataLoader> dataFileLoaders;

    @Autowired
    private CacheService<Player> cacheService;

    public PlayersResponse loadPlayersByPage(Integer page, Integer size, PlayerFieldsEnum sortBy) throws IOException {
        List<Player> players = getPlayers();
        sortBy(players, sortBy);

        if (page == null || size == null) {
            return new PlayersResponse(players, size, page, players.size(), size !=  null ? players.size()/size : null);
        }
        return new PlayersResponse(playersPerPage(players, page, size), page, size, players.size(), players.size()/size);
    }

    public List<Player> loadPlayers() throws IOException {
        List<Player> players = new ArrayList<>();
        for (FileDataLoader loader : dataFileLoaders) {
            players.addAll(loader.loadData());
        }
        return players;
    }

    public Player loadPlayersById(String id) throws IOException {
        Player playerById = cacheService.getById(id);
        if (playerById != null) {
            return playerById;
        }
        for (FileDataLoader loader : dataFileLoaders) {
            playerById = (Player)loader.loadDataByIde(id);
            if (playerById != null) {
                break;
            }
        }
        return playerById;
    }

    private void overLimitValidation(int start, int size, int total) {
        if (start > total) {
            throw new RuntimeException("Size over limit, max page: " + (double) (total / size) + " for size " + size);
        }
    }

    private List<Player> playersPerPage(List<Player> players, Integer page, Integer size) {
        int start = page == 0 ? 0 : (page - 1) * size;
        overLimitValidation(start,size, players.size());
        int end = Math.min((start + size), players.size());
        return players.subList(start, end);
    }

    private List<Player> getPlayers() throws IOException {
        List<Player> players;
        if (cacheService.getCacheSize() == 0) {
            players = loadPlayers();
            players.forEach(p -> cacheService.setCache(p.getPlayerID(), p));
        } else {
            players = new ArrayList<>(cacheService.getCache());
        }
        return players;
    }

    private void sortBy(List<Player> players, PlayerFieldsEnum sortBy) {
        if (sortBy != null) {
            log.info("Sort BY {}", sortBy.PlayerFieldsEnum());
            switch (sortBy) {
                case PLAYER_ID -> players.sort(Comparator.comparing(Player::getPlayerID));
                case BIRTH_YEAR -> players.sort(Comparator.comparing(Player::getBirthYear));
                case BIRTH_MONTH -> players.sort(Comparator.comparing(Player::getBirthMonth));
                case BIRTH_DAY -> players.sort(Comparator.comparing(Player::getBirthDay));
                case BIRTH_COUNTRY -> players.sort(Comparator.comparing(Player::getBirthCountry));
                case BIRTH_STATE -> players.sort(Comparator.comparing(Player::getBirthState));
                case BIRTH_CITY -> players.sort(Comparator.comparing(Player::getBirthCity));
                case DEATH_YEAR -> players.sort(Comparator.comparing(Player::getDeathYear));
                case DEATH_MONTH -> players.sort(Comparator.comparing(Player::getDeathMonth));
                case DEATH_DAY -> players.sort(Comparator.comparing(Player::getDeathDay));
                case DEATH_COUNTRY -> players.sort(Comparator.comparing(Player::getDeathCountry));
                case DEATH_STATE -> players.sort(Comparator.comparing(Player::getDeathState));
                case DEATH_CITY -> players.sort(Comparator.comparing(Player::getDeathCity));
                case NAME_FIRST -> players.sort(Comparator.comparing(Player::getNameFirst));
                case NAME_LAST -> players.sort(Comparator.comparing(Player::getNameLast));
                case NAME_GIVEN -> players.sort(Comparator.comparing(Player::getNameGiven));
                case WEIGHT -> players.sort(Comparator.comparing(Player::getWeight));
                case HEIGHT -> players.sort(Comparator.comparing(Player::getHeight));
                case BATS -> players.sort(Comparator.comparing(Player::getBats));
                case THROWS -> players.sort(Comparator.comparing(Player::getPlayerThrows));
                case DEBUT -> players.sort(Comparator.comparing(Player::getDebut));
                case FINAL_GAME -> players.sort(Comparator.comparing(Player::getFinalGame));
                case RETRO_ID -> players.sort(Comparator.comparing(Player::getRetroID));
                case BBREF_iD -> players.sort(Comparator.comparing(Player::getBbrefID));
            }

        }
    }

}
