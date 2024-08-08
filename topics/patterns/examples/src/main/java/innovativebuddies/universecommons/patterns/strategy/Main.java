package innovativebuddies.universecommons.patterns.strategy;

public class Main {
    public static void main(String[] args) {
        Context context = new Context();

        // Используем стратегию обычной доставки
        context.setStrategy(new RegularShippingStrategy());
        System.out.println("Regular Shipping Cost: " + context.executeStrategy(10));

        // Используем стратегию экспресс-доставки
        context.setStrategy(new ExpressShippingStrategy());
        System.out.println("Express Shipping Cost: " + context.executeStrategy(10));

        // Используем стратегию международной доставки
        context.setStrategy(new InternationalShippingStrategy());
        System.out.println("International Shipping Cost: " + context.executeStrategy(10));
    }
}
