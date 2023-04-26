package learn;

import test.MyHashMap;

public class TestMyHashMap {

    public static void main(String[] args) {
        test1();
    }

    static void test0() {
        MyHashMap map = new MyHashMap();
        for (int i = 0; i < 100; i++) {
            map.put("刘华强" + i, "你这瓜保熟吗？" + i);
        }
        System.out.println(map.size());
        for (int i = 0; i < 100; i++) {
            System.out.println(map.get("刘华强" + i));
        }
    }

    static void test1() {
        MyHashMap map = new MyHashMap();
        map.put("刘华强1", "哥们，你这瓜保熟吗？");
        map.put("刘华强1", "你这瓜熟我肯定要啊！");
        System.out.println(map.get("刘华强1"));
    }

}
