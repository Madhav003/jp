# changes.md

| What changed | Reason |
|---|---|
| Pavlov decide() was shortened to direct history-based string checks with no helper method. | Keeps the strategy readable and easy to explain as win-stay/lose-shift behavior. |
| RandomAgent decide() was reduced to one line using Math.random(). | Matches the requested minimal form and keeps intent obvious. |
| Grudger decide() stays as a plain for loop scanning for "DEFECT". | This is the clearest possible implementation for viva explanation. |
| Game.run() now uses one top-to-bottom round loop with inline decision validation. | Students can trace decision, validation, scoring, logging, and history updates in one place. |
| Game keeps getRoundScores() as a private payoff lookup method. | Preserves clean matrix logic without cluttering the round loop. |
| Tournament now uses Thread[] sized n*(n-1)/2 and indexed joins. | Simplifies thread lifecycle logic and removes dynamic thread list management. |
| Tournament pair loop now runs only unique pairs using j = i + 1. | Aligns with the required thread-array size formula for pairwise games. |
| Scoreboard.getScores() now returns the raw HashMap. | Matches the requested simple access style. |
| Scoreboard gained getRankedNames() with names[] and scores[] bubble sort. | Moves ranking into Scoreboard and avoids comparators/Collections.sort. |
| FileLogger.writeLeaderboard() now uses Scoreboard.getRankedNames(). | Removes sorting complexity from file I/O code. |
| Main rounds-per-game selection is now one visible line: 20 + new Random().nextInt(31). | Makes startup logic direct and aligned with the required 20-50 range. |
| Main prints selected rounds before tournament start. | Gives immediate runtime clarity to the user. |
| doc.md was rewritten with updated pseudocode, class responsibilities, and simplified trace. | Documentation now mirrors the current simplified execution flow. |
| explain.md was rewritten with short concept-to-code mapping for viva delivery. | Makes each required Java concept easy to narrate with compact examples. |
