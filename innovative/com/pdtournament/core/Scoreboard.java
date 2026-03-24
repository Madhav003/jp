package com.pdtournament.core;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class Scoreboard {
    private HashMap<String, Integer> scores;

    private static class ScoreEntryComparator implements Comparator<Map.Entry<String, Integer>> {
        public int compare(Map.Entry<String, Integer> entryA, Map.Entry<String, Integer> entryB) {
            return entryB.getValue().compareTo(entryA.getValue());
        }
    }

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
        entries.sort(new ScoreEntryComparator());

        ArrayList<String> rankedNames = new ArrayList<String>();
        for (int i = 0; i < entries.size(); i++) {
            rankedNames.add(entries.get(i).getKey());
        }

        return rankedNames;
    }
}
