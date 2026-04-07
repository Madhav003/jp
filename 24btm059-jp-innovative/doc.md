# Iterated Prisoner's Dilemma Tournament Simulator

## 1. Project Goal
This project runs a repeated Prisoner's Dilemma tournament with six strategies. The code is intentionally kept simple so a student can explain the flow in viva from top to bottom.

## 2. Simplified Runtime Flow
1. Main picks rounds per game using one line of random logic.
2. Main prints the selected rounds.
3. Main creates all six agent objects.
4. Main starts Tournament.
5. Tournament creates one thread per unique pair of agents.
6. Each thread runs one Game for the chosen number of rounds.
7. Game updates shared Scoreboard and logs each round with FileLogger.
8. Tournament joins all threads and writes leaderboard.csv.
9. Main prints final standings through DashboardPrinter.

## 3. Package Structure
- com.pdtournament.agents: strategy interface and six strategy classes.
- com.pdtournament.core: game engine, tournament scheduler, shared scoreboard.
- com.pdtournament.io: file output and console dashboard output.
- com.pdtournament.exceptions: custom checked exception for invalid moves.

## 4. Class Responsibility Table
| File/Class | Main Responsibility |
|---|---|
| Main | Startup sequence and wiring all objects |
| Decidable | Defines decide(opponentHistory) contract |
| Agent | Abstract base class with private name and getName() |
| AlwaysCooperate | Always returns COOPERATE |
| AlwaysDefect | Always returns DEFECT |
| TitForTat | First COOPERATE, then copy opponent last move |
| Grudger | Defects forever after seeing first opponent DEFECT |
| RandomAgent | Randomly returns COOPERATE or DEFECT |
| Pavlov | Win-stay/lose-shift style simplified with short string checks |
| Game | Round loop, validation, scoring, history updates, logging |
| Tournament | Pair scheduling, thread array lifecycle, thread join |
| Scoreboard | Synchronized score updates and bubble-sort ranking helper |
| FileLogger | Writes results.txt and leaderboard.csv |
| DashboardPrinter | Prints sorted standings to console |
| InvalidDecisionException | Signals invalid strategy return value |

## 5. Tournament Pseudocode (Current)
```text
startTournament():
    clear output files
    add all agent names to scoreboard

    n = number of agents
    threads = new Thread[n*(n-1)/2]
    index = 0

    for i = 0 to n-1:
        for j = i+1 to n-1:
            create Game(agents[i], agents[j])
            create Thread(game)
            threads[index] = thread
            index = index + 1
            thread.start()

    for k = 0 to index-1:
        threads[k].join()

    write leaderboard using scoreboard.getRankedNames()
    return scoreboard.getScores()
```

## 6. Game Pseudocode (Current)
```text
run():
    historyA = []
    historyB = []

    try:
        for round = 1 to rounds:
            decisionA = agentA.decide(historyB)
            validate decisionA

            decisionB = agentB.decide(historyA)
            validate decisionB

            scores = getRoundScores(decisionA, decisionB)
            scoreboard.updateScore(agentA, scores[0])
            scoreboard.updateScore(agentB, scores[1])

            log round result
            historyA.add(decisionA)
            historyB.add(decisionB)
    catch InvalidDecisionException:
        log error message for that game
```

## 7. Payoff Matrix (Unchanged)
| A vs B | COOPERATE | DEFECT |
|---|---:|---:|
| COOPERATE | 3, 3 | 0, 5 |
| DEFECT | 5, 0 | 1, 1 |

## 8. Ranking Logic (Current)
Scoreboard.getRankedNames() now handles ranking using:
1. entry set to arrays (names[], scores[])
2. plain bubble sort in descending score
3. returns ranked names list

FileLogger only writes CSV rows in that ranked order.

## 9. Example Trace (Single Match Snippet)
Example: TitForTat vs AlwaysDefect, rounds = 3

- Round 1: TitForTat = COOPERATE, AlwaysDefect = DEFECT -> scores 0 and 5
- Round 2: TitForTat = DEFECT, AlwaysDefect = DEFECT -> scores 1 and 1
- Round 3: TitForTat = DEFECT, AlwaysDefect = DEFECT -> scores 1 and 1

This trace matches the current top-to-bottom Game.run flow.

## 10. Why This Version Is Viva-Friendly
- No stream/lambda logic.
- No comparator-based sorting.
- No hidden helper inside round loop for decision validation.
- Thread handling uses a plain array and indexed joins.
- Strategy methods are small and easy to narrate.
