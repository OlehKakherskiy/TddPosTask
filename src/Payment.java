import java.text.MessageFormat;

/**
 * Created by Oleh_Kakherskyi on 9/27/2016.
 */
public class Payment {

    private Order order;

    private int total;

    private int remaining;


    public Payment(Order order, int total, int remaining) {
        this.order = order;
        this.total = total;
        this.remaining = remaining;
    }

    public String formatPayment() {
        return MessageFormat.format("{0}\nTotal: {1};\nRemaining: {2}", order.formatOrder(), total, remaining);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Payment payment = (Payment) o;

        if (total != payment.total) return false;
        if (remaining != payment.remaining) return false;
        return order.equals(payment.order);

    }

    @Override
    public int hashCode() {
        int result = order.hashCode();
        result = 31 * result + total;
        result = 31 * result + remaining;
        return result;
    }
}
