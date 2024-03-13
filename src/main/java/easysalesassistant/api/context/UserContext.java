package easysalesassistant.api.context;

public class UserContext {
    private static final ThreadLocal<String> CURRENT_USER = new ThreadLocal<>();

    public static String getCurrentUser() {
        return CURRENT_USER.get();
    }

    public static void setCurrentUser(String tenant) {
        CURRENT_USER.set(tenant);
    }
}
