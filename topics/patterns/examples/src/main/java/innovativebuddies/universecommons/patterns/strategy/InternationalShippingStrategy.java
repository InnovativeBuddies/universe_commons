package innovativebuddies.universecommons.patterns.strategy;

public class InternationalShippingStrategy implements Strategy {
    @Override
    public double calculateShippingCost(double weight) {
        return weight * 2.0;  // International shipping rate
    }
}
