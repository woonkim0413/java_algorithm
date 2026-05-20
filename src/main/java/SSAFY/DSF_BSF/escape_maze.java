package SSAFY.DSF_BSF;

import java.util.*;

public class escape_maze {
    static final int TEST_NUM = 10;
    static int[][] arr = new int[16][16];
    static Deque<Node> stack = new ArrayDeque<>();
    // 서, 남, 동, 북 방향으로임
    static int[] x_direction = {1, 0, -1, 0};
    static int[] y_direction = {0, 1, 0, -1};

    static class Node {
        int x;
        int y;

        Node(int y, int x) {
            this.x = x;
            this.y = y;
        }
    }

    static int solved_maze() {
        stack.clear();

        // 시작 위치 찾아서 stack에 넣음
        for (int y = 1; y < 15; y++) {
            for (int x = 1; x < 15; x++) {
                if (arr[y][x] == 2)
                    stack.push(new Node(y, x));
            }
        }
        while (!stack.isEmpty()) {
            Node node = stack.pop();
            int x = node.x;
            int y = node.y;
            arr[y][x] = -1;
            // dy = y + y_direction[i]으로 저장한 뒤에 사용하는 것이 더 좋았을 듯 함
            for (int i = 0; i < 4; i ++) {
                if (arr[y + y_direction[i]][x + x_direction[i]] == 0) {
                    stack.push(new Node(y + y_direction[i], x + x_direction[i]));
                }
                if (arr[y + y_direction[i]][x + x_direction[i]] == 3) {
                    return 1;
                }
            }
        }
        return 0;
        // 이렇게 풀면 index를 못 찾네
//        for (int[] row : arr) {
//            for (int num : row) {
//                if (num == 2)
//            }
//        }

        // 먼저 시작 위치 2를 찾음
        // 2의 좌표를 deque에 넣음
        // deque를 사용하여 dfs를 구현함 (recursive로도 구현 가능한지 gpt 서칭)
        // 지나간 위치는 -1로 전환
        // deque가 isEmpty인데 더이상 갈 곳이 없다면 0 반환
        // 3을 만나면 1 반환
    }

    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        StringBuilder result = new StringBuilder("");
        String line;

        for (int t = 0; t < TEST_NUM; t ++) {
            kb.nextLine();
            for (int i = 0; i < 16; i++) {
                line = kb.nextLine();
                for (int j = 0; j < 16; j++) {
                    arr[i][j] = line.charAt(j) - '0';
                }
            }
            result.append("#")
                    .append(t + 1)
                    .append(" ")
                    .append(solved_maze());
            if (t + 1 < TEST_NUM)
                result.append("\n");
        }
        System.out.println(result.toString());
    }
}
