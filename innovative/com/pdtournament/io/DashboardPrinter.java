package sem4.jp.innovative.com.pdtournament.io;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DashboardPrinter {
    public void printScoreboard(HashMap scores) {
        ArrayList<Map.Entry> entries = new ArrayList<Map.Entry>(scores.entrySet());

        for (int i = 0; i < entries.size() - 1; i++) {
            for (int j = i + 1; j < entries.size(); j++) {
                Integer scoreA = (Integer) entries.get(i).getValue();
                Integer scoreB = (Integer) entries.get(j).getValue();

                if (scoreB > scoreA) {
                    Map.Entry temp = entries.get(i);
                    entries.set(i, entries.get(j));
                    entries.set(j, temp);
                }
            }
        }

        System.out.println("\nFinal Tournament Standings");
        System.out.println("-------------------------");

        for (int i = 0; i < entries.size(); i++) {
            int rank = i + 1;
            System.out.println(rank + ". " + entries.get(i).getKey() + " - " + entries.get(i).getValue());
        }
    }
}
