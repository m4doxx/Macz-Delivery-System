
package macz.delivery;

public class UserSession {

    private static int userId = -1;
    private static String username;
    private static String fullName;
    private static String role;

    public static void login(
            int id, String user, String name, String userRole) {

        userId = id;
        username = user;
        fullName = name;
        role = userRole;
    }

    public static int getUserId() {
        return userId;
    }

    public static String getUsername() {
        return username;
    }

    public static String getFullName() {
        return fullName;
    }

    public static String getRole() {
        return role;
    }

    public static boolean isLoggedIn() {
        return userId > 0;
    }

    public static void logout() {
        userId = -1;
        username = null;
        fullName = null;
        role = null;
    }
}
