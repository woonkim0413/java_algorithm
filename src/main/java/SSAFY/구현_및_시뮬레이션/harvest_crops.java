package SSAFY.구현_및_시뮬레이션;

import java.util.*;

public class harvest_crops {

    static int[][] arr;

    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        int test_num = kb.nextInt();
        StringBuilder result = new StringBuilder("");

        for (int t = 0; t < test_num; t++) {
            int N = kb.nextInt();
            kb.nextLine();
            arr = new int[N][N];
            for (int i = 0; i < N; i++) {
                String str = kb.nextLine();
                for (int j = 0; j < N; j++) {
                    arr[i][j] = str.charAt(j) - '0';
                }
            }
            int mid = N / 2;
            int sum = 0;
            int start = -1;
            int end = N;
            for (int down = mid; down >= 0; down --) {
                ++start;
                --end;
                for (int i = start; i <= end; i++) {
                    sum += arr[down][i];
                }
            }
            start = 0;
            end = N - 1;
            for (int up = mid + 1; up < N; up ++) {
                start ++;
                end --;
                for (int i = start; i <= end; i++) {
                    sum += arr[up][i];
                }
            }

            result.append("#").append(t + 1).append(" ").append(sum);
            if (t + 1 < test_num) {
                result.append("\n");
            }
        }
        System.out.println(result);
    }
}
