package entities;

public class DailyProduct extends Product {
    private String size;
    private double unit;
    
    public DailyProduct(){
        super("", "",0, "");
    }
    public DailyProduct(String code, String name, int quantity, String type, String size, double unit){
        super(code, name, quantity, type);
        this.size = size;
        this.unit = unit;
    }

    public String getSize(){
        return size;
    }

    public void setSize(String size){
        this.size = size;
    }

    public double getUnit(){
        return unit;
    }

    public void setUnit(double unit){
        this.unit = unit;
    }

    @Override
    public String toString(){
        return super.toString() + " " + size + " " + unit;
    }

}
