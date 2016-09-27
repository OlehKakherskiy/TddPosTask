import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Oleg on 26.09.2016.
 */
public class Order {

    private List<OrderRow> orderRows = new ArrayList<>();

    public int total() {
        return orderRows.stream().mapToInt(OrderRow::getSubtotal).sum();
    }

    public void add(Product p, int quantity) {
        orderRows.add(new OrderRow(p, quantity));
    }

    public boolean remove(int index) {
        if (index < 0 || index >= orderRows.size()) {
            return false;
        } else {
            orderRows.remove(index);
            return true;
        }
    }

    public List<OrderRow> getOrderRows() {
        return orderRows;
    }

    public String formatOrder() {
        List<String> formattedRows = orderRows.stream().map(OrderRow::formatRow).collect(Collectors.toList());
        int chars = formattedRows.stream().mapToInt(String::length).sum();
        StringBuilder builder = new StringBuilder(chars);
        int i = 0;
        for (String row : formattedRows) {
            builder.append(i++).append(". ").append(row).append("\n");
        }
        return builder.append("Total = ").append(total()).append(".").toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        return orderRows.equals(order.orderRows);

    }

    @Override
    public int hashCode() {
        return orderRows.hashCode();
    }

    public Payment processPayment(int balance) {
        int total = total();
        if (balance - total < 0) {
            throw new IllegalArgumentException("Small balance. Must be at least " + total);
        }
        return new Payment(this, total, balance - total);
    }
}
