import java.util.Arrays;
import java.util.List;

/**
 * Created by Oleh_Kakherskyi on 9/27/2016.
 */
public class PoST {

    private List<Product> products;

    private Order order;

    private int[] possibleCoins;

    private int balance;

    public PoST(List<Product> products, int[] possibleCoins) {
        this.products = products;
        this.possibleCoins = possibleCoins;
    }


    public boolean addItem(int index, int quantity) throws CloneNotSupportedException {
        order = (order != null) ? order : new Order();
        if (isWrongIndex(index)) {
            return false;
        } else {
            order.add(products.get(index).clone(), quantity);
            return true;
        }
    }

    public String printCurrentOrderState() {
        return (order == null) ? "No order" : order.formatOrder();
    }

    public List<Product> getAvailableProducts() {
        return products;
    }

    public Order getOrder() {
        return order;
    }

    public boolean removeItemFromOrder(int index) {
        return order.remove(index);
    }

    private boolean isWrongIndex(int index) {
        return index < 0 || index >= products.size();
    }

    public int[] getPossibleCoins() {
        return possibleCoins;
    }

    public int getBalance() {
        return balance;
    }

    public void payMoney(int currentCoin) {
        for (int coin : possibleCoins) {
            if (coin == currentCoin) {
                balance += currentCoin;
                return;
            }
        }
        throw new IllegalArgumentException("Wrong coin: should be from the list: " + Arrays.toString(possibleCoins));
    }

    public int refuseOrder() {
        if (order != null) {
            order.getOrderRows().clear();
            order = null;
        }
        return balance;
    }

    public Payment processPaymentOperation() {
        if (order == null) {
            throw new RuntimeException("There's no order to process payment for");
        }
        Payment p = order.processPayment(balance);
        order = null;
        return p;
    }
}
