package com.lt.utils.utils.tools;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

public class ReadFileUtil {
    private static final String DEFAULT_ENCODING = "UTF-8";

    public ReadFileUtil() {
    }

    public static String readFile(String proper) {
        try {
            InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("url.properties");
            Properties prop = new Properties();
            prop.load(new InputStreamReader(inputStream, "UTF-8"));
            String driverClassName = prop.getProperty(proper);
            return driverClassName;
        } catch (FileNotFoundException var4) {
            System.out.println("properties文件路径有误！");
            var4.printStackTrace();
        } catch (IOException var5) {
            var5.printStackTrace();
        }

        return proper;
    }
}

