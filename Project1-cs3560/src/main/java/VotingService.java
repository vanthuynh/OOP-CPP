import java.util.Arrays;
import java.util.HashMap;

public final class VotingService {
    private HashMap<String, Integer> frequency_map = new HashMap<>();

    public static void countFrequency(int [] arr) {
        int len = arr.length;
        for (int i = 0; i < len; ++i) {
            if (arr[i] > 0) {
                System.out.println(i);
            }
        }
    }

    public static void displayFrequency() {
        System.out.println("Poll Result: ");
    }

}
