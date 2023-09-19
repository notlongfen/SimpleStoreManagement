package entities;
import java.io.Serializable;
public abstract class Product implements Serializable{
    private String code;
    private String name;
    private int quantity;
    private String type;

    public Product(String code, String name, int quantity, String type){
        this.code = code;
        this.name = name;
        this.quantity = quantity;
        this.type = type;
    }

    public String getCode(){
        return code;
    }

    public String getName(){
        return name;
    }

    public void setCode(String code){
        this.code = code;
    }

    public void setName(String name){
        this.name = name;
    }

    public int getQuantity(){
        return quantity;
    }

    public void setQuantity(int quantity){
        this.quantity = quantity;
    }

    public String getType(){
        return type;
    }

    public void setType(String type){
        this.type = type;
    }

    @Override
    public String toString(){
        return code + " " + name + " " + quantity + " " + type;
    }
    
}
