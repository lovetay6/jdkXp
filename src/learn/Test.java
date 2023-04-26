package learn;

import java.util.HashMap;

public class Test {

    public static void main(String[] args) {
        HashMap<String, String> map = new HashMap<>();
        map.put("love", "lost");
        String love = map.get("love");
        System.out.println(love);
    }
}
