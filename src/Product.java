/**
 * Created by Oleg on 26.09.2016.
 */
public class Product implements Cloneable {

    private int price;

    private String name;

    private String description;

    public Product(String name, String description, int price) {
        this.price = price;
        this.name = name;
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (price != product.price) return false;
        if (!name.equals(product.name)) return false;
        return description.equals(product.description);

    }

    @Override
    public int hashCode() {
        int result = price;
        result = 31 * result + name.hashCode();
        result = 31 * result + description.hashCode();
        return result;
    }

    @Override
    protected Product clone() throws CloneNotSupportedException {
        return new Product(name, description, price);
    }

    @Override
    public String toString() {
        return "Product{" +
                "price=" + price +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
