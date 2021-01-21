package dev.marius.decimal;

import dev.marius.Start;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public final class Language {

    private static final Map<String, String> messages = new HashMap<>();

    static {
        try {
            // Get user language of the current computer
            String lang_key = System.getProperty("user.language");
            // Check if language is supporte, if not set language to english
            if (!lang_key.equalsIgnoreCase("de") && !lang_key.equalsIgnoreCase("en"))
                lang_key = "en";
            // Get language properties file from jar
            InputStream stream = Start.class.getResourceAsStream(lang_key + ".properties");
            // Read the stream
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
            // Put the content of the resource into Properties
            Properties properties = new Properties();
            properties.load(reader);
            // Get all entries of properties
            for (Map.Entry<Object, Object> entry : properties.entrySet()) {
                Object key = entry.getKey();
                Object value = entry.getValue();
                // Put key and value of current property to messages map
                messages.put(key.toString(), value.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String get(String key) {
        return messages.getOrDefault(key, "");
    }

    public static String get(String key, Object... args) {
        return String.format(messages.getOrDefault(key, ""), args);
    }

}
