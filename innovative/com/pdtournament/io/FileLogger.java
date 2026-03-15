package com.pdtournament.io;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FileLogger {
    public synchronized void clearFiles() {
        FileWriter writer = null;

        try {
            writer = new FileWriter("results.txt", false);
            writer.write("");
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

    public synchronized void writeLeaderboard(HashMap<String, Integer> scores) {
        ArrayList<Map.Entry<String, Integer>> entries = new ArrayList<Map.Entry<String, Integer>>(scores.entrySet());

        for (int i = 0; i < entries.size() - 1; i++) {
            for (int j = i + 1; j < entries.size(); j++) {
                if (entries.get(j).getValue() > entries.get(i).getValue()) {
                    Map.Entry<String, Integer> temp = entries.get(i);
                    entries.set(i, entries.get(j));
                    entries.set(j, temp);
                }
            }
        }

        FileWriter writer = null;

        try {
            writer = new FileWriter("leaderboard.csv", false);
            writer.write("Rank,Agent,Coins\n");

            for (int i = 0; i < entries.size(); i++) {
                int rank = i + 1;
                writer.write(rank + "," + entries.get(i).getKey() + "," + entries.get(i).getValue() + "\n");
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
