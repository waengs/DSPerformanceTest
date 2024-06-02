import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

class PerformanceTest {
    private static final int NUM_USERS = 100000;
    private static final int NUM_DRINKS = 1000;
    private static final int NUM_CART_ITEMS = 10000;
    private static Random random = new Random();

    public static void main(String[] args) {
        // HashMap setup
        HashMap<String, User> userHashMap = new HashMap<>();
        HashMap<String, Drink> drinkHashMap = new HashMap<>();
        HashMap<Integer, CartItem> cartHashMap = new HashMap<>();

        // ArrayList setup
        ArrayList<User> userArrayList = new ArrayList<>();
        ArrayList<Drink> drinkArrayList = new ArrayList<>();
        ArrayList<CartItem> cartArrayList = new ArrayList<>();

        // Populate and measure performance
        long hashMapAddUsersTime = populateUsers(userHashMap);
        long arrayListAddUsersTime = populateUsers(userArrayList);

        long hashMapRetrieveUsersTime = retrieveUsers(userHashMap);
        long arrayListRetrieveUsersTime = retrieveUsers(userArrayList);

        long hashMapAddDrinksTime = populateDrinks(drinkHashMap);
        long arrayListAddDrinksTime = populateDrinks(drinkArrayList);

        long hashMapAddCartItemsTime = populateCartItems(cartHashMap, drinkHashMap);
        long arrayListAddCartItemsTime = populateCartItems(cartArrayList, drinkArrayList);

        System.out.println("HashMap add users time: " + hashMapAddUsersTime + " ms");
        System.out.println("ArrayList add users time: " + arrayListAddUsersTime + " ms");

        System.out.println("HashMap retrieve users time: " + hashMapRetrieveUsersTime + " ms");
        System.out.println("ArrayList retrieve users time: " + arrayListRetrieveUsersTime + " ms");

        System.out.println("HashMap add drinks time: " + hashMapAddDrinksTime + " ms");
        System.out.println("ArrayList add drinks time: " + arrayListAddDrinksTime + " ms");

        System.out.println("HashMap add cart items time: " + hashMapAddCartItemsTime + " ms");
        System.out.println("ArrayList add cart items time: " + arrayListAddCartItemsTime + " ms");
    }

    private static long populateUsers(Map<String, User> userMap) {
        long start = System.nanoTime();
        for (int i = 0; i < NUM_USERS; i++) {
            String username = "user" + i;
            userMap.put(username, new User(username, "password" + i));
        }
        long end = System.nanoTime();
        return TimeUnit.NANOSECONDS.toMillis(end - start);
    }

    private static long populateUsers(ArrayList<User> userList) {
        long start = System.nanoTime();
        for (int i = 0; i < NUM_USERS; i++) {
            String username = "user" + i;
            userList.add(new User(username, "password" + i));
        }
        long end = System.nanoTime();
        return TimeUnit.NANOSECONDS.toMillis(end - start);
    }

    private static long retrieveUsers(Map<String, User> userMap) {
        long start = System.nanoTime();
        for (int i = 0; i < NUM_USERS; i++) {
            String username = "user" + random.nextInt(NUM_USERS);
            userMap.get(username);
        }
        long end = System.nanoTime();
        return TimeUnit.NANOSECONDS.toMillis(end - start);
    }

    private static long retrieveUsers(ArrayList<User> userList) {
        long start = System.nanoTime();
        for (int i = 0; i < NUM_USERS; i++) {
            String username = "user" + random.nextInt(NUM_USERS);
            for (User user : userList) {
                if (user.username.equals(username)) {
                    break;
                }
            }
        }
        long end = System.nanoTime();
        return TimeUnit.NANOSECONDS.toMillis(end - start);
    }

    private static long populateDrinks(Map<String, Drink> drinkMap) {
        long start = System.nanoTime();
        for (int i = 0; i < NUM_DRINKS; i++) {
            String drinkName = "Drink" + i;
            Drink drink = new Drink(drinkName, random.nextDouble() * 5);
            drinkMap.put(drinkName, drink);
        }
        long end = System.nanoTime();
        return TimeUnit.NANOSECONDS.toMillis(end - start);
    }

    private static long populateDrinks(ArrayList<Drink> drinkList) {
        long start = System.nanoTime();
        for (int i = 0; i < NUM_DRINKS; i++) {
            String drinkName = "Drink" + i;
            Drink drink = new Drink(drinkName, random.nextDouble() * 5);
            drinkList.add(drink);
        }
        long end = System.nanoTime();
        return TimeUnit.NANOSECONDS.toMillis(end - start);
    }

    private static long populateCartItems(Map<Integer, CartItem> cartMap, Map<String, Drink> drinkMap) {
        long start = System.nanoTime();
        for (int i = 0; i < NUM_CART_ITEMS; i++) {
            String drinkName = "Drink" + random.nextInt(NUM_DRINKS);
            Drink drink = drinkMap.get(drinkName);
            String topping = "None";
            double finalPrice = drink.price + (random.nextBoolean() ? 1.0 : 0.0);
            cartMap.put(i, new CartItem(drink, topping, finalPrice));
        }
        long end = System.nanoTime();
        return TimeUnit.NANOSECONDS.toMillis(end - start);
    }

    private static long populateCartItems(ArrayList<CartItem> cartList, ArrayList<Drink> drinkList) {
        long start = System.nanoTime();
        for (int i = 0; i < NUM_CART_ITEMS; i++) {
            Drink drink = drinkList.get(random.nextInt(NUM_DRINKS));
            String topping = "None";
            double finalPrice = drink.price + (random.nextBoolean() ? 1.0 : 0.0);
            cartList.add(new CartItem(drink, topping, finalPrice));
        }
        long end = System.nanoTime();
        return TimeUnit.NANOSECONDS.toMillis(end - start);
    }
}
