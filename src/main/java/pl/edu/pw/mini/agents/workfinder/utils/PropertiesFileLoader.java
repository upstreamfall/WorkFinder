package pl.edu.pw.mini.agents.workfinder.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Pawel on 2015-04-23.
 */
public class PropertiesFileLoader {
    private static String resourcesDir = "src/main/resources/";

    public static Properties loadSPropertyFile(String folder, String fileName) {
        Properties prop = new Properties();
        InputStream input = null;
        try {
            StringBuilder str = new StringBuilder(resourcesDir);
            str.append("/").append(folder).append("/").append(fileName).append(".properties");
            input = new FileInputStream(str.toString());

            prop.load(input);
            return prop;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
