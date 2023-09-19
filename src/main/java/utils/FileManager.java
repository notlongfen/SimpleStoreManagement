package utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import entities.Product;
public class FileManager implements IFileManager{
    @Override
    public void saveToFile(String path, Product data) {
        try {
            FileOutputStream fos = new FileOutputStream(path);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(data);
            oos.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<String> loadData(String path) { //to program(deseralize)
        List<String> data = new ArrayList<>();
        try {
            FileInputStream fis = new FileInputStream(path);
            ObjectInputStream ois = new ObjectInputStream(fis);
            data = (List<String>) ois.readObject();
            ois.close();
            fis.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return data;
    }

    
    
}
