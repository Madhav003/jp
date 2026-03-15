package com.pdtournament.agents;

import java.util.ArrayList;

public class Pavlov extends Agent {
    public Pavlov() {
        super("Pavlov");
    }

    public String decide(ArrayList<String> opponentHistory) {
        if (opponentHistory.size() == 0) {
            return "COOPERATE";
        }

        String myDecision = "COOPERATE";

        for (int i = 0; i < opponentHistory.size(); i++) {
            String opponentDecision = opponentHistory.get(i);
            int score = getRoundScore(myDecision, opponentDecision);

            if (score == 0 || score == -1) {
                if ("COOPERATE".equals(myDecision)) {
                    myDecision = "DEFECT";
                } else {
                    myDecision = "COOPERATE";
                }
            }
        }

        return myDecision;
    }

    private int getRoundScore(String myDecision, String opponentDecision) {
        if ("COOPERATE".equals(myDecision) && "COOPERATE".equals(opponentDecision)) {
            return 1;
        }
        if ("DEFECT".equals(myDecision) && "DEFECT".equals(opponentDecision)) {
            return 0;
        }
        if ("DEFECT".equals(myDecision) && "COOPERATE".equals(opponentDecision)) {
            return 2;
        }
        return -1;
    }
}
