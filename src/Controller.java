import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by Oleh_Kakherskyi on 9/27/2016.
 */
public class Controller {

    private PoST poST;

    private Scanner scanner = new Scanner(System.in);

    private String possibleCommands =
            "1.: print products;\n" +
                    "2.: print order;\n" +
                    "3.: add product (`product_number` `quantity`);\n" +
                    "4.: remove product('item_number');\n" +
                    "5.: clear order and return balance;\n" +
                    "6.: pay money;\n" +
                    "7.: process payment and print it;\n" +
                    "q.: quit";

    public Controller(PoST poST) {
        this.poST = poST;
    }

    public void processUser() {
        String commandCode = "";

        while (true) {
            System.out.println();
            System.out.println(possibleCommands);
            commandCode = scanner.nextLine().trim();
            switch (commandCode) {
                case "1": {
                    printAvailableProducts();
                    break;
                }
                case "2": {
                    printOrder();
                    break;
                }
                case "3": {
                    processAddProduct();
                    break;
                }
                case "4": {
                    processRemoveProduct();
                    break;
                }
                case "5": {
                    processRefuseOrderAndReturnBalance();
                    break;
                }
                case "6": {
                    processPayMoney();
                    break;
                }
                case "7": {
                    processPayment();
                    break;
                }
                case "q": {
                    return;
                }
            }
        }
    }

    private void printAvailableProducts() {
        int i = 0;
        for (Product p : poST.getAvailableProducts()) {
            System.out.println(i++ + ". " + p.toString());
        }
    }

    private void printOrder() {
        System.out.println(poST.printCurrentOrderState());
    }

    private void processAddProduct() {
        boolean notOk = true;

        while (notOk) {
            try {
                System.out.println("Input product index");
                int index = Integer.parseInt(scanner.nextLine().trim());
                System.out.println("Input quantity (should be positive)");
                int quantity = Integer.parseInt(scanner.nextLine().trim());
                if (!poST.addItem(index, quantity)) {
                    System.out.println("Index should be in range of [0," + (poST.getAvailableProducts().size() - 1) + "]");

                } else {
                    notOk = false;
                }
            } catch (NumberFormatException e) {
                System.out.println("product index and quantity should be positive number");
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        }
    }

    private void processRemoveProduct() {
        boolean isOk = false;
        while (!isOk) {
            int orderPositionsCount = (poST.getOrder() == null) ? 0 : poST.getOrder().getOrderRows().size();
            if (orderPositionsCount == 0) {
                System.out.println("Nothing to remove");
                return;
            }
            System.out.println(poST.printCurrentOrderState());
            System.out.println("Choose order line to remove:");
            try {
                int index = Integer.parseInt(scanner.nextLine().trim());
                if (!poST.removeItemFromOrder(index)) {
                    throw new NumberFormatException();
                }
                isOk = true;
            } catch (NumberFormatException e) {
                System.out.println("line index should be positive number in range of [0," + (orderPositionsCount - 1) + "]");
            }
        }
    }

    private void processRefuseOrderAndReturnBalance() {
        System.out.println("Money to return: " + poST.refuseOrder());
    }

    private void processPayMoney() {
        String coin = "";
        while (!coin.equals("q")) {
            System.out.println(MessageFormat.format("Available coins to get payment: {0} or print ''q'' to exit payment procedure",
                    Arrays.toString(poST.getPossibleCoins())));
            coin = scanner.nextLine().trim();
            if (coin.equals("q")) {
                break;
            } else {
                try {
                    poST.payMoney(Integer.parseInt(coin));
                    System.out.println("balance = " + poST.getBalance());
                } catch (NumberFormatException e) {
                    System.out.println("You should input one of available coins " + Arrays.toString(poST.getPossibleCoins()) + " or 'q'");
                } catch (IllegalArgumentException e1) {
                    System.out.println(e1.getMessage());
                }
            }
        }
    }

    private void processPayment() {
        try {
            System.out.println(poST.processPaymentOperation().formatPayment());
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

}
