package com.pdtournament.core;

import java.util.HashMap;

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
        return new HashMap<String, Integer>(scores);
    }
}
