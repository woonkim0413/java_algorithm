package inflean.algorithm_string;

import java.util.*;

public class char_change {
    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        String str1 = kb.next();
        char[] str = str1.toCharArray();
        for (int i = 0; i < str1.length(); i++) {
            if (str[i] >= 65 && str[i] <= 90) {
                str[i] += 32;
            } else if (str[i] >= 97 && str[i] <= 122) {
                str[i] -= 32;
            }
        }
        System.out.println("my answer: " + new String(str));
    }
}
