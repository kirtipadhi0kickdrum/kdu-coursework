package org.example;

import java.util.List;

import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;


public class MatchFixtureGenerator {

    public static void main(String[] args) {
        List<String> teams = getTeams();
        List<List<String>> fixtures = generateFixtures(teams);
        writeFixturesToCSV(fixtures, "src/main/resources/IPL_fixtures.csv");
    }

    private static List<String> getTeams() {

        return List.of("MI", "RCB", "SRH", "CSK", "RR", "PBKS", "DC", "KKR");
    }

    private static List<List<String>> generateFixtures(List<String> teams) {
        List<List<String>> fixtures = new ArrayList<>();
        List<String> dates = generateDates(teams.size());

        List<Integer> teamIndices = new ArrayList<>();
        for (int i = 0; i < teams.size(); i++) {
            teamIndices.add(i);
        }

        int dateIndex = 0;


        for (int i = 0; i < teams.size(); i++) {
            for (int j : teamIndices) {
                if (i != j) {
                    List<String> fixture = new ArrayList<>();
                    fixture.add(dates.get(dateIndex % dates.size()));  // Use modulo to reset to 0 when reaching the end
                    fixture.add(teams.get(i));
                    fixture.add(teams.get(j));
                    fixtures.add(fixture);

                    dateIndex++;
                }
            }
        }


        List<List<String>> secondRoundFixtures = new ArrayList<>(fixtures);
        Collections.reverse(secondRoundFixtures);
        fixtures.addAll(secondRoundFixtures);

        return fixtures;
    }

    private static List<String> generateDates(int numberOfTeams) {
        List<String> dates = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Calendar calendar = Calendar.getInstance();

        try {

            calendar.setTime(dateFormat.parse("16-01-2024"));


            for (int i = 0; i < numberOfTeams - 1; i++) {
                dates.add(dateFormat.format(calendar.getTime()));
                calendar.add(Calendar.DAY_OF_MONTH, 1);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return dates;
    }

    private static void writeFixturesToCSV(List<List<String>> fixtures, String filePath) {
        try (FileWriter writer = new FileWriter(filePath)) {
            for (List<String> fixture : fixtures) {
                writer.append(String.join(",", fixture));
                writer.append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
