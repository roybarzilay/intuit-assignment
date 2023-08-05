package com.example.assignment.service.data.load.players;

import com.example.assignment.model.Player;
import com.example.assignment.service.data.load.BaseDataLoadService;
import com.example.assignment.service.data.load.FileDataLoader;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

@Component
@Log4j2
public class CsvDataFileLoader extends BaseDataLoadService implements FileDataLoader<Player> {

    public static final String CSV = "csv";
    public static final String DATA_FOLDER_NAME = "/data";
    public static final int HEADER_LINE_INDEX = 0;

    @Override
    public List<Player> loadData() throws IOException {
        return loadData(null);
    }

    @Override
    public Player loadDataByIde(String id) throws IOException {
        List<Player> data = loadData(id);
        return data.isEmpty() ? null : data.get(0);
    }

    private List<Player> loadData(String playerId) throws IOException {
        var fileList = getResourceUrls(CSV);

        List<Player> results = new ArrayList<>();
        for (String file : fileList) {
            String resourceFile = file.substring(file.indexOf(DATA_FOLDER_NAME));
            List<List<String>> records = readFile(resourceFile);
            results.addAll(parseData(records, playerId));
            log.info("{} records loaded from {}", (records.size() - 1), resourceFile);
        }

        return results;
    }

    private List<List<String>> readFile(String file) throws IOException {
        List<List<String>> records = new ArrayList<>();
        try(InputStream in = getClass().getResourceAsStream(file)) {
            assert in != null;

            try(BufferedReader br = new BufferedReader(new InputStreamReader(in))){
                String line;
                while ((line = br.readLine()) != null) {
                    records.add(Arrays.asList(line.split(",")));
                }
            }
        } catch (IOException ex) {
            log.error("Failed to load {}", file, ex);
            throw ex;
        }
        return records;
    }

    private List<Player> parseData(List<List<String>> records, String playerId) {
        List<Player> players = new ArrayList<>();
        Map<String, Integer> header = new HashMap<>();
        for (int line = 0; line < records.size(); line++) {
            if (line == HEADER_LINE_INDEX) {
                header = getHeaderMap(records.get(line));
            } else {
                Player player = new Player(header, records.get(line), line);
                if (playerId == null || playerId.equals(player.getPlayerID())) {
                    players.add(player);
                    if (playerId != null) {
                        break;
                    }
                }
            }
        }
        return players;
    }

    private Map<String, Integer> getHeaderMap(List<String> headerLine) {
        Map<String, Integer> header = new HashMap<>();
        for (int headerIndex = 0; headerIndex < headerLine.size(); headerIndex++) {
            header.put(headerLine.get(headerIndex), headerIndex);
        }
        return header;
    }


}
