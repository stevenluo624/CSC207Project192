package data_access.helper;

import com.google.cloud.Timestamp;
import com.google.cloud.firestore.Blob;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.GeoPoint;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Map.entry;

public class FirestoreHelper implements IdTokenInterface, DBAccessInterface {
    private static final Logger log = LoggerFactory.getLogger(FirestoreHelper.class);
    private final String apiKey;
    private final String projectId;
    private JsonObject tokenJson;

    public FirestoreHelper(String apiKey, String projectId) {
        this.apiKey = apiKey;
        this.projectId = projectId;

        getToken();
    }

    @Override
    public void getToken() {
        String url = "https://identitytoolkit.googleapis.com/v1/accounts:signUp?key=" + apiKey;

        try {
            URL obj = new URL(url);
            HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");

            // Send empty body for anonymous sign-in
            String jsonInputString = "{\"returnSecureToken\":true}";

            con.setDoOutput(true);
            try (OutputStream os = con.getOutputStream()) {
                byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            // Get the response
            int responseCode = con.getResponseCode();
            log.info("Get token response Code: {}", responseCode);

            // Read the response
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            String jsonOutput = response.toString();
            this.tokenJson = new JsonParser().parse(jsonOutput).getAsJsonObject();
            log.info(this.tokenJson.toString());

            con.disconnect();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteToken() {
        String url = "https://identitytoolkit.googleapis.com/v1/accounts:delete?key=" + apiKey;

        try {
            URL obj = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            String jsonInputString = "{\"idToken\":\"" + this.getIdToken() + "\"}";

            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                log.info("Anonymous user account deleted successfully.");
            } else {
                log.info("Failed to delete account: {}", responseCode);
            }

            connection.disconnect();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getIdToken() {
        return this.tokenJson.get("idToken").getAsString();
    }

    @Override
    public void addDocument(String collection, Map<String, Object> fields, String documentValue) {
//        getToken();

        String url = "https://firestore.googleapis.com/v1/projects/" + this.projectId +
                "/databases/(default)/documents/" + collection + "?documentId=" + documentValue;

        try {
            URL obj = new URL(url);
            HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
            con.setRequestMethod("POST");
            con.setDoOutput(true);
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Authorization", "Bearer " + this.getIdToken());

            fields.replaceAll(
                    (k, v) -> dbEntry(getFirestoreValueType(v), v));

            JsonObject data = new JsonObject();
            Gson gson = new Gson();
            data.add("fields", gson.toJsonTree(fields));
            String jsonData = data.toString();
            try (OutputStream os = con.getOutputStream()) {
                byte[] input = jsonData.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                log.info("Data added successfully!");
            } else {
                log.info("Failed to add data: {}", responseCode);
            }

            con.disconnect();

//            deleteToken();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String addDocument(String collection, Map<String, Object> fields) {
//        getToken();

        String url = "https://firestore.googleapis.com/v1/projects/" + this.projectId +
                "/databases/(default)/documents/" + collection;

        try {
            URL obj = new URL(url);
            HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
            con.setRequestMethod("POST");
            con.setDoOutput(true);
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Authorization", "Bearer " + this.getIdToken());

            fields.replaceAll(
                    (k, v) -> dbEntry(getFirestoreValueType(v), v));

            JsonObject data = new JsonObject();
            Gson gson = new Gson();
            data.add("fields", gson.toJsonTree(fields));
            String jsonData = data.toString();
            try (OutputStream os = con.getOutputStream()) {
                byte[] input = jsonData.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            int responseCode = con.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                log.info("Data added successfully!");
                BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

                JsonObject responseJson = JsonParser.parseString(response.toString()).getAsJsonObject();
                String documentName = responseJson.get("name").getAsString();
                String documentId = documentName.substring(documentName.lastIndexOf("/") + 1);

                return documentId;
            } else {
                log.info("Failed to add data: {}", responseCode);
            }

            con.disconnect();

            return "";
//            deleteToken();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public JsonObject getDocument(String collection, String documentValue) {
//        getToken();

        String url = "https://firestore.googleapis.com/v1/projects/" + this.projectId +
                "/databases/(default)/documents/" + collection + "/" + documentValue;

        try {
            URL obj = new URL(url);
            HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Authorization", "Bearer " + this.getIdToken());
            int responseCode = con.getResponseCode();
            log.info("Get Response Code: {}", responseCode);

            // Read the response
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            StringBuffer response = new StringBuffer();
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            String jsonOutput = response.toString();

            con.disconnect();

//            deleteToken();
            return new JsonParser().parse(jsonOutput).getAsJsonObject().get("fields").getAsJsonObject();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public JsonObject getPage(String collection, int pageNumber, int pageSize) {
//        getToken();

        JsonObject result;
        String prevToken = null;
        for (int i = 0; i < pageNumber-1; i++) {
            prevToken = getPageToken(collection, prevToken, pageSize);
        }

        String url = "https://firestore.googleapis.com/v1/projects/" + this.projectId +
                "/databases/(default)/documents/" + collection + "?pageSize=" + pageSize;

        if (prevToken != null) {
            url += "&pageToken=" + prevToken;
        }

        try {
            URL obj = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) obj.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Authorization", "Bearer " + this.getIdToken());

            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String inputLine;
                StringBuilder content = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }

                in.close();
                conn.disconnect();

                // Parse JSON response
                result = JsonParser.parseString(content.toString()).getAsJsonObject();
            } else {
                log.info("GET request failed. Response Code: {}", responseCode);
                return null;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

//        deleteToken();
        return result;
    }

    @Override
    public void updateDocument(String collection, Map<String, Object> fields, String documentValue) {
//        getToken();

        String url = "https://firestore.googleapis.com/v1/projects/" + this.projectId +
                "/databases/(default)/documents/" + collection + "/" + documentValue;

        try {
            URL obj = new URL(url);
            HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("X-HTTP-Method-Override", "PATCH");
            con.setDoOutput(true);
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Authorization", "Bearer " + this.getIdToken());

            fields.replaceAll(
                    (k, v) -> dbEntry(getFirestoreValueType(v), v));

            JsonObject data = new JsonObject();
            Gson gson = new Gson();
            data.add("fields", gson.toJsonTree(fields));
            String jsonData = data.toString();
            try (OutputStream os = con.getOutputStream()) {
                byte[] input = jsonData.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                log.info("Data updated successfully!");
            } else {
                log.info("Failed to update data: {}", responseCode);
            }

            con.disconnect();

//            deleteToken();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean checkExists(String collection, String documentValue) {
//        getToken();

        String url = "https://firestore.googleapis.com/v1/projects/" + this.projectId +
                "/databases/(default)/documents/" + collection + "/" + documentValue;

        try {
            boolean result = false;

            URL obj = new URL(url);
            HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Authorization", "Bearer " + this.getIdToken());
            int responseCode = con.getResponseCode();
            log.info("Check Exist Response Code: {}", responseCode);

            if (responseCode == HttpURLConnection.HTTP_OK) {
                result = true;
            }

            con.disconnect();

//            deleteToken();
            return result;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean checkPageExists(String collection, int pageNumber, int pageSize) {
        if (pageNumber == 1) return true;
        if (pageNumber < 1) return false;
//        getToken();

        boolean result = true;
        String prevToken = null;
        for (int i = 0; i < pageNumber-1; i++) {
            prevToken = getPageToken(collection, prevToken, pageSize);
            if (prevToken == null) {
                break;
            }
        }
        if (prevToken == null) {
            result = false;
        }

//        deleteToken();
        return result;
    }

    @Override
    public void deleteDocument(String collection, String documentValue) {
        String url = "https://firestore.googleapis.com/v1/projects/" + this.projectId +
                "/databases/(default)/documents/" + collection + "/" + documentValue;

        try {
            URL obj = new URL(url);
            HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
            con.setRequestMethod("DELETE");
            con.setRequestProperty("Authorization", "Bearer " + this.getIdToken());
            int responseCode = con.getResponseCode();
            log.info("Response Code: {}", responseCode);

            con.disconnect();

//            deleteToken();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void incrementField(String collection, String documentValue, String fieldName, Double value) {
        String url = "https://firestore.googleapis.com/v1/projects/" + this.projectId +
                "/databases/(default)/documents:commit";
        String doc = "projects/" + this.projectId +
                "/databases/(default)/documents/" + collection + "/" + documentValue;
        log.info(url);

        try {
            URL obj = new URL(url);
            HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
            con.setRequestMethod("POST");
            con.setDoOutput(true);
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Authorization", "Bearer " + this.getIdToken());

            JsonObject data = new JsonObject();
            JsonObject writes = new JsonObject();
            JsonObject requestBody = new JsonObject();
            JsonObject transform = new JsonObject();
            JsonObject increment = new JsonObject();
            increment.addProperty("integerValue", value);
            transform.addProperty("fieldPath", fieldName);
            transform.add("increment", increment);

            requestBody.add("fieldTransforms", new Gson().toJsonTree(new JsonObject[]{transform}));
            requestBody.addProperty("document", doc);

            writes.add("transform", requestBody);

            data.add("writes", writes);

            String jsonData = data.toString();
            try (OutputStream os = con.getOutputStream()) {
                byte[] input = jsonData.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                log.info("Data updated successfully!");
            } else {
                log.info("Failed to increment data: {}", responseCode);
            }

            con.disconnect();

//            deleteToken();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private String getFirestoreValueType(Object value) {
        if (value == null) {
            return "nullValue";
        } else if (value instanceof Boolean) {
            return "booleanValue";
        } else if (value instanceof Long || value instanceof Integer) {
            return "doubleValue";
        } else if (value instanceof Double || value instanceof Float) {
            return "doubleValue";
        } else if (value instanceof String) {
            return "stringValue";
        } else if (value instanceof Timestamp) {
            return "timestampValue";
        } else if (value instanceof GeoPoint) {
            return "geoPointValue";
        } else if (value instanceof Blob) {
            return "bytesValue";
        } else if (value instanceof DocumentReference) {
            return "referenceValue";
        } else if (value instanceof List) {
            return "arrayValue";
        } else if (value instanceof Map) {
            return "mapValue";
        } else {
            return "unknownType";
        }
    }

    private Map<String, Object> dbEntry(String type, Object obj) {
        if (type.equals("arrayValue")) {
            List<Object> list = ((List<Object>)obj);
            for (int i = 0; i < list.size(); i++) {
                Object o = list.get(i);
                list.set(i, dbEntry(getFirestoreValueType(o), o));
            }
            return Map.ofEntries(entry(type, dbEntry("values",list)));
        }
        return Map.ofEntries(entry(type, obj));
    }

    private String getPageToken(String collection, String prevToken, int pageSize) {

        String url = "https://firestore.googleapis.com/v1/projects/" + this.projectId +
                "/databases/(default)/documents/" + collection + "?pageSize=" + pageSize;

        if (prevToken != null && !prevToken.isEmpty()) {
            url += "&pageToken=" + prevToken;
        }

        try {
            URL obj = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) obj.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Authorization", "Bearer " + this.getIdToken());

            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String inputLine;
                StringBuilder content = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }

                in.close();
                conn.disconnect();

                // Parse JSON response
                JsonObject jsonResponse = JsonParser.parseString(content.toString()).getAsJsonObject();

                // Check if there's a nextPageToken for more data
                if (jsonResponse.has("nextPageToken")) {
                    return jsonResponse.get("nextPageToken").getAsString();
                } else {
                    return null; // No more pages
                }
            } else {
                log.info("GET request failed. Response Code: {}", responseCode);
                return null;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}