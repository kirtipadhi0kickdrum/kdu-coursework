package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getName());
    public static void main(String[] args) {
        String csvPath = "src/main/resources/IPL_2021-data.csv";
        readFromCSV read = new readFromCSV();
        List<Player> players = read.readFromCSV(csvPath);


        logger.info("The team whose details we are going to display is RCB ");

        String teamName = "RCB";
        List<String> bowlers = getBowlerWicket40(players, teamName);
        logger.info("The Bowlers of team RCB having wickets greater than 40 are - " + bowlers);

        String highestWicketBowlers = getBowlersHighest(players, teamName);
        logger.info("The bowler with the highest wickets in the team RCB is - " + highestWicketBowlers);
        String highestRunScorer = getRunHighest(players, teamName);
        logger.info("The batsman with the highest runs in team RCB is - " + highestRunScorer);

       List<String> top3RunScorrer = getTopRunScorrer(players);
        logger.info("The top 3 run scorers of the season are - " + top3RunScorrer);
        List<String> top3WicketTakers = getTopWicketTakers(players);
        logger.info("The top 3 wicket takers of the season are - " + top3WicketTakers);
    }

    public static List<String> getBowlerWicket40(List<Player> players, String teamName)
    {
        return players.stream()
                .filter(p -> p.getTeam().equalsIgnoreCase(teamName))
                .filter(p -> p.getWickets() >= 40)
                .map(Player::getName)
                .toList();
    }
    public static String getBowlersHighest(List<Player> players, String teamName)
    {
        return players.stream()
                .filter(p -> p.getTeam().equalsIgnoreCase(teamName))
                .max(Comparator.comparing(Player::getWickets))
                .get().getName();

    }

    public static String getRunHighest(List<Player> players, String teamName)
    {
        return players.stream()
                .filter(p -> p.getTeam().equalsIgnoreCase(teamName))
                .max(Comparator.comparing(Player::getRuns))
                .get().getName();
    }

    public static List<String> getTopRunScorrer(List<Player> players)
    {
        return players.stream()
                .sorted(Comparator.comparingInt(Player::getRuns).reversed())
                .limit(3)
                .map(Player::getName)
                .toList();
    }
    public static List<String> getTopWicketTakers(List<Player> players)
    {
        return players.stream()
                .sorted(Comparator.comparingInt(Player::getWickets).reversed())
                .limit(3)
                .map(Player::getName)
                .toList();
    }
}