package SSAFY.DSF_BSF;

import java.util.*;

public class Distance_of_maze_dfs {

    static int[][] arr;
    static Deque<Node> stack = new ArrayDeque<>();
    static int min_distance;
    static Node goal;
    static int[] x_direction = {1, 0, -1, 0};
    static int[] y_direction = {0, 1, 0, -1};

    static class Node {
        int x;
        int y;
        int move_num;

        Node(int arg_y, int arg_x, int arg_move_num) {
            x = arg_x;
            y = arg_y;
            move_num = arg_move_num;
        }
    }

    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        StringBuilder result = new StringBuilder("");
        int test_num = kb.nextInt();
        String line;

        for (int t = 0; t < test_num; t++) {
            int N = kb.nextInt();
            kb.nextLine();
            arr = new int[N][N];
            stack.clear();
            min_distance = Integer.MAX_VALUE;

            for (int i = 0; i < N; i++) {
                line = kb.nextLine();
                for (int j = 0; j < N; j++) {
                    arr[i][j] = line.charAt(j) - '0';
                    if (arr[i][j] == 2) {
                        stack.push(new Node(i, j, 0));
                        arr[i][j] = 1;
                    }
                    if (arr[i][j] == 3) {
                        goal = new Node(i, j, -1);
                    }
                }
            }

            while (!stack.isEmpty()) {
                Node node = stack.pop();
                // 만약 목적지에 도착했고 거리가 기존 거리보다 작다면 업데이트
                if (node.y == goal.y && node.x == goal.x) {
                    if (min_distance > node.move_num - 1)
                        min_distance = node.move_num - 1;
                    continue ;
                }

                // 4방향 유효성 검사 후 stack에 넣기
                for (int i = 0; i < 4; i++) {
                    int dx = node.x + x_direction[i];
                    int dy = node.y + y_direction[i];
                    if (dx >= 0 && dy >= 0 && dx < N && dy < N && arr[dy][dx] != 1) {
                        stack.push(new Node(dy, dx, node.move_num + 1));
                        arr[dy][dx] = 1;
                    }
                }
            }
            if (min_distance == Integer.MAX_VALUE) {
                min_distance = 0;
            }

            // 출력
            result.append("#").append(t+1).append(" ").append(min_distance);
            if (t + 1 < test_num)
                result.append("\n");
        }
        System.out.println(result);
    }
}