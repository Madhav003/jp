package sem4.jp.innovative.com.pdtournament.agents;

import java.util.ArrayList;

public class AlwaysCooperate extends Agent {
    public AlwaysCooperate() {
        super("AlwaysCooperate");
    }

    public String decide(ArrayList<String> opponentHistory) {
        return "COOPERATE";
    }
}
