package user_session;

public class UserSession {
    private static UserSession instance;
    private int id_user;
    private String full_name;

    public UserSession(int id_user, String full_name) {
        this.id_user = id_user;
        this.full_name = full_name;
    }

    public static void startSession(int id_user, String full_name) {
        instance = new UserSession(id_user, full_name);
    }

    public static UserSession getInstance() {
        return instance;
    }

    public static void setInstance(UserSession instance) {
        UserSession.instance = instance;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public static void cleanSession() {
        instance = null;
    }
}
