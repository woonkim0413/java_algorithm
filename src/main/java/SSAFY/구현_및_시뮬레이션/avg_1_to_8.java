package SSAFY.구현_및_시뮬레이션;

import java.util.*;

public class avg_1_to_8 {
    public static void main(String args[]) throws Exception
    {
        Scanner kb = new Scanner(System.in);
        StringBuilder result = new StringBuilder("");
        int test_num = kb.nextInt();
        for (int t = 0; t < test_num; t++) {
            int[] arr = new int[10];
            double sum = 0;
            for (int i = 0;  i < 10; i ++) {
                arr[i] = kb.nextInt();
            }
            Arrays.sort(arr);
            for (int i = 1; i < 9; i ++) {
                sum += arr[i];
            }
            result.append("#").append(t+1).append(" ").append(Math.round(sum / 8));
            if (t + 1 < test_num)
                result.append("\n");
        }
        System.out.println(result);
    }
}
