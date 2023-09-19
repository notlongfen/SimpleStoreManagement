package manage;
import entities.Product;

public interface IProduct {
    void addProduct(Product a);
    void updateProduct(Product oldProduct, Product newProduct);
    void removeProduct(Product product);
    void displayAll(boolean option);
}
