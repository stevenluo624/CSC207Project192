package helper;

public class GlobalHelper {
    private static final FirestoreHelper helper = new FirestoreHelper(
            ProjectConstants.API_KEY,
            ProjectConstants.PROJECT_ID);

    public static FirestoreHelper getHelper() {
        return helper;
    }
}
