package dev.marius.decimal;

import java.util.ArrayList;
import java.util.Map;

public final class JavaUtility {

    public static String reverse(final String string) {
        // Make a StringBuilder for output string later
        final StringBuilder result = new StringBuilder();
        // get all chars of the string
        final char[] arr = string.toCharArray();
        // Make a for loop that start on length on given char array and go down
        for (int i = arr.length; i > 0; i--) {
            // add char to StringBuilder
            result.append(arr[i - 1]);
        }
        // return the result
        return result.toString();
    }

    public static ArrayList<String> replace(final ArrayList<String> stringList, final Map<Integer, Character> map) {
        // Make List for result
        final ArrayList<String> result = new ArrayList<>();
        // Iterate through the list of strings
        for (String s : stringList) {
            // Check if string is in given map
            if (map.containsKey(Integer.valueOf(s))) {
                // Add value of map to result
                result.add(String.valueOf(map.get(Integer.valueOf(s))));
            } else {
                // Add string to result
                result.add(s);
            }
        }
        // return the result
        return result;
    }

    public static String toString(final ArrayList<String> stringList) {
        // Make a StringBuilder for return
        StringBuilder res = new StringBuilder();
        // Iterate through all strings and append them to the StringBuilder
        for (String s : stringList) res.append(s);
        // Return the result
        return res.toString();
    }

}
