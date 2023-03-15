package utils;

import org.apache.commons.lang3.RandomStringUtils;

public class RandomUtils {

    public static String getRandomString(int value) {
        String randomString = RandomStringUtils.randomAlphabetic(value);
        return randomString;
    }

    public static Integer getRandomValue(int maxValue){
        double value = Math.random()*(++maxValue);
        return (int) value;
    }

}
