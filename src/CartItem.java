public class CartItem {
    Drink drink;
    String topping;
    double finalPrice;

    CartItem(Drink drink, String topping, double finalPrice) {
        this.drink = drink;
        this.topping = topping;
        this.finalPrice = finalPrice;
    }
}
