import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Oleg on 26.09.2016.
 */
public class OrderTest {

    private Order order;

    @Before
    public void beforeTest() {
        order = new Order();
    }

    @Test
    public void testTotalOfEmpty() {
        Assert.assertEquals(0, order.total());
    }

    @Test
    public void testTotal() {
        order.add(new Product("name1", "desc1", 1), 2);
        order.add(new Product("name2", "desc3", 10), 4);
        Assert.assertEquals(42, order.total());
    }

    @Test
    public void testAdd() throws Exception {
        order.add(new Product("name1", "desc1", 1), 2);
        Assert.assertArrayEquals(
                new OrderRow[]{new OrderRow(new Product("name1", "desc1", 1), 2)},
                order.getOrderRows().toArray()
        );
    }

    @Test
    public void removeNegativeIndex() throws Exception {
        order.add(new Product("name1", "desc1", 1), 2);
        order.add(new Product("name2", "desc3", 10), 4);


        Assert.assertFalse(order.remove(-1));
        Assert.assertArrayEquals(new OrderRow[]{
                        new OrderRow(new Product("name1", "desc1", 1), 2),
                        new OrderRow(new Product("name2", "desc3", 10), 4)},
                order.getOrderRows().toArray());
    }


    @Test
    public void removePositiveWrongIndex() throws Exception {
        order.add(new Product("name1", "desc1", 1), 2);
        order.add(new Product("name2", "desc3", 10), 4);


        Assert.assertFalse(order.remove(2));
        Assert.assertArrayEquals(new OrderRow[]{
                        new OrderRow(new Product("name1", "desc1", 1), 2),
                        new OrderRow(new Product("name2", "desc3", 10), 4)},
                order.getOrderRows().toArray());
    }

    @Test
    public void testProcessEmptyPayment() throws Exception {
        Assert.assertEquals(new Payment(new Order(), 0, 0), new Order().processPayment(0));
    }

    @Test
    public void testProcessPayment() throws Exception {
        Order targetOrder = new Order();
        targetOrder.add(new Product("name1", "desc1", 10), 1);
        targetOrder.add(new Product("name2", "desc2", 20), 2);
        targetOrder.add(new Product("name3", "desc3", 30), 3);

        Assert.assertEquals(new Payment(targetOrder, 140, 5), targetOrder.processPayment(145));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testProcessPaymentSmallBalance() throws Exception {
        Order targetOrder = new Order();
        targetOrder.add(new Product("name1", "desc1", 10), 1);
        targetOrder.add(new Product("name2", "desc2", 20), 2);
        targetOrder.add(new Product("name3", "desc3", 30), 3);
        targetOrder.processPayment(135);
    }
}