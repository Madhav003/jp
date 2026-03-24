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
        String lastOpponentMove = opponentHistory.get(opponentHistory.size() - 1);
        String myLastMove = "COOPERATE";
        if (opponentHistory.size() > 1 && "DEFECT".equals(opponentHistory.get(opponentHistory.size() - 2))) {
            myLastMove = "DEFECT";
        }
        if (("COOPERATE".equals(myLastMove) && "COOPERATE".equals(lastOpponentMove))
                || ("DEFECT".equals(myLastMove) && "DEFECT".equals(lastOpponentMove))) {
            return "COOPERATE";
        }
        return "DEFECT";
    }
}
