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

    public static String getTimeEnding(long milliseconds) {
        long secondsTotal = milliseconds / 1000;

        long days = secondsTotal / 86400;
        long remainingSeconds = secondsTotal % 86400;

        long hours = remainingSeconds / 3600;
        remainingSeconds = remainingSeconds % 3600;

        long minutes = remainingSeconds / 60;
        long seconds = remainingSeconds % 60;

        StringBuilder result = new StringBuilder();
        if (days > 0)
            result.append(days)
                    .append(" ")
                    .append(getWordForm(days, "день", "дня", "дней"))
                    .append(" ");
        if (hours > 0)
            result.append(hours)
                    .append(" ")
                    .append(getWordForm(hours, "час", "часа", "часов"))
                    .append(" ");
        if (minutes > 0)
            result.append(minutes)
                    .append(" ")
                    .append(getWordForm(minutes, "минута", "минуты", "минут"))
                    .append(" ");
        if (seconds > 0 || result.isEmpty())
            result.append(seconds)
                    .append(" ")
                    .append(getWordForm(seconds, "секунда", "секунды", "секунд"));

        return result.toString().trim();
    }

}
