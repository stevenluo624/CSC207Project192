package helper;

@FunctionalInterface
public interface Callback {
    void onComplete(boolean success);
}
