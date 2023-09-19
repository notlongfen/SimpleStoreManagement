package report;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import entities.DailyProduct;
import entities.LongLifeProduct;
import entities.Product;
import entities.WareHouse;
import manage.ProductManagement;
import manage.WareHouseManagement;

public class Report implements IReport {

    @Override
    public List<Product> displayProductExpired(List<Product> listProduct) {
        String dateFormat = "dd/MM/yyyy";
        DateFormat sdf = new SimpleDateFormat(dateFormat);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(dateFormat);
        LocalDate now = LocalDate.now();
        String time = dtf.format(now);
        List<Product> listProductExpired = new ArrayList<Product>();
        try{
            Date nowTime = sdf.parse(time);
            for(Product product : listProduct){
                LongLifeProduct longLifeProduct = (LongLifeProduct) product;
                Date expiredTime = sdf.parse(longLifeProduct.getExpirationDate());
                if(nowTime.compareTo(expiredTime) > 0){
                    listProductExpired.add(product);
                    System.out.println(product);

                }
            }
        }catch(ParseException e){
            Logger.getLogger(Report.class.getName()).log(Level.SEVERE, null, e);
        }
        return listProductExpired;
    }

    @Override
    public List<Product> displayProductSelling(List<Product> listProduct) {
    //     List<Product> listProductSelling = new ArrayList<Product>();
    //     for(Product product : listProduct){
    //         if(product instanceof DailyProduct && product.getQuantity() > 0){
    //             listProductSelling.add(product);
    //             System.out.println(product.toString());
    //         }else if(product instanceof LongLifeProduct && product.getQuantity() > 0 && !containsProduct(displayProductExpired(listProduct), product)){
    //             listProductSelling.add(product);
    //             System.out.println(product.toString());
    //         }
    //    }
    //    return listProductSelling;

       String dateFormat = "dd/MM/yyyy";
        DateFormat sdf = new SimpleDateFormat(dateFormat);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(dateFormat);
        LocalDate now = LocalDate.now();
        String time = dtf.format(now);
        List<Product> listProductNotExpired = new ArrayList<Product>();
        try{
            Date nowTime = sdf.parse(time);
            for(Product product : listProduct){
                LongLifeProduct longLifeProduct = (LongLifeProduct) product;
                Date expiredTime = sdf.parse(longLifeProduct.getExpirationDate());
                if(nowTime.compareTo(expiredTime) <= 0 && product.getQuantity() > 0){
                    listProductNotExpired.add(product);
                    System.out.println(product);

                }
            }
        }catch(ParseException e){
            Logger.getLogger(Report.class.getName()).log(Level.SEVERE, null, e);
        }
        return listProductNotExpired;
    }

    private boolean containsProduct(List<Product> listProduct, Product targetProduct) {
        for (Product product : listProduct) {
            if (product.equals(targetProduct)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Product> displayProductRunningOut(List<Product> listProduct) { //should less than or equal 3 and sort ascending by quantity
        List<Product> listProductRunningOut = new ArrayList<Product>();
        for(Product product : listProduct){
            if(product.getQuantity() <= 3){
                listProductRunningOut.add(product);
                System.out.println(product.toString());

            }
        }
        Comparator <Product> compareByQuantity = (Product p1, Product p2) -> p1.getQuantity() - p2.getQuantity();
        Collections.sort(listProductRunningOut, compareByQuantity);
        return listProductRunningOut;
    }

    @Override
    public Product displayReceiptProduct(String code, ProductManagement productManagement, WareHouseManagement wareHouseManagement) {
        Product product = productManagement.getProductByCode(code);
        return wareHouseManagement.getProductInWareHouse(product);
    }
    
}
