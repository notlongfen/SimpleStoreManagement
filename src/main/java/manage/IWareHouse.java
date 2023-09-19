package manage;

import entities.WareHouse;

public interface IWareHouse {
    void createImportReceipt(WareHouse newRecipt);
    void createExportReceipt(WareHouse newRecipt);

}
