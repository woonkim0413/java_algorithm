package SSAFY.완전탐색;

import java.util.*;

public class Sum_of_subsequences_by_bfs_stack {

    static int [] arr;
    static int count;
    static Deque<Node> stack = new ArrayDeque<>();

    private static class Node {
        int index;
        int value;

        private Node (int value, int index) {
            this.index = index;
            this.value = value;
        }
    }

    // 백트레킹 + dfs 사용
    static void backtracking(int K) {
        count = 0;
        stack.clear();
        stack.push(new Node(arr[0], 0));
        stack.push(new Node(0, 0));

        while (!stack.isEmpty())
        {
            Node node = stack.pop(); // 자동 unpacking으로 가능하긴 하지만 좀 고민
            if (node.value >= K) {
                if (node.value == K)
                    count++;
                continue;
            }
            if (node.index < arr.length - 1) {
                stack.push(new Node(arr[node.index + 1] + node.value, node.index + 1));
                stack.push(new Node(node.value, node.index + 1));
            }
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
            backtracking(K);
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
