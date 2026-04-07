# explain.md

## Purpose
This file maps each required Java concept directly to the simplified implementation so a student can explain each concept quickly in viva.

## 1) OOP Fundamentals
- Object creation: Main creates agent objects, Tournament, and DashboardPrinter.
- Encapsulation: private fields are used in Agent, Game, Tournament, and Scoreboard, with public methods for access/use.
- Methods and flow: Main -> Tournament.startTournament() -> Game.run() shows clear object interaction.

Short snippet:
```java
ArrayList<Agent> agents = new ArrayList<Agent>();
agents.add(new AlwaysCooperate());
Tournament tournament = new Tournament(agents, roundsPerGame);
HashMap<String, Integer> finalScores = tournament.startTournament();
```

## 2) Inheritance and Interface
- Interface: Decidable defines decide(ArrayList<String> opponentHistory).
- Abstract class: Agent implements Decidable and stores common name field.
- Concrete subclasses: AlwaysCooperate, AlwaysDefect, TitForTat, Grudger, RandomAgent, Pavlov.

Short snippet:
```java
public abstract class Agent implements Decidable {
    private String name;
    public Agent(String name) { this.name = name; }
    public String getName() { return name; }
}
```

## 3) Dynamic Method Dispatch
Game stores references as Agent type, but at runtime decide() resolves to each concrete strategy.

Short snippet:
```java
String decisionA = agentA.decide(historyB);
String decisionB = agentB.decide(historyA);
```

Why it matters: same Game code works for all strategies without if-else by class type.

## 4) Packages
- com.pdtournament.agents: strategy behavior.
- com.pdtournament.core: tournament execution and scoring.
- com.pdtournament.io: output to files and console.
- com.pdtournament.exceptions: custom checked exception class.

This separation keeps responsibilities clean and easy to explain.

## 5) Exception Handling
Game validates decisions after each strategy call. If a strategy returns invalid text, InvalidDecisionException is thrown and caught once around the full loop.

Short snippet:
```java
if (!"COOPERATE".equals(decisionA) && !"DEFECT".equals(decisionA)) {
    throw new InvalidDecisionException(agentA.getName() + " returned: " + decisionA);
}
```

Why this is clear: one try/catch wraps the round loop and logs one readable error line.

## 6) Multithreading
Tournament creates one thread per unique pair and stores threads in a fixed array sized n*(n-1)/2.

Short snippet:
```java
Thread[] threads = new Thread[n * (n - 1) / 2];
threads[threadIndex] = new Thread(game);
threads[threadIndex].start();
```

Then Tournament joins each thread in an indexed loop.

## 7) File Handling
FileLogger uses FileWriter for:
- clearFiles(): reset results.txt and write round header.
- logRound(): append one line per round.
- writeLeaderboard(): write ranked CSV output.

Ranking is now read from Scoreboard.getRankedNames(), so FileLogger only writes data in order.

## 8) Strategy Logic (Simplified)
- Grudger: scan opponentHistory with plain for loop; defect forever after first DEFECT.
- RandomAgent: one-line random choice.
- Pavlov: compact win-stay/lose-shift style checks with direct string comparisons.
- TitForTat, AlwaysCooperate, AlwaysDefect: intentionally left simple.

## 9) Scoreboard Ranking Logic
Scoreboard now has getRankedNames():
1. Convert map entries into names[] and values[] arrays.
2. Bubble sort by score descending.
3. Return ranked agent names as ArrayList<String>.

No Comparator, no Collections.sort, no streams.

## 10) Viva-Friendly End-to-End Story
1. Main selects rounds and prints them.
2. Main registers six agents.
3. Tournament runs every unique matchup in parallel.
4. Each Game executes a plain round loop with visible steps.
5. Scoreboard aggregates synchronized scores.
6. FileLogger writes round logs and final leaderboard.
7. DashboardPrinter shows final standings on console.
