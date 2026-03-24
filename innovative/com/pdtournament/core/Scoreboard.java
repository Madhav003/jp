package com.pdtournament.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Scoreboard {
    private HashMap<String, Integer> scores;

    public Scoreboard() {
        scores = new HashMap<String, Integer>();
    }

    public synchronized void addAgent(String name) {
        if (!scores.containsKey(name)) {
            scores.put(name, 0);
        }
    }

    public synchronized void updateScore(String name, int delta) {
        if (!scores.containsKey(name)) {
            scores.put(name, 0);
        }

        int current = scores.get(name);
        int updated = current + delta;

        scores.put(name, updated);
    }

    public synchronized HashMap<String, Integer> getScores() {
        return scores;
    }

    public synchronized ArrayList<String> getRankedNames() {
        ArrayList<Map.Entry<String, Integer>> entries = new ArrayList<Map.Entry<String, Integer>>(scores.entrySet());
        int size = entries.size();
        String[] names = new String[size];
        int[] values = new int[size];

        for (int i = 0; i < size; i++) {
            names[i] = entries.get(i).getKey();
            values[i] = entries.get(i).getValue();
        }

        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - i - 1; j++) {
                if (values[j] < values[j + 1]) {
                    int tempScore = values[j];
                    values[j] = values[j + 1];
                    values[j + 1] = tempScore;

                    String tempName = names[j];
                    names[j] = names[j + 1];
                    names[j + 1] = tempName;
                }
            }
        }

        ArrayList<String> rankedNames = new ArrayList<String>();
        for (int i = 0; i < size; i++) {
            rankedNames.add(names[i]);
        }

        return rankedNames;
    }
}
