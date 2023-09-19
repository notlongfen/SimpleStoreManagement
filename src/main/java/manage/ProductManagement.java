package manage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Scanner;
import entities.DailyProduct;
import entities.LongLifeProduct;
import entities.Order;
import entities.Product;

public class ProductManagement implements IProduct{
    private List<Product> productList = new ArrayList<Product>();
    // private List<Order> orderList = new ArrayList<Order>();
    // Scanner sc = new Scanner(System.in);    
    // String productFile = "./productFile.dat";
    // String warehouseFile = "./warehouseFile.dat";
    public ProductManagement() {
        productList = new ArrayList<Product>();
    }
    public void addProduct(Product a) {
        productList.add(a);
    }

    public void updateProduct(Product oldProduct, Product newProduct) {
        String code = newProduct.getCode();
        String type = newProduct.getType();
        String name = newProduct.getName();
        int quantity = newProduct.getQuantity();
        String oldCode = oldProduct.getCode();
        String oldType = oldProduct.getType();
        String oldName = oldProduct.getName();
        int oldQuantity = oldProduct.getQuantity();
        if (code.isBlank()) {
            newProduct.setCode(oldCode);
        }
        if (type.isBlank()) {
            newProduct.setType(oldType);
        }
        if (name.isBlank()) {
            newProduct.setName(oldName);
        }
        if (quantity == 0) { // quantity == -1
            newProduct.setQuantity(oldQuantity);
        }
        if(type.isBlank()){
            newProduct.setType(oldType);
        }
        if (newProduct instanceof DailyProduct) {
            DailyProduct newDailyProduct = (DailyProduct) newProduct;
            DailyProduct oldDailyProduct = (DailyProduct) oldProduct;
            String size = newDailyProduct.getSize();
            double unit = newDailyProduct.getUnit();
            String oldSize = oldDailyProduct.getSize();
            double oldUnit = oldDailyProduct.getUnit();
            if (size.isBlank()) {
                newDailyProduct.setSize(oldSize);
            }
            if (unit == 0) {
                newDailyProduct.setUnit(oldUnit);
            }
        } else if (newProduct instanceof LongLifeProduct) {
            LongLifeProduct newLongLifeProduct = (LongLifeProduct) newProduct;
            LongLifeProduct oldLongLifeProduct = (LongLifeProduct) oldProduct;
            String manufacturingDate = newLongLifeProduct.getManufacturingDate();
            String expirationDate = newLongLifeProduct.getExpirationDate();
            String oldManufacturingDate = oldLongLifeProduct.getManufacturingDate();
            String oldExpirationDate = oldLongLifeProduct.getExpirationDate();
            if (manufacturingDate.isBlank()) {
                newLongLifeProduct.setManufacturingDate(oldManufacturingDate);
            }
            if (expirationDate.isBlank()) {
                newLongLifeProduct.setExpirationDate(oldExpirationDate);
            }
        }

        oldProduct = newProduct;
    }

    public void removeProduct(Product product) {
        productList.remove(product);
    }

    public void displayAll(boolean option) {
        if (option) {
            try {
                displayByFile("product.dat");
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                // e.printStackTrace();
                Logger.getLogger(ProductManagement.class.getName()).log(Level.SEVERE, null, e);
            }
        } else {
            disPlayByCollection();
        }
    }
    public void display(List<Product> productList){
        for(Product a : productList){
            System.out.println(a);
        }
    }
    public void disPlayByCollection() {
        // for(Product a : productList){
        // if(a instanceof DailyProduct){
        // DailyProduct dailyProduct = (DailyProduct) a;
        // System.out.println(dailyProduct.toString());
        // }else if(a instanceof LongLifeProduct){
        // LongLifeProduct longLifeProduct = (LongLifeProduct) a;
        // System.out.println(longLifeProduct.toString());
        // }
        // }
        display(productList);
    }

    public void displayByFile(String pathFile) throws IOException, FileNotFoundException {
        List<Product> productListInFile = new ArrayList<Product>();
        File file = new File(pathFile);
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line = "";
        while ((line = br.readLine()) != null) {
            Product newProduct = null;
            String data[] = line.split(",");// [0];
            String code = data[0];
            String name = data[1];
            int quantity = Integer.parseInt(data[2]);
            String type = data[3];
            if (type.equals("DailyProduct")) {
                String size = data[4];
                double unit = Double.parseDouble(data[5]);
                newProduct = new DailyProduct(code, name, quantity, type, size, unit);
            } else if (type.equals("LongLifeProduct")) {
                String manufacturingDate = data[4];
                String expirationDate = data[5];
                newProduct = new LongLifeProduct(code, name, manufacturingDate, expirationDate, quantity, type);
            }
            productListInFile.add(newProduct);
        }
        display(productListInFile);
    }
    public List<Product> getListProduct(){
        return productList;
    }
    public Product getProductByCode(String code){
        for(Product product : productList){
            if(product.getCode().equals(code)){
                return product;
            }
        }
        return null;
    }

    // public void inputImport() {
    //     String iCode = sc.nextLine();
    //     String iOrderDate = sc.nextLine();
    //     String iType = "import";
    //     Order newOrder = new Order(iCode, iOrderDate, iType, 0);
    //     int continueInput = 1;
    //     while (continueInput != 0) {
    //         String pCode = sc.nextLine();
    //         int pQuantity = sc.nextInt();
    //         newOrder.addProduct(pCode, pQuantity);

    //         String pType;
    //         int option = sc.nextInt();
    //         sc.nextLine();
    //         System.out.println("Choose product type: 1: DailyProduct, 0: LongLifeProduct)");
    //         pType = option != 0 ? "DailyProduct" : "LongLifeProduct";

    //         String pOrderDate = sc.nextLine();
    //         Product newProduct = new Product(pCode, pOrderDate, pQuantity, pType);
    //         addProduct(newProduct);
    //         System.out.println("Do you want to continue input? (1: yes, 0: no)");
    //         continueInput = sc.nextInt();
    //         sc.nextLine();
    //     }
    // }

    // public void saveToFile() {
        
    // }
}
