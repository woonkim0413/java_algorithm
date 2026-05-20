package SSAFY.완전탐색;

import java.util.*;

public class Sum_of_subsequences_by_bitmask {

    static int [] arr;
    static int count;

    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        StringBuilder result = new StringBuilder("");

        int test_num = kb.nextInt();
        for (int i = 0; i < test_num; i ++) {
            int N = kb.nextInt();
            int K = kb.nextInt();
            arr = new int[N];
            for (int j = 0; j < N; j++) {
                arr[j] = kb.nextInt();
            }
            count = 0;

            // N이 10이면 bitmask < 1024가 된다.
            for (int bitmask = 1; bitmask < (1 << N); bitmask ++) {
                int value = 0;
                // bitmask 값의 bit 자릿수를 검사하여 자릿수가 1인 경우
                // 해당 자릿수를 arr index로 사용하여 total 값 구함
                // ex) 0110이면 arr[1] + arr[2]를 계산하여 k랑 비교
                for (int t = 0; t < N; t++) {
                    if ((bitmask & (1 << t)) != 0) {
                        value += arr[t];
                    }
                }
                if (value == K)
                    count ++;
            }
            result.append("#")
                    .append(i + 1)
                    .append(" ")
                    .append(count);
            if (i + 1 < test_num) {
                result.append("\n");
            }
        }
        System.out.println(result);
    }
}
