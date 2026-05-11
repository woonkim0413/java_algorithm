package algorithm_string;

import java.util.*;

public class word_flip {
    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        int word_num = Integer.valueOf(kb.nextLine()).intValue();
        if (word_num > 20 || word_num < 3) {
            System.out.println("N 범위가 틀렸습니다.");
        }
        List<StringBuilder> arr = new ArrayList<>(word_num);
        for (int i = 0; i < word_num; i++) {
            arr.add(new StringBuilder(kb.nextLine()).reverse());
        }
        for (int i = 0; i < word_num; i++) {
            System.out.println(arr.get(i));
        }
    }
}