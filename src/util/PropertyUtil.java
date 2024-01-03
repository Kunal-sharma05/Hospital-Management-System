package util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyUtil {

    private static final String property_file = "util/db.properties";

    private PropertyUtil() {
        // Private constructor to prevent instantiation
    }

    public static String getPropertyString() {
        Properties properties = new Properties();

        try (InputStream input = PropertyUtil.class.getClassLoader().getResourceAsStream(property_file)) {
            if (input == null) {
                System.out.println("Sorry, unable to find " + property_file);
                return null;
            }

            // Load a properties file from class path
            properties.load(input);

            // Construct the connection string
            String url = properties.getProperty("db.url");
            String username = properties.getProperty("db.username");
            String password = properties.getProperty("db.password");
         

            return (url +  "?user=" + username + "&password=" + password);
        } catch (IOException e) {
            System.out.println("Not found"); // Handle the exception according to your needs
            return null;
        }
    }
}
