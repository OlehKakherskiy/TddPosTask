import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Oleg on 26.09.2016.
 */
public class OrderRowTest {

    @Test
    public void getSubtotal() throws Exception {
        OrderRow orderRow = new OrderRow(new Product("name1", "desc1", 1), 2);
        Assert.assertEquals(2, orderRow.getSubtotal());
    }

    @Test
    public void getSubtotalNegativeOrZeroQuantity() throws Exception {
        OrderRow orderRow = new OrderRow(new Product("name1", "desc1", 1), 0);
        Assert.assertEquals(1, orderRow.getSubtotal());
    }

}