# Code Timeline and File-by-File Walkthrough (Innovative Assignment)

This file explains what happens after you press Run, and then walks through every source file in the innovative assignment in simple, viva-friendly language.

## 1) What happens after you press Run

### Step 1: Java starts Main
- File: Main.java
- Entry point: main(String[] args)
- Java begins execution here first.

### Step 2: Number of rounds is chosen
- Main picks rounds per game using random logic:
- `int roundsPerGame = 20 + new Random().nextInt(1000);`
- So rounds can be from 20 to 1019.
- Main prints this value on console.

### Step 3: Agent objects are created
- Main creates an ArrayList of Agent.
- It adds six strategy objects:
- AlwaysCooperate, AlwaysDefect, TitForTat, Grudger, RandomAgent, Pavlov.

### Step 4: Tournament object is created
- Main calls `new Tournament(agents, roundsPerGame)`.
- Tournament internally creates:
- one Scoreboard object
- one FileLogger object

### Step 5: Tournament starts
- Main calls `startTournament()`.
- Tournament first clears `results.txt` and writes a header with rounds per game.

### Step 6: Scoreboard is initialized
- Tournament loops over all agents.
- Each agent name is added to score map with starting score 0.

### Step 7: Match threads are prepared and started
- Tournament computes thread array size for self-play schedule:
- `n * (n + 1) / 2`
- It loops pairs as `for (i)`, `for (j = i; j < n; j++)`.
- This includes self-play games like Grudger vs Grudger.
- For each pair:
- create Game object
- wrap it in Thread
- start thread

### Step 8: Every thread runs one Game
- Each Game has its own local histories:
- historyA, historyB
- For each round:
- Agent A decides based on B history
- decision is validated (must be COOPERATE or DEFECT)
- Agent B decides based on A history
- decision is validated
- payoff is computed by matrix
- scoreboard is updated
- round result line is logged to file
- histories are updated

### Step 9: Invalid decisions are handled
- If any strategy returns invalid text, Game throws InvalidDecisionException.
- Game catches it, logs one error line, and stops that match.

### Step 10: Tournament waits for all games
- Tournament runs a join loop on every started thread.
- Only after all games finish, tournament continues.

### Step 11: Ranking is created
- Tournament asks Scoreboard for final scores.
- FileLogger writes leaderboard.csv.
- Scoreboard gives ranked names sorted by score descending.

### Step 12: Main prints final standings
- Main gets final score map returned from tournament.
- Main sends it to DashboardPrinter.
- DashboardPrinter sorts and prints rank list on console.

### Step 13: Program prints output file messages
- Main prints:
- detailed rounds are in results.txt
- final leaderboard is in leaderboard.csv
- Program exits.

## 2) File-by-file explanation of every block

## Main.java

### Imports block
- Imports Java utility classes:
- ArrayList, HashMap, Random
- Imports all project classes it needs:
- agent classes, Tournament, DashboardPrinter

### Class block
- `public class Main` is the startup class.

### main method block
- Chooses rounds per game and prints it.
- Creates list of agents and adds all six strategy objects.
- Creates Tournament with agents and rounds.
- Starts tournament and receives final score map.
- Creates DashboardPrinter and prints standings.
- Prints where result files are saved.

## com/pdtournament/agents/Decidable.java

### Interface block
- Defines one contract method:
- `String decide(ArrayList<String> opponentHistory);`
- Any strategy class must implement this decision function.

## com/pdtournament/agents/Agent.java

### Class declaration block
- `Agent` is abstract and implements `Decidable`.
- So each concrete agent inherits from Agent and must provide decide().

### Field block
- Private field `name` stores strategy name.

### Constructor block
- Sets the agent name.

### Getter block
- `getName()` returns the private name safely.

## com/pdtournament/agents/AlwaysCooperate.java

### Constructor block
- Calls `super("AlwaysCooperate")` to set name.

### decide method block
- Always returns `"COOPERATE"`.
- Ignores history.

## com/pdtournament/agents/AlwaysDefect.java

### Constructor block
- Calls `super("AlwaysDefect")`.

### decide method block
- Always returns `"DEFECT"`.
- Ignores history.

## com/pdtournament/agents/TitForTat.java

### Constructor block
- Calls `super("TitForTat")`.

### decide method block
- If no history, starts with `"COOPERATE"`.
- Else returns opponent's last move.
- This is mirror behavior.

## com/pdtournament/agents/Grudger.java

### Constructor block
- Calls `super("Grudger")`.

### decide method block
- Scans full opponent history using index loop.
- If any past move is `"DEFECT"`, returns `"DEFECT"` immediately.
- If no defection found, returns `"COOPERATE"`.

## com/pdtournament/agents/RandomAgent.java

### Constructor block
- Calls `super("RandomAgent")`.

### decide method block
- One-line random choice.
- 50% chance cooperate, 50% chance defect.

## com/pdtournament/agents/Pavlov.java

### Constructor block
- Calls `super("Pavlov")`.

### decide method block
- If no history, starts with `"COOPERATE"`.
- Reads opponent's most recent move.
- Sets own previous move default to `"COOPERATE"`.
- If enough history and previous opponent move was defect, own previous is set to `"DEFECT"`.
- If both previous moves match (CC or DD), returns `"COOPERATE"`.
- Otherwise returns `"DEFECT"`.

## com/pdtournament/core/Game.java

### Class and field block
- Stores two agents, number of rounds, shared scoreboard, shared file logger.
- Keeps local `roundResults` list.

### Constructor block
- Receives all dependencies and saves them in fields.
- Creates empty roundResults list.

### run method block
- Creates local histories for each player.
- Wraps main round loop in try/catch for InvalidDecisionException.
- Each round:
- asks both agents for decisions
- validates decisions strictly
- computes score pair with payoff matrix helper
- updates shared scoreboard
- builds readable round string
- saves string in roundResults and logs it to file
- appends decisions to histories
- Catch block logs invalid decision message and ends game.

### getRoundResults block
- Returns collected round lines for this game object.

### getRoundScores block
- Implements payoff matrix:
- C/C => 3,3
- D/D => 1,1
- D/C => 5,0
- C/D => 0,5
- Returns int array of two scores.

## com/pdtournament/core/Scoreboard.java

### Imports and helper comparator block
- Uses map/list utilities and Comparator.
- Internal `ScoreEntryComparator` sorts entries descending by score.

### Field block
- `scores` map stores total score per agent name.

### Constructor block
- Creates empty HashMap.

### addAgent block
- Synchronized method.
- Adds missing agent with score 0.

### updateScore block
- Synchronized method.
- Ensures key exists.
- Adds delta to current score.

### getScores block
- Synchronized method.
- Returns score map reference currently stored.

### getRankedNames block
- Synchronized method.
- Converts map entry set to list.
- Sorts with `.sort(new ScoreEntryComparator())`.
- Builds and returns ordered agent-name list.

## com/pdtournament/core/Tournament.java

### Class and field block
- Stores agents list, rounds, scoreboard, logger.

### Constructor block
- Saves input agents and rounds.
- Creates scoreboard and file logger objects.

### startTournament block
- Clears output file and writes rounds header.
- Adds all agent names to scoreboard.
- Computes number of matches with self-play formula: `n*(n+1)/2`.
- Creates Thread array of that size.
- Nested loop creates each game pair with `j = i`.
- Starts each game thread immediately.
- Join loop waits for all threads.
- Gets final scores.
- Writes leaderboard through FileLogger.
- Returns final score map.

## com/pdtournament/io/FileLogger.java

### clearFiles block
- Synchronized method.
- Opens results.txt in overwrite mode.
- Writes rounds header and separator line.
- Closes file safely in finally.

### logRound block
- Synchronized method.
- Opens results.txt in append mode.
- Writes one line per call.
- Closes file safely in finally.

### writeLeaderboard block
- Synchronized method.
- Gets score map and ranked names from Scoreboard.
- Opens leaderboard.csv in overwrite mode.
- Writes header row.
- Writes each rank as CSV line: rank,name,coins.
- Closes file safely in finally.

## com/pdtournament/io/DashboardPrinter.java

### Helper comparator block
- Internal comparator sorts score entries descending.

### printScoreboard block
- Receives score map.
- Converts map entries to list.
- Sorts list using `.sort(...)`.
- Prints title lines.
- Prints each row with rank, agent name, and score.

## com/pdtournament/exceptions/InvalidDecisionException.java

### Exception class block
- Custom checked exception extends Exception.
- Constructor takes message and passes it to parent class.
- Used when an agent returns an invalid move string.

## 3) How files connect to each other

### Main connections
- Main uses Tournament and DashboardPrinter.

### Tournament connections
- Tournament creates and uses Scoreboard, FileLogger, and Game.

### Game connections
- Game calls Agent decide() methods.
- Game uses Scoreboard to add points.
- Game uses FileLogger to record rounds.
- Game throws/catches InvalidDecisionException for bad decisions.

### Agent hierarchy connections
- All strategy files extend Agent.
- Agent implements Decidable interface contract.

## 4) Runtime files produced

### results.txt
- Created/overwritten at tournament start.
- Gets one line per round from all games.

### leaderboard.csv
- Created/overwritten after all threads finish.
- Stores final ranking in CSV format.

## 5) Existing non-runtime markdown files in this folder

### doc.md
- Project documentation and pseudocode.
- Not executed by Java runtime.

### explain.md
- Concept mapping for viva.
- Not executed by Java runtime.

### changes.md
- Change log of refactoring decisions.
- Not executed by Java runtime.
