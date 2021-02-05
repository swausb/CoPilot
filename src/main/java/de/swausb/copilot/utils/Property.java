package de.swausb.copilot.utils;

import java.io.*;
import java.util.Properties;

public class Property {

    /**
     * @param file name of the file
     * @param key  the key to look for in the config
     * @return value of the given key
     */
    public String get(String file, String key) {
        try (InputStream input = new FileInputStream("CoPilot/" + file + ".properties")) {

            Properties prop = new Properties();

            // load a properties file from InputStream
            prop.load(input);

            return prop.getProperty(key);

            // Java 8 , print key and values
//            prop.forEach((key, value) -> System.out.println("Key : " + key + ", Value : " + value));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * create the default properties-file
     */
    public void setDefaultProps() {
        //create the file if not exists
        File dir = new File("CoPilot");
        if (!dir.exists()) {
            dir.mkdirs();
            try (OutputStream output = new FileOutputStream("CoPilot/cfg.properties")) {

                Properties prop = new Properties();

                // set the properties value
                prop.setProperty("token", "paste token here");

                // save properties to project folder
                prop.store(output, null);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}