package SSAFY.구현_및_시뮬레이션;

import java.util.*;

public class snail_number {
    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        int test_num = kb.nextInt();

        StringBuilder result = new StringBuilder("");
        int[] x = {1, 0, -1, 0};
        int[] y = {0, 1, 0, -1};

        for (int i = 0; i < test_num; i++) {
            int N = kb.nextInt();
            int current_x = -1;
            int current_y = 0;
            int current_num = 0;
            int directions = 0;
            int[][] board = new int[N][N];
            for (int[] row : board) {
                Arrays.fill(row, -1);
            }
            int flag = 1;
            for (; flag == 1; ) {
                if (current_y + y[directions] >= 0 && current_x + x[directions] >= 0 &&
                        current_x + x[directions] < N && current_y + y[directions] < N &&
                        board[current_y + y[directions]][current_x + x[directions]] == -1) {
                    current_x += x[directions];
                    current_y += y[directions];
                    current_num += 1;
                    board[current_y][current_x] = current_num;
                } else {
                    directions = (directions + 1) % 4;
                    if (current_y + y[directions] < 0 || current_x + x[directions] < 0 ||
                            current_x + x[directions] >= N || current_y + y[directions] >= N ||
                            board[current_y + y[directions]][current_x + x[directions]] != -1) {
                        flag = 0;
                    }
                }
            }
            result.append("#" + (i + 1) + "\n");
            for (int t = 0; t < N; t++) {
                for (int k = 0; k < N; k++) {
                    result.append(board[t][k]);
                    if (k < N - 1) {
                        result.append(" ");
                    }
                }
                if (t < N - 1) {
                    result.append("\n");
                }
            }
            if (i + 1 < test_num)
                result.append("\n");
            // int[][]를 StringBuilder에 넣어야 함 추가로 값 사이엔 " "가 들어가야 함
        }
        System.out.print(result);
    }
        // 첫 값은 test 갯수
        // 다음 값들은 NxN의 N값
        // 메인 로직은 N값을 받아서 2차원 배열을 만들고 값을 계속 추가함
        // 2차원 배열은 모두 -1로 초기화 한다
        // 0,0부터 시작해서 처음엔 x방향으로 값을 1씩 늘려간다
        // x가 N - 1에 도달하거나 다음 index가 -1이 아닌 경우 방향을 튼다
        // 현재 방향은 list x = [1, 0, -1, 0], list y = [1, 0, -1, 0]를 각각 만들어서 방향을 틀 때마다 index를 한 칸씩 이동한다.
        // 현재 index를 통해 접근된 값을 현재 좌표의 x,y에 각각 더해서 이동한다.
        // 방향을 튼 다음에 바로 다음 값이 -1이 아닌 경우 종료한다.
        // 이렇게 얻은 2차원 배열을 stringBuilder에 추가한다.
}
