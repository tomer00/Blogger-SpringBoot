package com.tomer.blogger.utils;

import java.util.Random;

public class Const {

    public static final String IMAGES_PATH = "images/";


    public static String getRandomName() {
        var sb = new StringBuilder();

        var r = new Random();
        var allowed = "abcdefghijklmnopqrstuvwxyz";
        allowed += allowed.toUpperCase();

        for (int i = 0; i < 12; i++) {
            sb.append(allowed.charAt(r.nextInt(52)));
        }

        return sb.toString();
    }
}
