package sem4.jp.innovative.com.pdtournament.agents;

public abstract class Agent implements Decidable {
    private String name;

    public Agent(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
