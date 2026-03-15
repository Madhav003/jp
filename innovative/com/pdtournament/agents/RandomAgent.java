package sem4.jp.innovative.com.pdtournament.agents;

import java.util.ArrayList;

public class RandomAgent extends Agent {
    public RandomAgent() {
        super("RandomAgent");
    }

    public String decide(ArrayList<String> opponentHistory) {
        if (Math.random() >= 0.5) {
            return "COOPERATE";
        }
        return "DEFECT";
    }
}
