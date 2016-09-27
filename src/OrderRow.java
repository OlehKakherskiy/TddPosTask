import java.text.MessageFormat;

/**
 * Created by Oleg on 26.09.2016.
 */
public class OrderRow {

    private Product product;

    private int quantity;

    public OrderRow(Product product, int quantity) {
        this.product = product;
        this.quantity = (quantity > 0) ? quantity : 1;
    }

    public int getSubtotal() {
        return product.getPrice() * quantity;
    }

    public String formatRow() {
        return MessageFormat.format("{0}: {1}, price = {2}, count = {3}",
                product.getName(), product.getDescription(), product.getPrice(), quantity);
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderRow orderRow = (OrderRow) o;

        if (quantity != orderRow.quantity) return false;
        return product.equals(orderRow.product);

    }

    @Override
    public int hashCode() {
        int result = product.hashCode();
        result = 31 * result + quantity;
        return result;
    }
}
