package com.pdtournament.io;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class DashboardPrinter {
    private static class ScoreEntryComparator implements Comparator<Map.Entry<String, Integer>> {
        public int compare(Map.Entry<String, Integer> entryA, Map.Entry<String, Integer> entryB) {
            return entryB.getValue().compareTo(entryA.getValue());
        }
    }

    public void printScoreboard(HashMap<String, Integer> scores) {
        ArrayList<Map.Entry<String, Integer>> entries = new ArrayList<Map.Entry<String, Integer>>(scores.entrySet());
        entries.sort(new ScoreEntryComparator());

        System.out.println("\nFinal Tournament Standings");
        System.out.println("-------------------------");

        for (int i = 0; i < entries.size(); i++) {
            int rank = i + 1;
            System.out.println(rank + ". " + entries.get(i).getKey() + " - " + entries.get(i).getValue());
        }
    }
}
