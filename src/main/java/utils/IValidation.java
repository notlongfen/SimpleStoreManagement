package utils;

import java.util.List;

import Service.Status;
import entities.Product;
import entities.WareHouse;

public interface IValidation {
      String checkString(String msg,Status status);
  
    String checkProductCodeExist(String msg, List<Product> listProduct,Status status);

    String checkReceiptCodeExist(String msg, List<WareHouse> listWareHouse);

    String checkBeforeDate(String msg,Status status);

    String checkAfterDate(String msg, String pd,Status status);

    String checkType(String msg, Status status);
    
    String checkSize(String msg, Status status);
    
    int checkInt(String msg, int min, int max, Status status);

    double checkDouble(String msg, double min, double max,Status status);

    boolean checkYesOrNo(String msg);

    boolean checkUpdateOrDelete(String msg);

    boolean checkFileOrCollection(String msg);
}
