package innovativebuddies.universecommons.patterns.strategy;

public class Context {
    private Strategy strategy;

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public double executeStrategy(double weight) {
        return strategy.calculateShippingCost(weight);
    }
}
