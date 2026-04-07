import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import com.pdtournament.agents.Agent;
import com.pdtournament.agents.AlwaysCooperate;
import com.pdtournament.agents.AlwaysDefect;
import com.pdtournament.agents.Grudger;
import com.pdtournament.agents.Pavlov;
import com.pdtournament.agents.RandomAgent;
import com.pdtournament.agents.TitForTat;
import com.pdtournament.core.Tournament;
import com.pdtournament.io.DashboardPrinter;

public class Main {
	public static void main(String[] args) {
		int roundsPerGame = 20 + new Random().nextInt(1000);
		System.out.println("Rounds per game selected: " + roundsPerGame);

		ArrayList<Agent> agents = new ArrayList<Agent>();
		agents.add(new AlwaysCooperate());
		agents.add(new AlwaysDefect());
		agents.add(new TitForTat());
		agents.add(new Grudger());
		agents.add(new RandomAgent());
		agents.add(new Pavlov());

		Tournament tournament = new Tournament(agents, roundsPerGame);
		HashMap<String, Integer> finalScores = tournament.startTournament();

		DashboardPrinter dashboardPrinter = new DashboardPrinter();
		dashboardPrinter.printScoreboard(finalScores);

		System.out.println("\nDetailed rounds written to results.txt");
		System.out.println("Final leaderboard written to leaderboard.csv");
	}
}
