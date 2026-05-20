package SSAFY.완전탐색;

import java.util.*;

public class Sum_of_subsequences_by_dfs_recursive {

    static int [] arr;
    static int count;

    // 백트레킹 + dfs 사용
    static void backtracking(int K, int start, int value) {
        // 백트래킹: 더 볼 필요 없는 경우 중단
        if (value >= K) {
            if (value == K)
                count++;
            return ;
        }
        // DFS: 다음 숫자를 하나 선택하고 깊게 들어감
        for (int i = start; i < arr.length; i ++) {
            backtracking(K, i + 1, value + arr[i]);
        }
    }

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
            backtracking(K, 0, 0);
            result.append("#")
                    .append(i + 1)
                    .append(" ")
                    .append(count);
            if (i + 1 < test_num) {
                result.append("\n");
            }
        }
        // 목표 숫자 및 일차원 배열이 있음
        // 일차원 배열을 돌며 원스들의 합이 목표 숫자와 같은 경우를 찾아내서 숫자를 세고 반환함
        // 첫 번째 원소를 더하고 다음 경우의 수를 쭉 더함
        // 백트레킹을 구현해야 할 것 같음
        System.out.println(result);
    }
}
