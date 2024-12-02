package data_access.helper;

import com.google.gson.JsonObject;

import java.util.Map;

public interface DBAccessInterface {
    void addDocument(String collection, Map<String, Object> data, String documentValue);
    String addDocument(String collection, Map<String, Object> data);
    JsonObject getDocument(String collection, String documentValue);
    JsonObject getPage(String collection, int pageNumber, int pageSize);
    void updateDocument(String collection, Map<String, Object> data, String documentValue);
    boolean checkExists(String collection, String documentValue);
    boolean checkPageExists(String collection, int pageNumber, int pageSize);
    void deleteDocument(String collection, String documentValue);
    void incrementField(String collection, String documentValue, String fieldName, int value);
}