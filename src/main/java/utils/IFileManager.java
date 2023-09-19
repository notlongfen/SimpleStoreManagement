package utils;

import java.util.List;
import entities.Product;

public interface IFileManager {
    void saveToFile(String path, Product data);

    List<String> loadData(String path);

}
