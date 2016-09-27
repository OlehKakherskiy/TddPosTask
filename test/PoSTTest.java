import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

/**
 * Created by Oleh_Kakherskyi on 9/27/2016.
 */
public class PoSTTest {

    private PoST defaultPost;

    @Before
    public void beforeTest() throws Exception {
        defaultPost = new PoST(Arrays.asList(new Product("name1", "desc1", 1)), new int[]{1, 5, 10, 25, 50});
    }

    @Test
    public void testGetAvailableProducts() throws Exception {
        PoST poST = new PoST(Arrays.asList(new Product("name1", "desc1", 1),
                new Product("name2", "desc2", 2), new Product("name3", "desc3", 3)), new int[]{1, 5, 10, 25, 50});
        Assert.assertArrayEquals(
                new Product[]{new Product("name1", "desc1", 1), new Product("name2", "desc2", 2), new Product("name3", "desc3", 3)},
                poST.getAvailableProducts().toArray()
        );
    }

    @Test
    public void testAddItem() throws Exception {
        Assert.assertTrue(defaultPost.addItem(0, 2));
        Order expected = new Order();
        expected.add(new Product("name1", "desc1", 1), 2);
        Assert.assertEquals(expected, defaultPost.getOrder());
    }

    @Test
    public void testAddItemWrongIndex() throws Exception {
        Assert.assertFalse(defaultPost.addItem(1, 2));
        Assert.assertEquals(new Order(), defaultPost.getOrder());
    }

    @Test
    public void testRemoveItem() throws Exception {
        defaultPost.addItem(0, 2);
        Assert.assertTrue(defaultPost.removeItemFromOrder(0));
        Assert.assertEquals(new Order(), defaultPost.getOrder());
    }

    @Test
    public void testRemoveItemWrongIndex() throws Exception {
        defaultPost.addItem(0, 2);
        Assert.assertTrue(defaultPost.removeItemFromOrder(0));
        Assert.assertEquals(new Order(), defaultPost.getOrder());
    }


    @Test
    public void getPossibleCoins() throws Exception {
        Assert.assertArrayEquals(new int[]{1, 5, 10, 25, 50}, defaultPost.getPossibleCoins());
    }


    @Test
    public void getBalanceWhenEmpty() throws Exception {
        Assert.assertEquals(0, defaultPost.getBalance());
    }

    @Test
    public void getNonEmptyBalance() throws Exception {
        defaultPost.payMoney(5);
        Assert.assertEquals(5, defaultPost.getBalance());
    }

    @Test
    public void testPayMoneyRightCoin() throws Exception {
        defaultPost.payMoney(10);
        Assert.assertEquals(10, defaultPost.getBalance());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPayMoneyWrongCoin() throws Exception {
        defaultPost.payMoney(2);
    }

    @Test
    public void refuseEmptyOrder() throws Exception {
        Assert.assertEquals(0, defaultPost.refuseOrder());
        Assert.assertNull(defaultPost.getOrder());
    }

    @Test
    public void refuseOrder() throws Exception {
        defaultPost.addItem(0, 5);
        defaultPost.payMoney(5);
        defaultPost.payMoney(50);
        Assert.assertEquals(55, defaultPost.refuseOrder());
        Assert.assertNull(defaultPost.getOrder());
    }
}