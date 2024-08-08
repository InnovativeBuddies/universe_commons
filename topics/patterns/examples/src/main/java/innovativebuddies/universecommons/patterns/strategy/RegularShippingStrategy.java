package innovativebuddies.universecommons.patterns.strategy;

public class RegularShippingStrategy implements Strategy {
    @Override
    public double calculateShippingCost(double weight) {
        return weight * 1.0;  // Regular shipping rate
    }
}
