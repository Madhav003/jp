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
	private static final int mrpg = 10;
	private static final int maxrpg = 100;

	public static void main(String[] args) {
		Random randomNum = new Random();
		int roundsPerGame = randomNum.nextInt(maxrpg - mrpg + 1)
				+ mrpg;

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

		System.out.println("Rounds per game: " + roundsPerGame);
		System.out.println("\nDetailed rounds written to results.txt");
		System.out.println("Final leaderboard written to leaderboard.csv");
	}
}
