package com.pdtournament.io;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import com.pdtournament.core.Scoreboard;

public class FileLogger {
    public synchronized void clearFiles(int roundsPerGame) {
        FileWriter writer = null;

        try {
            writer = new FileWriter("results.txt", false);
            writer.write("Rounds per game: " + roundsPerGame + "\n");
            writer.write("----------------------------------------\n");
        } catch (IOException e) {
            System.out.println("Error clearing results.txt: " + e.getMessage());
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    System.out.println("Error closing results.txt: " + e.getMessage());
                }
            }
        }
    }

    public synchronized void logRound(String line) {
        FileWriter writer = null;

        try {
            writer = new FileWriter("results.txt", true);
            writer.write(line + "\n");
        } catch (IOException e) {
            System.out.println("Error writing results.txt: " + e.getMessage());
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    System.out.println("Error closing results.txt: " + e.getMessage());
                }
            }
        }
    }

    public synchronized void writeLeaderboard(Scoreboard scoreboard) {
        HashMap<String, Integer> scores = scoreboard.getScores();
        ArrayList<String> rankedNames = scoreboard.getRankedNames();

        FileWriter writer = null;

        try {
            writer = new FileWriter("leaderboard.csv", false);
            writer.write("Rank,Agent,Coins\n");

            for (int i = 0; i < rankedNames.size(); i++) {
                int rank = i + 1;
                String name = rankedNames.get(i);
                writer.write(rank + "," + name + "," + scores.get(name) + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error writing leaderboard.csv: " + e.getMessage());
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    System.out.println("Error closing leaderboard.csv: " + e.getMessage());
                }
            }
        }
    }
}
