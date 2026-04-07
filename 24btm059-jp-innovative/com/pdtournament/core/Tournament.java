package com.pdtournament.core;

import java.util.ArrayList;
import java.util.HashMap;

import com.pdtournament.agents.Agent;
import com.pdtournament.io.FileLogger;

public class Tournament {
    private ArrayList<Agent> agents;
    private int rounds;
    private Scoreboard scoreboard;
    private FileLogger fileLogger;

    public Tournament(ArrayList<Agent> agents, int rounds) {
        this.agents = agents;
        this.rounds = rounds;
        this.scoreboard = new Scoreboard();
        this.fileLogger = new FileLogger();
    }

    public HashMap<String, Integer> startTournament() {
        fileLogger.clearFiles(rounds);

        for (int i = 0; i < agents.size(); i++) {
            scoreboard.addAgent(agents.get(i).getName());
        }

        int n = agents.size();
        Thread[] threads = new Thread[n * (n + 1) / 2];
        int threadIndex = 0;

        for (int i = 0; i < agents.size(); i++) {
            for (int j = i; j < agents.size(); j++) {
                Game game = new Game(agents.get(i), agents.get(j), rounds, scoreboard, fileLogger);
                Thread thread = new Thread(game);
                threads[threadIndex] = thread;
                threadIndex++;
                thread.start();
            }
        }

        for (int i = 0; i < threadIndex; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        HashMap<String, Integer> finalScores = scoreboard.getScores();
        fileLogger.writeLeaderboard(scoreboard);
        return finalScores;
    }
}
