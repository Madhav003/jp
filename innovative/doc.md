# Iterated Prisoner's Dilemma Tournament Simulator

## 1. Project Overview

### Project Title
Iterated Prisoner's Dilemma Tournament Simulator

### Abstract
The Iterated Prisoner's Dilemma Tournament Simulator is a Java-based academic project that models repeated strategic interactions among autonomous decision-making agents. Each agent follows a predefined behavioral strategy, such as Always Cooperate, Always Defect, Tit-for-Tat, Random, Grudger, or Pavlov, and competes in a full round-robin tournament. In every match, two agents repeatedly choose between cooperation and defection for a fixed number of rounds. A payoff matrix converts each pair of choices into points, and cumulative scores determine tournament rankings.

The simulator is implemented with simple object-oriented Java classes and basic multithreading. Each unique pairwise match runs concurrently in a separate thread, while synchronized score updates and file logging preserve correctness. The project is educationally significant because it demonstrates how local decision rules and repeated interactions produce global social outcomes such as trust, retaliation, exploitation, and stable cooperation.

### Problem the Project Solves
Real systems often involve repeated interactions among self-interested participants. A one-time decision model does not capture how memory, reputation, and retaliation shape long-term behavior. This project solves that gap by providing:

- A repeatable simulation framework for strategic interaction.
- A controlled environment to compare strategies quantitatively.
- A practical demonstration of how cooperation can emerge or collapse over time.
- A software architecture that is simple enough for students and extensible for research exploration.

### Why the Iterated Prisoner's Dilemma Matters
The Iterated Prisoner's Dilemma (IPD) is one of the most influential models in game theory and computational social science. It captures a deep paradox: individually rational short-term choices can produce collectively poor outcomes, while reciprocal behavior can improve long-term welfare.

In computer science, IPD is important because it maps directly to distributed decision-making, protocol compliance, trust modeling, and adaptive agent design. In software engineering education, it provides a compact but powerful case study where algorithmic policy, state/history management, concurrency, and performance all intersect.

### Real-World Applications
| Domain | How IPD Maps to the Domain | Example |
|---|---|---|
| Economics | Firms repeatedly decide whether to compete aggressively or maintain stable pricing | Price wars vs tacit cooperation |
| Evolutionary Biology | Organisms choose cooperative or selfish behavior repeatedly | Reciprocal altruism in social species |
| Distributed Systems | Nodes decide whether to share resources or free-ride | Peer-to-peer bandwidth sharing |
| Cybersecurity | Participants decide whether to follow protocol or exploit weaknesses | Honest signaling vs adversarial behavior |
| AI and Multi-Agent Systems | Agents choose strategic responses in repeated interactions | Reinforcement policies in repeated games |

---

## 2. Background Theory

### Prisoner's Dilemma
The classical Prisoner's Dilemma is a two-player strategic game with two actions per player:

- Cooperate (C)
- Defect (D)

The core condition is that defection yields a higher immediate payoff regardless of the opponent's move, yet mutual cooperation yields a better joint outcome than mutual defection. The standard symbolic ordering is:

T > R > P > S

where:

- T = Temptation payoff (defect when opponent cooperates)
- R = Reward payoff (both cooperate)
- P = Punishment payoff (both defect)
- S = Sucker payoff (cooperate when opponent defects)

### Iterated Prisoner's Dilemma
In IPD, the same two players interact repeatedly across many rounds. This repetition fundamentally changes strategic incentives:

- Past behavior affects future response.
- Cooperation can be rewarded over time.
- Defection can trigger retaliation.
- Stability can emerge from reciprocity.

Hence, IPD is not only a game of immediate utility but also a game of memory and policy.

### Payoff Matrix Used in This Project
This simulator uses a classic numeric matrix:

| Player A \ Player B | Cooperate | Defect |
|---|---:|---:|
| Cooperate | 3, 3 | 0, 5 |
| Defect | 5, 0 | 1, 1 |

This matrix satisfies Prisoner's Dilemma conditions:

- T = 5
- R = 3
- P = 1
- S = 0

And therefore:

- T > R > P > S
- 5 > 3 > 1 > 0

### Cooperation vs Defection
Cooperation means accepting moderate immediate reward to preserve long-term gains through trust and reciprocity. Defection means taking a higher immediate reward at the risk of retaliation and future loss of cooperation. IPD experiments reveal that the best strategy often depends on the population of opponents, noise, horizon length, and scoring policy.

### Classic Strategies in the Simulator
- Tit-for-Tat: Start with cooperation, then copy the opponent's previous move.
- Always Defect: Defect every round.
- Always Cooperate: Cooperate every round.
- Random: Choose C or D probabilistically.
- Grudger: Cooperate until the opponent defects once, then defect forever.
- Pavlov (Win-Stay Lose-Shift): Repeat successful behavior, switch after poor outcomes.

### Why Tournaments Are Used
A tournament evaluates strategies across a diverse opponent population rather than a single matchup. This reveals:

- Robustness across adversarial and cooperative opponents.
- Sensitivity to exploitation.
- Population-level ranking under common conditions.

Tournaments are therefore more informative than isolated two-strategy comparisons.

---

## 3. Project Goals

### Primary Objectives
1. Build a complete IPD tournament simulator in Java.
2. Model pairwise repeated interactions with strategy memory.
3. Compare strategy performance with cumulative scoring and ranking.
4. Execute independent matches concurrently using basic multithreading.
5. Generate clear outputs for console and file-based analysis.

### Secondary Objectives
1. Make architecture simple and readable for academic evaluation.
2. Keep components modular to support extension.
3. Enable reproducible testing with deterministic strategy classes (except Random).
4. Provide implementation quality suitable for innovation project submission.

### Educational Goals
1. Demonstrate game theory through executable software.
2. Apply object-oriented design principles in a non-trivial simulation.
3. Teach synchronization and shared state protection in multithreaded Java.
4. Train students to connect theory, algorithm design, and software architecture.

---

## 4. System Architecture

### High-Level Architecture
The project is organized into three major layers:

- Strategy and agent definitions.
- Tournament and game execution engine.
- Output and reporting subsystem.

~~~text
+------------------------------+
|            Main              |
|  Registers agents, starts    |
|  tournament, prints summary  |
+---------------+--------------+
                |
                v
+------------------------------+
|         Tournament           |
|  Schedules unique pairs,     |
|  creates one thread/match,   |
|  waits for completion        |
+-------+---------------+------+
        |               |
        v               v
+---------------+   +----------------+
|     Game      |   |   Scoreboard   |
| Round loop,   |-->| synchronized   |
| strategy call |   | score updates  |
| payoff calc   |   +----------------+
+-------+-------+
        |
        v
+------------------------------+
|          FileLogger          |
| results.txt + leaderboard.csv|
| synchronized writes          |
+------------------------------+
~~~

### Design Philosophy
1. Keep logic explicit and transparent for academic readability.
2. Minimize hidden framework behavior.
3. Use composition over monolithic classes.
4. Separate game mechanics, strategy policy, and output responsibilities.

### Separation of Concerns
- Main: configuration and startup orchestration.
- Tournament: scheduling and thread lifecycle management.
- Game: per-match round simulation and payoff assignment.
- Agent/Decidable: strategy behavior abstraction.
- Scoreboard: thread-safe aggregation.
- FileLogger and DashboardPrinter: reporting.

### Main Components

#### Tournament Manager
Controls the lifecycle of a full tournament. It initializes scoreboard entries, schedules all unique pairwise games, starts each game in a separate thread, and waits for all threads to finish before producing final ranking data.

#### Strategy Interface
The strategy contract is represented by Decidable. Any class that can decide based on opponent history can participate in games. This allows interchangeable strategy policies.

#### Player Class (Conceptual) and Agent Class (Implementation)
In many designs, Player wraps strategy and identity. In this implementation, Agent fulfills this role directly. Agent contains player identity (name) and strategy capability via the Decidable contract.

#### Match Engine
Game is the match engine. It executes the per-round interaction loop, validates moves, computes payoffs, updates global scores, and logs outcomes.

#### Scoreboard and Ranking
Scoreboard stores cumulative integer points per agent and exposes synchronized update/get methods. Ranking is generated by sorting entries in descending order before display/export.

#### Thread Manager
Tournament acts as a simple thread manager by constructing Thread objects for each match and using join to enforce full completion before final result generation.

---

## 5. Object-Oriented Design

### Core OOP Principles Applied
1. Abstraction: strategy behavior is abstracted by Decidable.
2. Inheritance: concrete strategies inherit from Agent.
3. Polymorphism: Tournament and Game operate on Agent references without strategy-specific branching.
4. Encapsulation: score state is private in Scoreboard with controlled synchronized mutation.

### Strategy Pattern Usage
The design maps directly to the Strategy pattern:

- Strategy interface: Decidable
- Concrete strategies: AlwaysCooperate, AlwaysDefect, TitForTat, RandomAgent, Grudger, Pavlov
- Context: Game invokes decide on the current strategy holder (Agent)

This separation enables new strategies without changing game mechanics.

### Class Responsibilities
| Class | Responsibility |
|---|---|
| Decidable | Defines strategy decision contract using opponent history |
| Agent | Holds strategy identity and serves as abstract base for concrete agents |
| AlwaysCooperate | Returns Cooperate every round |
| AlwaysDefect | Returns Defect every round |
| TitForTat | Starts cooperative, then mirrors opponent's last move |
| RandomAgent | Produces stochastic C or D decisions |
| Grudger | Switches permanently to Defect after first opponent defection |
| Pavlov | Win-Stay Lose-Shift behavior |
| Game | Runs one repeated match, validates decisions, applies payoffs |
| Tournament | Schedules all pairwise games and handles concurrency lifecycle |
| Scoreboard | Thread-safe global score storage and retrieval |
| FileLogger | Thread-safe round logging and leaderboard export |
| DashboardPrinter | Human-readable final standings display |
| Main | Program entry and configuration |

### Why Each Class Exists
Each class has a single dominant purpose. This reduces coupling and simplifies testing. For example, changing score aggregation logic does not require editing strategy classes. Adding a strategy does not require changing Tournament. Replacing file output does not affect game mechanics.

---

## 6. Multithreading Design

### Why Multithreading Is Used
A tournament with many players creates many independent pairwise matches. Since each match is logically independent during round execution, running matches concurrently improves throughput and demonstrates practical parallelism.

### How Matches Run Concurrently
1. Tournament generates each unique pair (i, j).
2. For each pair, it creates a Game object.
3. It wraps Game in a Thread and starts it immediately.
4. After launching all matches, Tournament calls join on every thread.

This is simple and effective for a medium number of strategies.

### Thread Safety Considerations
Concurrent matches share two global resources:

- Global score map in Scoreboard.
- Output files in FileLogger.

Without synchronization, updates and writes may interleave incorrectly.

### Potential Race Conditions
1. Two matches update the same player score simultaneously.
2. Two matches write to results.txt simultaneously.
3. Final ranking is read before all matches finish.

### Synchronization Approach
1. Scoreboard methods are synchronized to serialize updates and reads.
2. FileLogger methods are synchronized to serialize file writes.
3. Tournament waits for all match threads using join before ranking.

### Thread vs ExecutorService
This implementation uses basic Thread for educational clarity and minimal abstraction. For very large tournaments, ExecutorService with a bounded pool would provide better resource control and scheduling efficiency.

### Performance Impact
If there are n players, unique matches are n(n-1)/2. Running them in parallel reduces wall-clock time compared to strict sequential execution, especially when rounds per match are high.

---

## 7. Tournament Logic

### Step-by-Step Process
1. Strategies are registered in Main.
2. Agent instances are created and placed in a list.
3. Tournament initializes scoreboard entries to zero.
4. Round-robin scheduling creates each unique pair once.
5. Each pair plays a repeated match for roundsPerGame rounds.
6. Match threads update global scores concurrently.
7. Tournament waits for all threads.
8. Final scores are sorted and exported as leaderboard.

### Pseudocode for Tournament Loop
~~~text
function startTournament(agents, roundsPerGame):
    clear results file and write rounds header
    for each agent in agents:
        scoreboard.addAgent(agent.name)

    threads = []

    for i from 0 to agents.size - 1:
        for j from i + 1 to agents.size - 1:
            game = new Game(agents[i], agents[j], roundsPerGame, scoreboard, fileLogger)
            t = new Thread(game)
            threads.add(t)
            t.start()

    for each t in threads:
        t.join()

    finalScores = scoreboard.getScores()
    writeLeaderboard(finalScores)
    return finalScores
~~~

### Round-Robin Interpretation
This is a full unique-pair round robin. Every pair interacts exactly once as a repeated game. The roundsPerGame value controls how many rounds occur within each pairwise match.

---

## 8. Game Logic

### Round Execution Model
For each round in a match:

1. Agent A decides using Agent B history.
2. Agent B decides using Agent A history.
3. Decisions are validated (must be Cooperate or Defect).
4. Payoffs are computed via matrix.
5. Global scores are updated.
6. Round result is logged.
7. Histories are appended for next round memory.

### Move Selection
A strategy receives only opponent history in this implementation. This keeps strategy APIs minimal and focuses on reaction-based policy design.

### History Storage
Two ArrayList history structures are maintained per game:

- historyA stores past moves of Agent A (visible to Agent B as opponent history).
- historyB stores past moves of Agent B (visible to Agent A as opponent history).

Histories reset for each new pairwise match.

### Standard Payoff Matrix
| A \ B | C | D |
|---|---:|---:|
| C | 3,3 | 0,5 |
| D | 5,0 | 1,1 |

This matrix promotes short-term temptation and long-term reciprocity.

---

## 9. Strategy Implementations

### Always Cooperate
Behavior: always returns Cooperate.

Effect: performs well with reciprocal strategies, vulnerable to exploiters.

### Always Defect
Behavior: always returns Defect.

Effect: gains strongly against unconditional cooperators, often underperforms in highly reciprocal ecosystems.

### Tit-for-Tat
Behavior: first move Cooperate, then mirror opponent's previous action.

Effect: simple, retaliatory, and forgiving when opponent returns to cooperation.

### Random
Behavior: chooses C or D randomly each round.

Effect: introduces stochasticity, difficult to predict, often inconsistent in long-term score accumulation.

### Pavlov (Win-Stay Lose-Shift)
Behavior: repeats previous decision if outcome was favorable, flips otherwise.

Effect: adapts quickly and can stabilize with cooperative partners while reacting to poor payoffs.

### Grudger
Behavior: cooperates until opponent defects once, then defects forever.

Effect: highly punitive; robust against repeated exploitation but can sustain mutual defection deadlocks.

---

## 10. Data Structures Used

### Player and Strategy Collection
Structure: ArrayList of Agent.

Reason: preserves deterministic registration order and efficient iteration for scheduling.

### Match History
Structure: ArrayList of String per player history per game.

Reason: straightforward indexed access for history-based strategies.

### Score Tracking
Structure: HashMap from agent name to integer score.

Reason: average O(1) updates and lookups under concurrent access with synchronized method guards.

### Tournament Scheduling
Structure: nested index loops over ArrayList.

Reason: simple generation of all unique unordered pairs without duplicates.

### Thread Tracking
Structure: ArrayList of Thread.

Reason: supports batch thread start and join synchronization.

---

## 11. Example Tournament Flow

Consider 4 strategies:

- AlwaysCooperate (AC)
- AlwaysDefect (AD)
- TitForTat (TFT)
- Grudger (G)

Unique pairings:

1. AC vs AD
2. AC vs TFT
3. AC vs G
4. AD vs TFT
5. AD vs G
6. TFT vs G

Assume 3 rounds per match for demonstration.

### Sample Match Trace: AD vs TFT
Round 1:

- AD chooses D
- TFT chooses C (first move)
- Payoff: AD +5, TFT +0

Round 2:

- AD chooses D
- TFT mirrors last opponent move and chooses D
- Payoff: AD +1, TFT +1

Round 3:

- AD chooses D
- TFT chooses D
- Payoff: AD +1, TFT +1

Subtotal from this match:

- AD: 7
- TFT: 2

### Score Update Illustration
After each round, Scoreboard receives synchronized updates for both players. This ensures no lost updates even if other matches are simultaneously writing.

### Ranking Generation
After all pair threads finish:

1. Final score map is copied from Scoreboard.
2. Entries are sorted descending.
3. Console and CSV outputs are produced.

---

## 12. Extensibility

### Adding a New Strategy
The architecture is intentionally open for extension.

Steps:

1. Create a new class that extends Agent.
2. Implement decide(opponentHistory).
3. Register the new strategy in Main.

### Example: Forgiver Strategy
~~~java
package com.pdtournament.agents;

import java.util.ArrayList;

public class Forgiver extends Agent {
    public Forgiver() {
        super("Forgiver");
    }

    public String decide(ArrayList<String> opponentHistory) {
        if (opponentHistory.size() == 0) {
            return "COOPERATE";
        }

        int defections = 0;
        for (String move : opponentHistory) {
            if ("DEFECT".equals(move)) {
                defections++;
            }
        }

        if (defections > opponentHistory.size() / 2) {
            return "DEFECT";
        }

        return "COOPERATE";
    }
}
~~~

No changes are required in Game or Tournament for this addition.

---

## 13. Performance Considerations

### Time Complexity
Let n be number of players and r be rounds per game.

- Number of unique matches: n(n-1)/2
- Work per match: O(r)
- Total computational work: O(n^2 r)

### Memory Complexity
- Scores: O(n)
- Histories per active match: O(r)
- Active matches in worst case: O(n^2)

In practical execution, memory is dominated by concurrent histories and thread objects.

### Thread Utilization
Current approach starts one thread per match. This is suitable for small to moderate n, common in classroom simulations. For larger n, thread pool scheduling should be considered.

### I/O Considerations
Frequent synchronized file writes can become a bottleneck for very high round counts. Buffered batching could improve throughput.

---

## 14. Limitations

1. Thread-per-match model may not scale efficiently for very large populations.
2. Strategies are rule-based and do not learn from tournament-level outcomes.
3. Random strategy introduces non-reproducibility unless seeded randomness is added.
4. Extensive per-round logging can increase I/O overhead.
5. Current strategy API receives only opponent history, not full game context.
6. No explicit noise model for accidental move flips.

---

## 15. Possible Future Improvements

1. Graphical dashboard for live score trajectories and match visualization.
2. Evolutionary mode with elimination, mutation, and population dynamics.
3. Reinforcement learning agents that adapt using reward history.
4. Configurable tournament presets with external JSON input.
5. ExecutorService-based bounded thread pool for large-scale runs.
6. Statistical analysis module for repeated tournament runs and confidence intervals.
7. Networked tournament mode for distributed execution across machines.
8. Persistence layer for historical tournament archives and replay.

---

## 16. How to Run the Project

### Prerequisites
- Java Development Kit (JDK 8 or newer recommended)
- Command-line access in project root directory

### Compilation and Execution
The following command style is accepted for simple submission demonstration:

~~~bash
javac *.java
java Main
~~~

For the package-based multi-folder project layout, compile all sources recursively:

~~~bash
javac Main.java com/pdtournament/agents/*.java com/pdtournament/core/*.java com/pdtournament/io/*.java com/pdtournament/exceptions/*.java
java Main
~~~

### Runtime Behavior
At startup, the program randomly selects rounds per game in a bounded range and prints the selected value. It also writes this value at the top of results.txt.

---

## 17. Example Output

### Console Output (Illustrative)
~~~text
Final Tournament Standings
-------------------------
1. AlwaysDefect - 440
2. Grudger - 410
3. TitForTat - 394
4. Pavlov - 375
5. AlwaysCooperate - 339
6. RandomAgent - 315
Rounds per game: 32

Detailed rounds written to results.txt
Final leaderboard written to leaderboard.csv
~~~

### Results File Header (Illustrative)
~~~text
Rounds per game: 32
----------------------------------------
AlwaysCooperate vs AlwaysDefect | Round 1/32 | COOPERATE | DEFECT | 0 5
...
~~~

### Leaderboard CSV (Illustrative)
~~~text
Rank,Agent,Coins
1,AlwaysDefect,440
2,Grudger,410
3,TitForTat,394
...
~~~

---

## 18. Conclusion

This project demonstrates how simple strategic rules can produce complex emergent behavior in repeated competitive-cooperative systems. Through a clean Java architecture and basic multithreading, it connects abstract game-theoretic concepts to executable simulation outcomes. The tournament framework highlights that success depends not only on immediate payoff maximization but also on interaction structure, memory depth, and population composition.

Academically, the simulator serves as a strong interdisciplinary artifact spanning game theory, algorithmic strategy, software engineering, and concurrent programming. Practically, it provides a reusable foundation for future experimentation, including adaptive agents, larger ecosystems, and advanced analytics.

Most importantly, the project shows a core principle of intelligent systems and social computation: cooperation is not a fixed moral stance or a static algorithmic choice; it is a dynamic equilibrium shaped by incentives, repetition, and response.
