package entities;

import java.util.HashMap;

public class Order {
   private String code;
   // private String name;
   private int quantity;
   private String orderDate;
   private String type; //import or export
   private HashMap<String, Integer> productMap = new HashMap<String, Integer>();

   // public Order(String code, String name,String orderDate, String type, int quantity){
   //      this.code = code;
   //      this.name = name;
   //      this.orderDate = orderDate;
   //      this.
   // }
   public Order(String code, String orderDate, String type, int quantity) {
      this.code = code;
      this.orderDate = orderDate;
      this.type = type;
      this.quantity = 0;
   }

   public String getCode() {
      return code;
   }

   // public String getName() {
   //    return name;
   // }

   public String getOrderDate() {
      return orderDate;
   }

   public String getType() {
      return type;
   }

   public int getQuantity() {
      return quantity;
   }

   public void setCode(String code) {
      this.code = code;
   }

   // public void setName(String name) {
   //    this.name = name;
   // }

   public void setOrderDate(String orderDate) {
      this.orderDate = orderDate;
   }

   public void setType(String type) {
      this.type = type;
   }

   public void setQuantity(int quantity) {
      this.quantity = quantity;
   }

   // public void addOrderList(String code, int quantity) {
   //    orderList.put(code, quantity);
   // }

   public HashMap<String, Integer> getOrderList() {
      return productMap;
   }

   public void setOrderList(HashMap<String, Integer> orderList) {
      this.productMap = productMap;
   }

   @Override
   public String toString() {
      return "Order [code=" + code + ", orderDate=" + orderDate + ", type=" + type + ", quantity="
            + quantity + "]";
   }

   public void addProduct(String code, int _quantity) {
      int currentQuantity = productMap.get(code);
      productMap.put(code, _quantity + currentQuantity);
      quantity += _quantity;
   }
}
