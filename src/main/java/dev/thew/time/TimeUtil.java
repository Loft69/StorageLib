package dev.thew.time;

public class TimeUtil {

    private static String getWordForm(long number, String form1, String form2, String form5) {
        number = Math.abs(number) % 100;
        long n = number % 10;
        if (number > 10 && number < 20) return form5;
        if (n > 1 && n < 5) return form2;
        if (n == 1) return form1;
        return form5;
    }

    public static String getTimeEnding(long seconds) {
        long hours = seconds / 3600;
        long minutes = (seconds % 3600) / 60;
        long secs = seconds % 60;

        StringBuilder result = new StringBuilder();

        if (hours > 0) result
                .append(hours)
                .append(" ")
                .append(getWordForm(hours, "час", "часа", "часов"))
                .append(" ");
        if (minutes > 0) result
                .append(minutes)
                .append(" ")
                .append(getWordForm(minutes, "минута", "минуты", "минут"))
                .append(" ");
        if (secs > 0 || result.isEmpty()) result
                .append(secs)
                .append(" ")
                .append(getWordForm(secs, "секунда", "секунды", "секунд"));

        return result.toString().trim();
    }

}
