package inflean.algorithm_string;

import java.util.*;

public class extract_numbers {
    public static void main (String[] argv) {
        Scanner kb = new Scanner(System.in);
        String str = kb.nextLine();
        StringBuilder result = new StringBuilder("");
        int i = 0;
        while (i < str.length()) {
            if (str.charAt(i) > '0' && str.charAt(i) <= '9')
                break;
            i ++;
        }
        while (i < str.length()) {
            if (str.charAt(i) >= '0' && str.charAt(i) <= '9')
                result.append(str.charAt(i));
            i ++;
        }
        System.out.println(result.toString());
    }
}
