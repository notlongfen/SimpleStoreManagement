package entities;
import java.util.ArrayList;
import java.util.List;

public class WareHouse {
    private String code;
    private String time;
    private List<Product> productList = new ArrayList<Product>();

    public WareHouse(String code, String time, List<Product> productList){
        this.code = code;
        this.time = time;
        this.productList = productList;
    }

    public String getCode(){
        return code;
    }
    
    public String getTime(){
        return time;
    }

    public List<Product> getProductList(){
        return productList;
    }

    public void setCode(String code){
        this.code = code;
    }

    public void setTime(String time){
        this.time = time;
    }

    public void setProductList(List<Product> productList){
        this.productList = productList;
    }

    @Override
    public String toString(){
        String result = code + " " + time + "\n";
        for (Product product : productList) {
            String pCode = product.getCode();
            result += (", " + pCode);
        }
        return result;
    }

}
