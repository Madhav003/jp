package sem4.jp.innovative.com.pdtournament.agents;

import java.util.ArrayList;

public class AlwaysDefect extends Agent {
    public AlwaysDefect() {
        super("AlwaysDefect");
    }

    public String decide(ArrayList<String> opponentHistory) {
        return "DEFECT";
    }
}
