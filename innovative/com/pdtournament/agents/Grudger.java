package sem4.jp.innovative.com.pdtournament.agents;

import java.util.ArrayList;

public class Grudger extends Agent {
    public Grudger() {
        super("Grudger");
    }

    public String decide(ArrayList<String> opponentHistory) {
        for (int i = 0; i < opponentHistory.size(); i++) {
            if ("DEFECT".equals(opponentHistory.get(i))) {
                return "DEFECT";
            }
        }
        return "COOPERATE";
    }
}
