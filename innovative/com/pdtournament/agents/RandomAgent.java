package com.pdtournament.agents;

import java.util.ArrayList;

public class RandomAgent extends Agent {
    public RandomAgent() {
        super("RandomAgent");
    }

    public String decide(ArrayList<String> opponentHistory) {
        return Math.random() < 0.5 ? "COOPERATE" : "DEFECT";
    }
}
