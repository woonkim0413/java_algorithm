package SSAFY.구현_및_시뮬레이션;

import java.util.*;

public class Oneje_recovery_memory {
    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        int test_num = kb.nextInt();
        kb.nextLine();

        StringBuilder result = new StringBuilder("");
        String str;
        int modify_count;
        int j;
        for (int i = 0; i < test_num; i ++) {
            str = kb.nextLine();
            modify_count = 0;
            j = 0;
            while (j < str.length() && str.charAt(j) == '0')
                j ++;
            if (j < str.length())
                modify_count ++;
            for ( ; j < str.length() - 1; j ++) {
                if (str.charAt(j) != str.charAt(j + 1)) {
                    modify_count++;
                }
            }
            result.append("#" + (i + 1) + " " + modify_count);
            if ((i + 1) < test_num)
                result.append("\n");
        }
        System.out.println(result);
        // 001111010011011100 처음에 0을 만나면 넘어감 1을 만나면 count 이후엔 앞의 값과 다른 경우 count하고 연속되면 카운트 x
    }
}
