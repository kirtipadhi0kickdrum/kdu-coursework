package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class readFromCSV {
    public List<Player> readFromCSV(String file)
    {
        List<Player> players= new ArrayList<>();
        Path filePath = Paths.get(file);

        try(BufferedReader br = Files.newBufferedReader(filePath, StandardCharsets.US_ASCII))
        {
            String eachPlayer = br.readLine();
            eachPlayer = br.readLine();

            while(eachPlayer != null)
            {
                String[] playerDetails = eachPlayer.split(",");

                Player player = createPlayers(playerDetails);
                players.add(player);
                eachPlayer = br.readLine();
            }
        }catch (IOException e)
        {
            e.printStackTrace();
        }
        return players;
    }
    private static Player createPlayers(String[] details)
    {
        String name = details[0];
        String team = details[1];
        String role = details[2];

        int matches= Integer.parseInt(details[3]);

        int runs = Integer.parseInt(details[4]);
        double average = Double.parseDouble(details[5]);
        double strikeRate = Double.parseDouble(details[6]);
        int wickets = Integer.parseInt(details[7]);

        return new Player(name, team, role, matches, runs, average, strikeRate, wickets);
    }

}


