package SSAFY.구현_및_시뮬레이션;

import java.util.*;

public class Kill_flies {
    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        StringBuilder result = new StringBuilder("");
        int test_num = kb.nextInt();

        for (int t = 0; t < test_num; t++) {
            int N = kb.nextInt();
            int M = kb.nextInt();
            int max_kill = 0;
            int[][] arr = new int[N][N];

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    arr[i][j] = kb.nextInt();
                }
            }
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    int temp = 0;
                    // 가로 유효성 체크
                    if (j + M > N || i + M > N)
                        continue;
                    for (int k = 0; k < M; k ++) {
                        for (int u = 0; u < M; u ++) {
                            temp += arr[i+k][j+u];
                        }
                    }
                    if (temp > max_kill)
                        max_kill = temp;
                }
            }
            result.append("#")
                    .append(t + 1)
                    .append(" ")
                    .append(max_kill);
            if (t + 1 < test_num)
                result.append("\n");
        }
        System.out.println(result.toString());
    }
    // 첫 줄은 테스트 숫자
    // 각 테스트 단위의 첫 번째 줗은 "N" "M"이 제공됨
    // 그 다음은 N x N 배열이 제공된다.
    // 배열의 원소 값은 30 이상을 넘지 않는다. (이런거 조건문으로 막아줘야 하나?)
    // 1) 먼저 테스트 숫자를 받아서 숫자만큼 for문을 돌린다.
    // 2) for문에서 NxN만큼 int를 받아서 int[][]에 담는다.
    // 3) 2차원 배열을 완전탐색하여 MxM값을 구한 다음 최대로 높은 값을 max_kill 변수에 넣는다.
    // 4) 이렇게 얻은 값을 StringBuilder 객체에 추가한다.
}
