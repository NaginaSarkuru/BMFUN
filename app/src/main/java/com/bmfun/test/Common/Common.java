package com.bmfun.test.Common;

public class Common {

    public static final int Level_1 = 30000; // 30 second per question
    public static final int Level_2 = 20000; // 20 second per question
    public static final int Level_3 = 15000; // 15 second per question
    public static final int Level_4 = 10000; // 10 second per question
    public static final int Level_5 = 8000; // 8 second per question

    public enum LEVEL{
        LEVEL1,
        LEVEL2,
        LEVEL3,
        LEVEL4,
        LEVEL5
    }

    public static char[] user_submit_answer;
    public static int count=1;
    public static String[] alphabet_character={
            "a","b","c","d","e","f","g","h","i","j","k","l",
            "m","n","o","p","q","r","s","t","u","v","w","x",
            "y","z"

    };


}
