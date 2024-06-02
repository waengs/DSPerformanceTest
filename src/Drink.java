import java.util.ArrayList;

public class Drink {
    String name;
    double price;
    int purchaseCount;
    ArrayList<String> toppings;
    ArrayList<Integer> toppingCounts;

    Drink(String name, double price) {
        this.name = name;
        this.price = price;
        this.purchaseCount = 0;
        this.toppings = new ArrayList<>();
        this.toppingCounts = new ArrayList<>();
    }

    void addTopping(String topping) {
        if (!toppings.contains(topping)) {
            toppings.add(topping);
            toppingCounts.add(0);
        }
    }

    void addPurchase(String topping) {
        purchaseCount++;
        int index = toppings.indexOf(topping);
        if (index != -1) {
            toppingCounts.set(index, toppingCounts.get(index) + 1);
        }
    }

    ArrayList<String> getSortedToppings() {
        ArrayList<String> sortedToppings = new ArrayList<>(toppings);
        sortedToppings.sort((t1, t2) -> Integer.compare(toppingCounts.get(toppings.indexOf(t2)), toppingCounts.get(toppings.indexOf(t1))));
        return sortedToppings;
    }

    String getMostPopularTopping() {
        int maxIndex = 0;
        for (int i = 1; i < toppingCounts.size(); i++) {
            if (toppingCounts.get(i) > toppingCounts.get(maxIndex)) {
                maxIndex = i;
            }
        }
        return toppings.size() > 0 ? toppings.get(maxIndex) : "None";
    }
}
