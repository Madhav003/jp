# Java Concept Analysis for Iterated Prisoner's Dilemma Tournament Simulator

This document analyzes the actual implementation in this codebase and maps each requested Java concept to concrete files, lines, and snippets.

## 1. FUNDAMENTAL OOP CONCEPTS

### 1.1 Classes and Objects

### Class definitions in this codebase

- `Main` in `Main.java:15`
- `Agent` (abstract) in `com/pdtournament/agents/Agent.java:3`
- `AlwaysCooperate` in `com/pdtournament/agents/AlwaysCooperate.java:5`
- `AlwaysDefect` in `com/pdtournament/agents/AlwaysDefect.java:5`
- `TitForTat` in `com/pdtournament/agents/TitForTat.java:5`
- `RandomAgent` in `com/pdtournament/agents/RandomAgent.java:5`
- `Grudger` in `com/pdtournament/agents/Grudger.java:5`
- `Pavlov` in `com/pdtournament/agents/Pavlov.java:5`
- `Game` in `com/pdtournament/core/Game.java:9`
- `Tournament` in `com/pdtournament/core/Tournament.java:9`
- `Scoreboard` in `com/pdtournament/core/Scoreboard.java:5`
- `FileLogger` in `com/pdtournament/io/FileLogger.java:9`
- `DashboardPrinter` in `com/pdtournament/io/DashboardPrinter.java:7`
- `InvalidDecisionException` in `com/pdtournament/exceptions/InvalidDecisionException.java:3`
- `Decidable` interface in `com/pdtournament/agents/Decidable.java:5`

### Object instantiation examples

From `Main.java:20-35`:

```java
Random randomNum = new Random();
ArrayList<Agent> agents = new ArrayList<Agent>();
agents.add(new AlwaysCooperate());
agents.add(new AlwaysDefect());
agents.add(new TitForTat());
agents.add(new Grudger());
agents.add(new RandomAgent());
agents.add(new Pavlov());

Tournament tournament = new Tournament(agents, roundsPerGame);
DashboardPrinter dashboardPrinter = new DashboardPrinter();
```

From `Tournament.java:18-35`:

```java
this.scoreboard = new Scoreboard();
this.fileLogger = new FileLogger();

ArrayList<Thread> threads = new ArrayList<Thread>();
Game game = new Game(agents.get(i), agents.get(j), rounds, scoreboard, fileLogger);
Thread thread = new Thread(game);
```

From `Game.java:23,27-28`:

```java
this.roundResults = new ArrayList<String>();
ArrayList<String> historyA = new ArrayList<String>();
ArrayList<String> historyB = new ArrayList<String>();
```

These are concrete runtime object creations from class blueprints.

### 1.2 Constructors

### Constructor definitions

Base class constructor in `Agent.java:6-8`:

```java
public Agent(String name) {
    this.name = name;
}
```

Concrete subclass constructor in `AlwaysCooperate.java:6-8`:

```java
public AlwaysCooperate() {
    super("AlwaysCooperate");
}
```

Another concrete constructor in `Pavlov.java:6-8`:

```java
public Pavlov() {
    super("Pavlov");
}
```

State-initializing constructor in `Game.java:17-24`:

```java
public Game(Agent agentA, Agent agentB, int rounds, Scoreboard scoreboard, FileLogger fileLogger) {
    this.agentA = agentA;
    this.agentB = agentB;
    this.rounds = rounds;
    this.scoreboard = scoreboard;
    this.fileLogger = fileLogger;
    this.roundResults = new ArrayList<String>();
}
```

State-initializing constructor in `Tournament.java:15-20`:

```java
public Tournament(ArrayList<Agent> agents, int rounds) {
    this.agents = agents;
    this.rounds = rounds;
    this.scoreboard = new Scoreboard();
    this.fileLogger = new FileLogger();
}
```

These constructors initialize object state at creation time.

### 1.3 Access Modifiers

### `public`

- Public class and method APIs are used when code must be accessible across packages.
- Example: `public class Tournament` in `Tournament.java:9`.
- Example: `public HashMap<String, Integer> startTournament()` in `Tournament.java:22`.
- Example: `public interface Decidable` in `Decidable.java:5`.

### `private`

- Private fields hide internals and enforce encapsulation.
- Example: `private Agent agentA;` etc. in `Game.java:10-15`.
- Example: `private HashMap<String, Integer> scores;` in `Scoreboard.java:6`.
- Private helper methods in `Game.java:63` and `Game.java:73` keep validation and payoff logic internal.

### `protected`

- No `protected` members are used in this codebase.
- The design prefers `private` fields with public methods.

### 1.4 Instance Variables vs Local Variables

### Instance variables

Example from `Game.java:10-15`:

```java
private Agent agentA;
private Agent agentB;
private int rounds;
private Scoreboard scoreboard;
private FileLogger fileLogger;
private ArrayList<String> roundResults;
```

These belong to each `Game` object and live for the object lifetime.

### Local variables

Example from `Game.run()` in `Game.java:27-35`:

```java
ArrayList<String> historyA = new ArrayList<String>();
ArrayList<String> historyB = new ArrayList<String>();

for (int round = 1; round <= rounds; round++) {
    String decisionA = getValidDecision(agentA, historyB);
    String decisionB = getValidDecision(agentB, historyA);
    int[] scores = getRoundScores(decisionA, decisionB);
}
```

These exist only within method/loop scope.

### 1.5 Method Definitions and Calls

One full definition-to-call trace:

Method definition in `Tournament.java:22`:

```java
public HashMap<String, Integer> startTournament() {
```

Method call in `Main.java:31-32`:

```java
Tournament tournament = new Tournament(agents, roundsPerGame);
HashMap<String, Integer> finalScores = tournament.startTournament();
```

Another trace:

Method definition in `Game.java:63`:

```java
private String getValidDecision(Agent agent, ArrayList<String> opponentHistory) throws InvalidDecisionException {
```

Method call in `Game.java:32-33`:

```java
String decisionA = getValidDecision(agentA, historyB);
String decisionB = getValidDecision(agentB, historyA);
```

This shows concrete method declaration and actual call sites in program flow.

## 2. INHERITANCE AND INTERFACES

### 2.1 Interface definition and enforced contract

`Decidable` in `com/pdtournament/agents/Decidable.java:5-6`:

```java
public interface Decidable {
    String decide(ArrayList<String> opponentHistory);
}
```

Contract enforced: any implementing type must provide `decide(...)` and return a move string.

### 2.2 Abstract base class and what it provides

`Agent` in `com/pdtournament/agents/Agent.java:3-12`:

```java
public abstract class Agent implements Decidable {
    private String name;

    public Agent(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
```

What `Agent` provides to subclasses:

- Shared identity state (`name` field).
- Shared constructor logic for naming.
- Shared accessor method (`getName()`).
- It ties inheritance to interface implementation (`implements Decidable`).

### 2.3 Concrete subclasses extending Agent

Example 1, `AlwaysDefect.java:5-12`:

```java
public class AlwaysDefect extends Agent {
    public AlwaysDefect() {
        super("AlwaysDefect");
    }

    public String decide(ArrayList<String> opponentHistory) {
        return "DEFECT";
    }
}
```

Example 2, `TitForTat.java:5-15`:

```java
public class TitForTat extends Agent {
    public TitForTat() {
        super("TitForTat");
    }

    public String decide(ArrayList<String> opponentHistory) {
        if (opponentHistory.size() == 0) {
            return "COOPERATE";
        }
        return opponentHistory.get(opponentHistory.size() - 1);
    }
}
```

Example 3, `Grudger.java:10-17`:

```java
public String decide(ArrayList<String> opponentHistory) {
    for (int i = 0; i < opponentHistory.size(); i++) {
        if ("DEFECT".equals(opponentHistory.get(i))) {
            return "DEFECT";
        }
    }
    return "COOPERATE";
}
```

### 2.4 Interface vs abstract base class in this project

- Interface (`Decidable`) enforces behavior contract only: must implement `decide(opponentHistory)`.
- Abstract class (`Agent`) provides shared reusable state and logic: name storage and `getName()`.
- In this project, strategies get both benefits by `extends Agent`, and `Agent` already `implements Decidable`.

### 2.5 `@Override` usage

- There is no explicit `@Override` annotation in strategy classes.
- Example: `AlwaysCooperate.java:10` has `public String decide(...)` without `@Override`.
- Why it matters: `@Override` lets compiler verify a method truly overrides interface/base method and prevents silent signature mistakes.

## 3. DYNAMIC METHOD DISPATCH

### 3.1 Agent references calling `decide()` polymorphically

In `Game.java:10-11`, fields are typed as base class:

```java
private Agent agentA;
private Agent agentB;
```

In `Game.java:63-64`, the key dispatch point is:

```java
private String getValidDecision(Agent agent, ArrayList<String> opponentHistory) throws InvalidDecisionException {
    String decision = agent.decide(opponentHistory);
```

This call is compiled against `Agent/Decidable`, but runtime object can be `TitForTat`, `AlwaysDefect`, `Pavlov`, etc.

### 3.2 Exact runtime polymorphism location

- Exact dynamic dispatch line: `Game.java:64` (`agent.decide(opponentHistory)`).
- Call chain starts in `Game.run()` at `Game.java:32-33`:

```java
String decisionA = getValidDecision(agentA, historyB);
String decisionB = getValidDecision(agentB, historyA);
```

`agentA` and `agentB` were passed from `Tournament` when `Game` was constructed (`Tournament.java:33`).

### 3.3 How Java resolves at runtime here

- The reference type is `Agent`.
- The actual object type is whichever concrete strategy was instantiated in `Main` and passed through `Tournament`.
- JVM virtual method dispatch selects the concrete `decide()` implementation for that object.

## 4. PACKAGES

### 4.1 Package statements used

- `package com.pdtournament.agents;`
  - `Agent.java`, `Decidable.java`, `AlwaysCooperate.java`, `AlwaysDefect.java`, `TitForTat.java`, `RandomAgent.java`, `Grudger.java`, `Pavlov.java`
- `package com.pdtournament.core;`
  - `Game.java`, `Tournament.java`, `Scoreboard.java`
- `package com.pdtournament.io;`
  - `FileLogger.java`, `DashboardPrinter.java`
- `package com.pdtournament.exceptions;`
  - `InvalidDecisionException.java`
- `Main.java` has no package declaration (default package entry point).

### 4.2 Import statements and why each boundary exists

From `Main.java:5-13`:

```java
import com.pdtournament.agents.Agent;
import com.pdtournament.agents.AlwaysCooperate;
import com.pdtournament.agents.AlwaysDefect;
import com.pdtournament.agents.Grudger;
import com.pdtournament.agents.Pavlov;
import com.pdtournament.agents.RandomAgent;
import com.pdtournament.agents.TitForTat;
import com.pdtournament.core.Tournament;
import com.pdtournament.io.DashboardPrinter;
```

Why: `Main` orchestrates startup, so it depends on strategy, core tournament engine, and output printing.

From `Game.java:5-7`:

```java
import com.pdtournament.agents.Agent;
import com.pdtournament.exceptions.InvalidDecisionException;
import com.pdtournament.io.FileLogger;
```

Why: game logic needs strategy decisions, custom error signaling, and persistent logging.

From `Tournament.java:6-7`:

```java
import com.pdtournament.agents.Agent;
import com.pdtournament.io.FileLogger;
```

Why: tournament schedules agent matchups and uses centralized file output.

### 4.3 Separation of concerns reflected by package structure

- `agents`: strategy policy only.
- `core`: execution and shared state (`Game`, `Tournament`, `Scoreboard`).
- `io`: side effects to console/files.
- `exceptions`: reusable domain errors.

This keeps behavior, orchestration, and side effects separated.

## 5. EXCEPTION HANDLING

### 5.1 All try/catch blocks in codebase

### `Game.run()` in `Game.java:31-55`

```java
try {
    String decisionA = getValidDecision(agentA, historyB);
    String decisionB = getValidDecision(agentB, historyA);
    ...
} catch (InvalidDecisionException e) {
    String message = "Invalid decision in game " + agentA.getName() + " vs " + agentB.getName() + ": "
            + e.getMessage();
    System.out.println(message);
    fileLogger.logRound(message);
    break;
}
```

Handles custom strategy decision validation failures.

### `Tournament.startTournament()` in `Tournament.java:41-45`

```java
try {
    threads.get(i).join();
} catch (InterruptedException e) {
    Thread.currentThread().interrupt();
}
```

Handles thread interruption while waiting for match completion.

### `FileLogger.clearFiles()` in `FileLogger.java:13-27`

```java
try {
    writer = new FileWriter("results.txt", false);
    writer.write("Rounds per game: " + roundsPerGame + "\n");
    writer.write("----------------------------------------\n");
} catch (IOException e) {
    System.out.println("Error clearing results.txt: " + e.getMessage());
} finally {
    if (writer != null) {
        try {
            writer.close();
        } catch (IOException e) {
            System.out.println("Error closing results.txt: " + e.getMessage());
        }
    }
}
```

### `FileLogger.logRound()` in `FileLogger.java:33-46`

```java
try {
    writer = new FileWriter("results.txt", true);
    writer.write(line + "\n");
} catch (IOException e) {
    System.out.println("Error writing results.txt: " + e.getMessage());
} finally {
    if (writer != null) {
        try {
            writer.close();
        } catch (IOException e) {
            System.out.println("Error closing results.txt: " + e.getMessage());
        }
    }
}
```

### `FileLogger.writeLeaderboard()` in `FileLogger.java:64-82`

```java
try {
    writer = new FileWriter("leaderboard.csv", false);
    writer.write("Rank,Agent,Coins\n");
    ...
} catch (IOException e) {
    System.out.println("Error writing leaderboard.csv: " + e.getMessage());
} finally {
    if (writer != null) {
        try {
            writer.close();
        } catch (IOException e) {
            System.out.println("Error closing leaderboard.csv: " + e.getMessage());
        }
    }
}
```

### 5.2 Custom exception class, throw site, catch site

Definition in `InvalidDecisionException.java:3-6`:

```java
public class InvalidDecisionException extends Exception {
    public InvalidDecisionException(String message) {
        super(message);
    }
}
```

Throw site in `Game.java:66-67`:

```java
if (!"COOPERATE".equals(decision) && !"DEFECT".equals(decision)) {
    throw new InvalidDecisionException(agent.getName() + " returned: " + decision);
}
```

Catch site in `Game.java:49-55`:

```java
} catch (InvalidDecisionException e) {
    ...
    break;
}
```

### 5.3 `finally` blocks and `throws` declarations

- `finally` appears in `FileLogger.clearFiles`, `FileLogger.logRound`, and `FileLogger.writeLeaderboard`.
- `throws` declaration appears in `Game.java:63`:

```java
private String getValidDecision(Agent agent, ArrayList<String> opponentHistory) throws InvalidDecisionException
```

### 5.4 What if exceptions were not handled

- Without `InvalidDecisionException` handling, one bad strategy return value could terminate a game thread unexpectedly and hide root cause context.
- Without `InterruptedException` handling, interruption status could be lost and thread coordination semantics would be incorrect.
- Without `IOException` handling and `finally`, file writes could fail silently and file handles could leak.

## 6. MULTITHREADING AND FILE HANDLING

### 6.1 `Game` implements `Runnable` and thread wrapping

`Game` declaration in `Game.java:9`:

```java
public class Game implements Runnable {
```

Wrapped in `Thread` in `Tournament.java:33-36`:

```java
Game game = new Game(agents.get(i), agents.get(j), rounds, scoreboard, fileLogger);
Thread thread = new Thread(game);
threads.add(thread);
thread.start();
```

### 6.2 Thread start and `join()` usage

Thread start in `Tournament.java:36` with `thread.start()`.

Waiting for completion in `Tournament.java:40-43`:

```java
for (int i = 0; i < threads.size(); i++) {
    try {
        threads.get(i).join();
```

This guarantees all matches finish before final scores are exported.

### 6.3 All synchronized methods and what each protects

In `Scoreboard.java`:

```java
public synchronized void addAgent(String name)            // line 12
public synchronized void updateScore(String name, int delta) // line 18
public synchronized HashMap<String, Integer> getScores()  // line 29
```

Protected resource: `scores` map (`Scoreboard.java:6`).

Race prevented: concurrent score updates from multiple game threads causing lost updates or inconsistent reads.

In `FileLogger.java`:

```java
public synchronized void clearFiles(int roundsPerGame)         // line 10
public synchronized void logRound(String line)                 // line 30
public synchronized void writeLeaderboard(HashMap<String, Integer> scores) // line 49
```

Protected resources: `results.txt` and `leaderboard.csv` write sequences.

Race prevented: interleaved writes from different game threads that would corrupt file output.

### 6.4 File handling lifecycle in `FileLogger`

Open examples:

```java
writer = new FileWriter("results.txt", false);   // clearFiles, line 14
writer = new FileWriter("results.txt", true);    // logRound, line 34
writer = new FileWriter("leaderboard.csv", false); // writeLeaderboard, line 65
```

Write examples:

```java
writer.write("Rounds per game: " + roundsPerGame + "\n");
writer.write(line + "\n");
writer.write("Rank,Agent,Coins\n");
```

Close examples in all three methods:

```java
if (writer != null) {
    try {
        writer.close();
    } catch (IOException e) {
        ...
    }
}
```

### 6.5 Why `FileLogger` is synchronized

`Game.run()` logs every round (`Game.java:45`) from many concurrent threads. Without synchronization:

- Two threads can open `results.txt` and write overlapping bytes at nearly the same time.
- Log lines may be interleaved or partially overwritten.
- Output ordering and integrity are not guaranteed.

Synchronization serializes these write operations and preserves coherent logs.

## Additional Accuracy Notes

- This codebase has a custom `InvalidDecisionException`, not `InvalidMoveException`.
- There are no explicit synchronized blocks (`synchronized(obj) { ... }`), only synchronized methods.
- There are no explicit `@Override` annotations in subclasses, even though methods do override interface/base signatures.
