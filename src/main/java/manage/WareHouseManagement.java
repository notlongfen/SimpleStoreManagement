package manage;
import java.util.ArrayList;
import java.util.List;

import entities.Product;
import entities.WareHouse;

public class WareHouseManagement implements IWareHouse{
    public List<WareHouse> wareHouseListImport ;
    public List<WareHouse> wareHouseListExport ;

    public WareHouseManagement(){
        wareHouseListImport = new ArrayList<WareHouse>();
        wareHouseListExport = new ArrayList<WareHouse>();
    }

    @Override
    public void createImportReceipt(WareHouse newRecipt) {
        wareHouseListImport.add(newRecipt);
    }

    @Override
    public void createExportReceipt(WareHouse newRecipt) {
      wareHouseListExport.add(newRecipt);
    }

    public Product getProductInWareHouse(Product product){
        for(WareHouse wareHouse : wareHouseListImport){
            for(Product p : wareHouse.getProductList()){
                if(p.getCode().equals(product.getCode())){
                    return p;
                }
            }
        }
        return null;
    }
    
}
