package com.company;

public class Hasher {

    // This is a dumb hash function. Nobody would ever use this in production
    public static int dumbHashFunc(String string){
        char[] thingsToAddUp = string.toCharArray();

        int numberToReturn = 0;

        for (char item: thingsToAddUp){
            numberToReturn += Character.getNumericValue(item);
        }

        return numberToReturn;
    }

    // This is a slightly smarter hash function. Nobody would ever use this in production
    public static int smarterHashFunc(String string){
        char[] thingsToAddUp = string.toCharArray();

        int numberToReturn = 0;

        for (int i = 0; i < thingsToAddUp.length; i++) {
            char item = thingsToAddUp[i];
            numberToReturn += Character.getNumericValue(item) * (i + 1);
        }

        return numberToReturn;
    }
}
