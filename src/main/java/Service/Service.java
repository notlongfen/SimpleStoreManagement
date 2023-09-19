package Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;
import entities.DailyProduct;
import entities.LongLifeProduct;
import entities.Product;
import entities.WareHouse;
import manage.ProductManagement;
import manage.WareHouseManagement;
import report.Report;
import utils.FileManager;
import utils.Validation;

public class Service implements IService {
    ProductManagement productManagement = new ProductManagement();
    Validation valid = new Validation();
    List<Product> listProduct = productManagement.getListProduct();
    WareHouseManagement wareHouseManagement = new WareHouseManagement();
    Report report = new Report();
    FileManager fm = new FileManager();
    private static int lastGeneratedCode = 0;

    public Product inputProduct(Status status){
        String code = valid.checkProductCodeExist("Product Code: ", listProduct, status);
        String name = valid.checkString("Product name: ", status);
        int quantity = valid.checkInt("Product quantity: ", 0, Integer.MAX_VALUE, status);
        String type = valid.checkType("Product Type: ", status);
        Product newProduct = null;
        if(type.equals("DailyProduct")){
            double unit = valid.checkDouble("Product unit: ", 0, Double.MAX_VALUE, status);
            String size = valid.checkSize("Product size: ", status);
            newProduct = new DailyProduct(code, name, quantity, type, size, unit);
        }else if(type.equals("LongLifeProduct")){
            String manufacturingDate = valid.checkBeforeDate("Manufacturing Date: ", status);
            String expirationDate = valid.checkAfterDate("Expiration Date: ", manufacturingDate, status);
            newProduct = new LongLifeProduct(code, name, manufacturingDate, expirationDate, quantity, type);
        }
        fm.saveToFile("product.dat", newProduct);
        return newProduct;
    }
    @Override
    public void addProduct() {
        while(true){
            Product newProduct = inputProduct(Status.ADD);
            productManagement.addProduct(newProduct);
            if(valid.checkYesOrNo("Do you want to continue (Y/N)? ")){
                continue;
            }else{
                break;
            }
        }
    }

    @Override
    public void updateProduct() {
        String code = valid.checkString("Product Code for update: ", Status.UPDATE);
        Product oldProduct = productManagement.getProductByCode(code);
        if(oldProduct.equals(null)){
            System.out.println("Product code not exist!");
        }else{
            Product newProduct = inputProduct(Status.UPDATE);
            productManagement.updateProduct(oldProduct, newProduct);
        }
        System.out.println("Update success!");
        System.out.println("Old product data change to: " + oldProduct);
    }

    @Override
    public void removeProduct() {
        String code = valid.checkString("Product Code for remove: ", Status.REMOVE);
        boolean flag = true;
        Product product = productManagement.getProductByCode(code);
        if(product.equals(null)){
            System.out.println("Product not exist!");
            flag = false;
        }else if(wareHouseManagement.getProductInWareHouse(product) != null){
            System.out.println("Product still exist warehouse!");
            flag = false;
        }
        productManagement.removeProduct(product);
        if(flag){
            System.out.println("Remove success!");
        }else{
            System.out.println("Failed to delete product");
        }
        
    }
    public String selfIncrementCode(){
        lastGeneratedCode++;
        if(lastGeneratedCode > 999999){
            throw new RuntimeException("Code is out of range!"); //Limit exceed
            
        }
        return String.format("%07d", lastGeneratedCode);
    }

    @Override
    public void displayAll() {
        boolean option = valid.checkFileOrCollection("Do you want to display by file or collection (F/D)? ");
        productManagement.displayAll(option);
    }

    public WareHouse inputReceipt(boolean option){ //option : import or export where the code is unique and self increment which is self increment with an character represent for import or export following that are 6 self increment number
        String code = "";
        if(option){
            code = "I" + selfIncrementCode();
        }else{
            code = "E" + selfIncrementCode();
        }
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String time = dtf.format(now); 
        List<Product> productList = productManagement.getListProduct();
        while(true){
            String productCode = valid.checkString("Product Code: ", Status.ADD);
            Product product = productManagement.getProductByCode(productCode);
            if(product.equals(null)){
                System.out.println("Product not exist!");
                continue;
            }else if(listProduct.contains(product)){
                System.out.println("Product already exist!");
                continue;
            }else{
                productList.add(product);
                if(valid.checkYesOrNo("Do you want to continue to add product to the recipt (Y/N)? ")){
                    continue;
                }
                break;
            }
        }
        WareHouse importReceipt = new WareHouse(code, time, productList);
        return importReceipt;
        
    }
    
    @Override
    public void createImportReceipt() {
        WareHouse importReceipt = inputReceipt(valid.checkImportOrExport("Do you want to import or export (I/E)? "));
        wareHouseManagement.createImportReceipt(importReceipt);
       
    }

    @Override
    public void createExportReceipt() {
        WareHouse importReceipt = inputReceipt(valid.checkImportOrExport("Do you want to import or export (I/E)? "));
        wareHouseManagement.createImportReceipt(importReceipt);
    }

    @Override
    public void displayProductExpired() {
        List<Product> listProductExpired = report.displayProductExpired(listProduct);
        //  if(listProductExpired.isEmpty()){
        //       System.out.println("No product expired!");
        //  }else{
        //         System.out.println("List product expired: ");
        //         for(Product product : listProductExpired){
        //             System.out.println(product);
        //         }
        //  }
        productManagement.display(listProductExpired);
    }

    @Override
    public void displayProductSelling() {
       List<Product> listProductSelling = report.displayProductSelling(listProduct);
       productManagement.display(listProductSelling);
    }

    @Override
    public void displayProductRunningOut() {
        List<Product> listProductRunningOut = report.displayProductRunningOut(listProduct);
        productManagement.display(listProductRunningOut);
    }

    @Override
    public void displayReceiptProduct() {
        String code = valid.checkString("Enter product code: ", Status.NONE);
        Product product = report.displayReceiptProduct(code, productManagement, wareHouseManagement);
        System.out.println(product);
    }
    
}
