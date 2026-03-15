package sem4.jp.innovative.com.pdtournament.agents;

import java.util.ArrayList;

public class TitForTat extends Agent {
    public TitForTat() {
        super("TitForTat");
    }

    public String decide(ArrayList<String> opponentHistory) {
        if (opponentHistory.size() == 0) {
            return "COOPERATE";
        }
        return opponentHistory.get(opponentHistory.size() - 1);
    }
}
