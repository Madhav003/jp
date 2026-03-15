package com.pdtournament.core;

import java.util.ArrayList;

import com.pdtournament.agents.Agent;
import com.pdtournament.exceptions.InvalidDecisionException;
import com.pdtournament.io.FileLogger;

public class Game implements Runnable {
    private Agent agentA;
    private Agent agentB;
    private int rounds;
    private Scoreboard scoreboard;
    private FileLogger fileLogger;
    private ArrayList<String> roundResults;

    public Game(Agent agentA, Agent agentB, int rounds, Scoreboard scoreboard, FileLogger fileLogger) {
        this.agentA = agentA;
        this.agentB = agentB;
        this.rounds = rounds;
        this.scoreboard = scoreboard;
        this.fileLogger = fileLogger;
        this.roundResults = new ArrayList<String>();
    }

    public void run() {
        ArrayList<String> historyA = new ArrayList<String>();
        ArrayList<String> historyB = new ArrayList<String>();

        for (int round = 1; round <= rounds; round++) {
            try {
                String decisionA = getValidDecision(agentA, historyB);
                String decisionB = getValidDecision(agentB, historyA);

                int[] scores = getRoundScores(decisionA, decisionB);

                scoreboard.updateScore(agentA.getName(), scores[0]);
                scoreboard.updateScore(agentB.getName(), scores[1]);

                String result = agentA.getName() + " vs " + agentB.getName() + " | Round " + round + "/" + rounds
                    + " | "
                        + decisionA + " | " + decisionB + " | " + scores[0] + " " + scores[1];

                roundResults.add(result);
                fileLogger.logRound(result);

                historyA.add(decisionA);
                historyB.add(decisionB);
            } catch (InvalidDecisionException e) {
                String message = "Invalid decision in game " + agentA.getName() + " vs " + agentB.getName() + ": "
                        + e.getMessage();
                System.out.println(message);
                fileLogger.logRound(message);
                break;
            }
        }
    }

    public ArrayList<String> getRoundResults() {
        return roundResults;
    }

    private String getValidDecision(Agent agent, ArrayList<String> opponentHistory) throws InvalidDecisionException {
        String decision = agent.decide(opponentHistory);

        if (!"COOPERATE".equals(decision) && !"DEFECT".equals(decision)) {
            throw new InvalidDecisionException(agent.getName() + " returned: " + decision);
        }

        return decision;
    }

    private int[] getRoundScores(String decisionA, String decisionB) {
        int[] scores = new int[2];

        if ("COOPERATE".equals(decisionA) && "COOPERATE".equals(decisionB)) {
            scores[0] = 3;
            scores[1] = 3;
            return scores;
        }

        if ("DEFECT".equals(decisionA) && "DEFECT".equals(decisionB)) {
            scores[0] = 1;
            scores[1] = 1;
            return scores;
        }

        if ("DEFECT".equals(decisionA) && "COOPERATE".equals(decisionB)) {
            scores[0] = 5;
            scores[1] = 0;
            return scores;
        }

        scores[0] = 0;
        scores[1] = 5;
        return scores;
    }
}
