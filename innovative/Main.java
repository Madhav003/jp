import java.util.ArrayList;
import java.util.HashMap;

import sem4.jp.innovative.com.pdtournament.agents.Agent;
import sem4.jp.innovative.com.pdtournament.agents.AlwaysCooperate;
import sem4.jp.innovative.com.pdtournament.agents.AlwaysDefect;
import sem4.jp.innovative.com.pdtournament.agents.Grudger;
import sem4.jp.innovative.com.pdtournament.agents.Pavlov;
import sem4.jp.innovative.com.pdtournament.agents.RandomAgent;
import sem4.jp.innovative.com.pdtournament.agents.TitForTat;
import sem4.jp.innovative.com.pdtournament.core.Tournament;
import sem4.jp.innovative.com.pdtournament.io.DashboardPrinter;

public class Main {
	private static final int ROUNDS_PER_GAME = 10;

	public static void main(String[] args) {
		ArrayList<Agent> agents = new ArrayList<Agent>();
		agents.add(new AlwaysCooperate());
		agents.add(new AlwaysDefect());
		agents.add(new TitForTat());
		agents.add(new Grudger());
		agents.add(new RandomAgent());
		agents.add(new Pavlov());

		Tournament tournament = new Tournament(agents, ROUNDS_PER_GAME);
		HashMap<String, Integer> finalScores = tournament.startTournament();

		DashboardPrinter dashboardPrinter = new DashboardPrinter();
		dashboardPrinter.printScoreboard(finalScores);

		System.out.println("\nDetailed rounds written to results.txt");
		System.out.println("Final leaderboard written to leaderboard.csv");
	}
}
