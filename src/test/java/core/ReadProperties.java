package core;

import java.io.IOException;
import java.util.Properties;

public class ReadProperties {
    private static Properties properties = null;
    private static String filename = "config.properties";

    static {
        properties = new Properties();
        try {
            properties.load(ReadProperties.class.getClassLoader().getResourceAsStream(filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getUrl() {return properties.getProperty("url");}

    public static String getApiUrl() {return properties.getProperty("url_api");}

    public static String getBrowserName() {
        return properties.getProperty("browser");
    }

    public static String getUsername() {
        return properties.getProperty("username");
    }

    public static String getPassword() {
        return properties.getProperty("password");
    }

    public static String getDbUrl() {
        return properties.getProperty("db_url");
    }

    public static String getDbUsername() {
        return properties.getProperty("db_username");
    }

    public static String getDbPassword() {
        return properties.getProperty("db_password");
    }

    public static String getToken() {
        return properties.getProperty("token");
    }

    public static boolean isHeadless() {
        return properties.getProperty("headless").equalsIgnoreCase("true");
    }
}
