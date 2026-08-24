package algorithm_study;

import java.util.*;
import java.io.*;

public class chef {

    static int N;
    static int[][] synergy;
    static boolean[] selected;
    static int minDifference;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(
                new InputStreamReader(System.in)
        );

        int T = Integer.parseInt(br.readLine());

        StringBuilder result = new StringBuilder();

        for (int testCase = 1; testCase <= T; testCase++) {

            N = Integer.parseInt(br.readLine());

            synergy = new int[N][N];
            selected = new boolean[N];
            minDifference = Integer.MAX_VALUE;

            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());

                for (int j = 0; j < N; j++) {
                    synergy[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            /*
             * A 음식과 B 음식을 서로 바꿔도 맛의 차이는 같다.
             * 따라서 0번 식재료를 항상 A 음식에 넣어 중복을 제거한다.
             */
            selected[0] = true;

            selectIngredients(1, 1);

            result.append("#")
                  .append(testCase)
                  .append(" ")
                  .append(minDifference)
                  .append("\n");
        }

        System.out.print(result);
    }

    /**
     * A 음식에 들어갈 N/2개의 식재료를 선택한다.
     *
     * @param index 현재 확인할 식재료 번호
     * @param count 현재까지 A 음식에 선택한 식재료 개수
     */
    static void selectIngredients(int index, int count) {

        // 맛의 차이는 음수가 될 수 없으므로 0이면 최솟값 확정
        if (minDifference == 0) {
            return;
        }

        // A 음식에 필요한 식재료를 모두 선택한 경우
        if (count == N / 2) {
            calculateDifference();
            return;
        }

        // 더 이상 선택할 식재료가 없는 경우
        if (index == N) {
            return;
        }

        /*
         * 남아 있는 식재료를 전부 선택해도
         * N/2개를 만들 수 없다면 탐색할 필요가 없다.
         */
        if (count + (N - index) < N / 2) {
            return;
        }

        // 현재 식재료를 A 음식에 넣는 경우
        selected[index] = true;
        selectIngredients(index + 1, count + 1);

        // 현재 식재료를 B 음식에 넣는 경우
        selected[index] = false;
        selectIngredients(index + 1, count);
    }

    /**
     * 선택된 식재료는 A 음식,
     * 선택되지 않은 식재료는 B 음식으로 두고 맛을 계산한다.
     */
    static void calculateDifference() {

        int tasteA = 0;
        int tasteB = 0;

        for (int i = 0; i < N - 1; i++) {
            for (int j = i + 1; j < N; j++) {

                int pairSynergy = synergy[i][j] + synergy[j][i];

                if (selected[i] && selected[j]) {
                    tasteA += pairSynergy;
                } else if (!selected[i] && !selected[j]) {
                    tasteB += pairSynergy;
                }
            }
        }

        int difference = Math.abs(tasteA - tasteB);
        minDifference = Math.min(minDifference, difference);
    }
}
