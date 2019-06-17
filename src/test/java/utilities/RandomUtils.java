package utilities;

import org.apache.commons.text.RandomStringGenerator;

import java.util.*;

public class RandomUtils {
    private static final String ALPHABETIC_UPPER_CASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String ALPHABETIC_LOWER_CASE = "abcdefghijklmnopqrstuvwxyz";
    private static final String NUMERIC = "0123456789";

    public static <T> T getRandomElementInList(List<T> list) {
        return list.get(new Random().nextInt(list.size()));
    }

    public static <K, V> Map.Entry<K, V> getRandomValueFromMap(Iterator<Map.Entry<K, V>> iterator, int count) {
        int index = (int) (Math.random() * count);

        while (index > 0 && iterator.hasNext()) {
            iterator.next();
            index--;
        }

        return iterator.next();
    }

    public static <K, V> Map.Entry<K, V> getRandomValueFromMap(Set<Map.Entry<K, V>> entries) {
        return getRandomValueFromMap(entries.iterator(), entries.size());
    }

    public static <K, V> Map.Entry<K, V> getRandomValueFromMap(Map<K, V> map) {

        return getRandomValueFromMap(map.entrySet());
    }

    public static String getRandomString(int numberOfSymbols, char... chars) {
        return new RandomStringGenerator.Builder()
                .selectFrom(chars)
                .build()
                .generate(numberOfSymbols);
    }

    public static String getRandomAlphaNumeric(int numberOfSymbols) {
        return getRandomString(numberOfSymbols, (ALPHABETIC_LOWER_CASE + ALPHABETIC_UPPER_CASE + NUMERIC).toCharArray());
    }

    public static String getRandomNumeric(int numberOfSymbols) {
        return getRandomString(numberOfSymbols, (NUMERIC).toCharArray());
    }


}
