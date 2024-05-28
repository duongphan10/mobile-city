package com.vn.mobilecity.util;


import java.util.Random;

public class CodeUtil {

    public static String generateCode(int length) {
        String characters = "0123456789";
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            char randomChar = characters.charAt(index);
            sb.append(randomChar);
        }
        return sb.toString();
    }

    public static String generateCodeOrder(int id, int length) {
        String characters = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random random = new Random();
        StringBuilder sb = new StringBuilder();

        // Generate a random string of uppercase letters
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            char randomChar = characters.charAt(index);
            sb.append(randomChar);
        }

        // Convert ID to string and handle length
        String idString = Integer.toString(id);
        String formattedId;
        if (idString.length() > 3) {
            // Take the last 4 characters if ID is longer than 4 characters
            formattedId = idString.substring(idString.length() - 3);
        } else {
            // Otherwise, pad with leading zeros
            formattedId = String.format("%03d", id);
        }
        sb.append(formattedId);

        return sb.toString();
    }

}
