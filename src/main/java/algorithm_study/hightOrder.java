
package algorithm_study;

import java.util.*;
import java.io.*;

public class hightOrder {

    static int N, M;
    static int[][] graph; // 그래프 인접행렬로 표현
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {

        int TC = Integer.parseInt(br.readLine());

        for (int t = 1; t <= TC; t++) {

            N = Integer.parseInt(br.readLine());
            M = Integer.parseInt(br.readLine());

            graph = new int[N + 1][N + 1];

            // 0번째 index -1 초기화 (탐색 여부 판단을 위해) (메모리제이션)
            for (int i = 1; i <= N; i++) {
                graph[i][0] = -1;
            }

            // 방향 그래프 생성 (나보다 큰 정점을 1로 표시)
            for (int i = 0; i < M; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());

                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                graph[a][b] = 1;
            }

            // 모든 학생에 대해 DFS
            for (int i = 1; i <= N; i++) {
                if (graph[i][0] == -1) {
                    dfs(i);
                }
            }

            // 자신보다 작은 학생 수 계산 (column)
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if (graph[i][j] == 1) {
                        graph[0][j]++;
                    }
                }
            }

            // 키 순서를 알 수 있는 학생 수
            int answer = 0;
            for (int i = 1; i <= N; i++) {
                if (graph[i][0] + graph[0][i] == N - 1) {
                    answer++;
                }
            }

            System.out.println("#" + t + " " + answer);
        }
    }

    static void dfs(int cur) {
        for (int next = 1; next <= N; next++) {

            if (graph[cur][next] == 0)
                continue;

            // 해당 점점이 탐색되어 있지 않다면 탐색
            if (graph[next][0] == -1) {
                dfs(next);
            }

            // 해당 정점은 위 if문으로 반드시 탐색되었으므로 해당 정점의 탐색 결과를 cur에 적용
            for (int j = 1; j <= N; j++) {
                if (graph[next][j] == 1) {
                    graph[cur][j] = 1;
                }
            }
        }

        // cur보다 키가 큰 정점 숫자 카운트하여 index 0에 저장
        int count = 0;
        for (int i = 1; i <= N; i++) {
            if (graph[cur][i] == 1) {
                count++;
            }
        }
        graph[cur][0] = count;
    }
}
