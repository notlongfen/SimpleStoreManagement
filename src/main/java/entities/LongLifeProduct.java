package entities;

public class LongLifeProduct extends Product {
    private String manufacturingDate;
    private String exprirationDate;
    

    public LongLifeProduct(){
        super("", "",0, "");
    }
    public LongLifeProduct(String code, String name,String manufacturingDate, String expirationDate, int quantity, String type){
        super(code, name, quantity, type);
        this.manufacturingDate = manufacturingDate;
        this.exprirationDate = expirationDate;
    }

    public String getManufacturingDate(){
        return manufacturingDate;
    }

    public void setManufacturingDate(String manufacturingDate){
        this.manufacturingDate = manufacturingDate;
    }

    public String getExpirationDate(){
        return exprirationDate;
    }

    public void setExpirationDate(String expirationDate){
        this.exprirationDate = expirationDate;
    }

    @Override
    public String toString(){
        return super.toString() + " " + manufacturingDate + " " + exprirationDate;
    }

}
