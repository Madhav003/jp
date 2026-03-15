# Iterated Prisoner's Dilemma Tournament Simulator

## 1) Goal of the project
This project simulates a full Iterated Prisoner's Dilemma tournament using simple Java classes and basic multithreading.

Each pair of agents plays multiple rounds.
A tournament score is maintained for every agent.
Results are shown in terminal and also written to files.

## 2) Package structure
Project is divided into four packages exactly as required.

- com.pdtournament.agents
- com.pdtournament.core
- com.pdtournament.io
- com.pdtournament.exceptions

Main.java is at project root and starts everything.

## 3) Agent design
All strategy agents extend one abstract base class Agent.

### 3.1 Decidable interface
File: com/pdtournament/agents/Decidable.java

Method:
- String decide(ArrayList<String> opponentHistory)

Every agent must return only:
- COOPERATE
- DEFECT

### 3.2 Abstract Agent class
File: com/pdtournament/agents/Agent.java

Responsibilities:
- Store agent name
- Provide getName()
- Force subclasses to implement decide() via Decidable

### 3.3 Strategy agents
Each strategy is in its own file.

1. AlwaysCooperate
- Always returns COOPERATE.

2. AlwaysDefect
- Always returns DEFECT.

3. TitForTat
- If first round: COOPERATE
- Else copy opponent's previous move

4. Grudger
- Starts with COOPERATE
- If opponent defects once, defects forever

5. RandomAgent
- Uses Math.random()
- >= 0.5 -> COOPERATE
- < 0.5 -> DEFECT

6. Pavlov
- Round 1 starts with COOPERATE
- For each previous round outcome:
  - if reward was +1 or +2, repeat previous move
  - if reward was 0 or -1, switch move
- This is implemented by replaying rounds from opponentHistory in order

## 4) Core tournament logic

### 4.1 Scoreboard
File: com/pdtournament/core/Scoreboard.java

Uses:
- HashMap<String, Integer> scores

Important methods:
- addAgent(String name)
- synchronized updateScore(String name, int delta)
- synchronized getScores()

Why synchronized:
- Many Game threads update scores at same time
- synchronized prevents race conditions

Minimum score rule:
- If score goes below 0, it is set back to 0

### 4.2 Game
File: com/pdtournament/core/Game.java

Implements Runnable so one game can run inside one Thread.

What one Game object does:
- Takes two agents, number of rounds, shared Scoreboard, shared FileLogger
- Maintains two history lists:
  - historyA = decisions of Agent A
  - historyB = decisions of Agent B
- For each round:
  - get decision from each agent using opponent history
  - validate decision (must be COOPERATE or DEFECT)
  - apply payoff matrix
  - update shared scoreboard
  - write one line to results.txt
  - append round result to internal ArrayList

Round payoff matrix used:
- C/C -> +1, +1
- D/D -> +0, +0
- D/C -> +2, -1
- C/D -> -1, +2

Invalid decision handling:
- Custom checked exception InvalidDecisionException is thrown
- Game catches it, logs message, and stops that game safely

### 4.3 Tournament
File: com/pdtournament/core/Tournament.java

Responsibilities:
- Create all round-robin pairings
- Start one Thread per Game
- join all threads so main flow waits for completion
- collect final scores
- write final leaderboard.csv

Round-robin logic:
- Nested loops for i and j where j > i
- This ensures each unique pair plays exactly once

## 5) Input/output classes

### 5.1 FileLogger
File: com/pdtournament/io/FileLogger.java

Uses only FileWriter as required.

Methods:
- clearFiles() -> clears results.txt before tournament starts
- logRound(String line) -> appends one round line to results.txt
- writeLeaderboard(HashMap<String, Integer> scores) -> writes ranked standings to leaderboard.csv

All file operations are inside try-catch blocks.

results.txt line format:
- AgentA vs AgentB | Round X | DecisionA | DecisionB | ScoreA ScoreB

leaderboard.csv format:
- Rank,Agent,Coins
- one row per agent in descending score order

### 5.2 DashboardPrinter
File: com/pdtournament/io/DashboardPrinter.java

Method:
- printScoreboard(HashMap scores)

Behavior:
- Takes score map
- Sorts entries by coin total descending using simple loops
- Prints rank list in terminal

## 6) Exception class
File: com/pdtournament/exceptions/InvalidDecisionException.java

- Custom checked exception
- Extends Exception
- Used when any agent returns an invalid decision string

## 7) Main entry point
File: Main.java

Main flow:
1. Set constant ROUNDS_PER_GAME
2. Create all 6 agents
3. Create Tournament object
4. Start tournament
5. Print final scoreboard in terminal
6. Show output file names

No external libraries are used.
No Maven/Gradle is needed.

## 8) Multithreading model
- One Game = one Runnable
- Tournament starts one Thread for each pair game
- Shared resources:
  - Scoreboard
  - FileLogger
- Synchronization is used in:
  - Scoreboard update and read methods
  - FileLogger write methods

This keeps updates safe even when many games run at once.

## 9) How to compile and run
Open terminal in project root folder where Main.java exists.

Compile:

```bash
javac Main.java com/pdtournament/agents/*.java com/pdtournament/core/*.java com/pdtournament/io/*.java com/pdtournament/exceptions/*.java
```

Run:

```bash
java Main
```

After run, check files:
- results.txt
- leaderboard.csv

## 10) Why this solution is viva-friendly
- Small classes with clear purpose
- Simple package structure
- Simple loops and conditionals
- Basic Thread and Runnable usage
- No advanced Java features
- Clear mapping between strategy rules and decide() logic
- Directly testable with javac and java commands
