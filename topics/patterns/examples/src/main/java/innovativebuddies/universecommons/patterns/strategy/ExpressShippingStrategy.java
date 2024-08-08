package innovativebuddies.universecommons.patterns.strategy;

public class ExpressShippingStrategy implements Strategy {
    @Override
    public double calculateShippingCost(double weight) {
        return weight * 1.5;  // Express shipping rate
    }
}
