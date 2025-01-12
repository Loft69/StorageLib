package dev.thew.time;

public class TimeUtil {

    public static String getTimeEnding(int number, String form1, String form2, String form5) {
        number = Math.abs(number);
        int n100 = number % 100;
        int n10 = number % 10;

        if (n100 >= 11 && n100 <= 14) return form5;
        if (n10 == 1) return form1;
        if (n10 >= 2 && n10 <= 4) return form2;

        return form5;
    }

}
