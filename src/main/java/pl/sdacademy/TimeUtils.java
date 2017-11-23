package pl.sdacademy;

public class TimeUtils {

    public static String formattedLength(int length) {
        int minutes = length /60;
        int seconds = length % 60;
        return String.format("%d:%02d",minutes,seconds);
    }
}
