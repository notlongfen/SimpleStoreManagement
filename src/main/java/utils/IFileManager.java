package utils;

import java.util.List;

public interface IFileManager {
    void saveToFile(String path, List data);

    List<String> loadData(String path);

}
