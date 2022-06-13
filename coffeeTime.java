import java.util.*;

public class CoffeeTime implements Problem<String> {
    private int ingredientsNum;
    private int budget;
    private Map<String, Integer> coffeeMenu;

    public static void main(String[] args) {
        new CoffeeTime(2, 3).enumerate();
    }

    public CoffeeTime (int ingredientsNum, int budget) {
        this.ingredientsNum = ingredientsNum;
        this.budget = budget;
        coffeeMenu = new HashMap<>();
        coffeeMenu.put("Chocolate", 2);
        coffeeMenu.put("Frothed milk", 1);
        coffeeMenu.put("Whisky", 2);
        coffeeMenu.put("Lemon Junice", 1);
        coffeeMenu.put("Caramel", 1);
        coffeeMenu.put("Whipped Cream", 2);
    }

    public boolean isSuccess(List<String> soFar) {
        return soFar.size() == ingredientsNum && calcuBudget(soFar) == budget;
    }

    public boolean isPartial(List<String> soFar) {
        return soFar.size() < ingredientsNum && calcuBudget(soFar) < budget;
    }

    public Iterable<String> options() {
        return coffeeMenu.keySet();
    }

    private int calcuBudget(List<String> soFar) {
        int sum = 0;
        for (String ingredient: soFar) {
            sum += coffeeMenu.get(ingredient);
        }
        return sum;
    }
}

// a recursive enumeration problem composed of the given element type
interface Problem<E> {

    // returns true if the given list is a valid, successful combination
    public boolean isSuccess(List<E> soFar);

    // returns true if the given list is a valid, partial combination
    public boolean isPartial(List<E> soFar);

    // returns all the options that can be explored
    public Iterable<E> options();

    // prints-out all the valid, successful combinations of options
    public default void enumerate() {
        enumerate(new ArrayList<E>());
    }

    private void enumerate(List<E> soFar) {
        if (isSuccess(soFar)) {
            System.out.println(soFar);
        } else if (isPartial(soFar)) {
            for (E option : options()) {
                soFar.add(option);
                enumerate(soFar);
                soFar.remove(soFar.size() - 1);
            }
        }
    }
}
