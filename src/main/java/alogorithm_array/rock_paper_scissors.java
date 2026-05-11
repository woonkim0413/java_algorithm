package alogorithm_array;

import java.util.*;

// 1:가위, 2:바위, 3:보
public class rock_paper_scissors {
    public static void main(String[] strings) {
        Scanner kb = new Scanner(System.in);
        int iterators = Integer.parseInt(kb.nextLine()); // Integer.toValue()랑 뭐가 다르지?
        String human_A = kb.nextLine();
        String human_B = kb.nextLine();
        String[] arrA = human_A.trim().split("\\s+");
        String[] arrB = human_B.trim().split("\\s+");

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < iterators; i++) {
            int A = Integer.parseInt(arrA[i]);
            int B = Integer.parseInt(arrB[i]);

            if (A == B) {
                result.append("D\n");
            } else if (
                (A == 1 && B == 3) ||
                (A == 2 && B == 1) ||
                (A == 3 && B == 2)
            ) {
                result.append("A\n");
            } else {
                result.append("B\n");
            }
        }
        System.out.print(result);
    }
}
