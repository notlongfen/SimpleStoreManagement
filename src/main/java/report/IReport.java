package report;

import java.util.List;
import entities.Product;
import entities.WareHouse;
import manage.ProductManagement;
import manage.WareHouseManagement;
public interface IReport {
    List<Product> displayProductExpired(List<Product> listProduct);
    List<Product> displayProductSelling(List<Product> listProduct);
    List<Product> displayProductRunningOut(List<Product> listProduct);
    Product displayReceiptProduct(String code, ProductManagement productManagement, WareHouseManagement wareHouseManagement);
}
