import java.util.Arrays;

/**
 * Created by Oleg on 27.09.2016.
 */
public class Main {

    public static void main(String[] args) {
        Product tea = new Product("tea", "green", 25);
        Product coffee = new Product("coffee", "black", 35);
        Product juice = new Product("juice", "juice, just juice", 10);
        PoST poST = new PoST(Arrays.asList(tea, coffee, juice), new int[]{1, 5, 10, 25, 50});
        new Controller(poST).processUser();
    }
}
