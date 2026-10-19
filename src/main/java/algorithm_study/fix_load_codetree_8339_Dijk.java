package algorithm_study;

import java.util.*;
import java.io.*;

public class fix_load_codetree_8339_Dijk {

    static PriorityQueue<long[]> pq; // 정점 후보 / 0: 정점 번호 1: 누적 가중치 2: fixNum
    static List<int[]>[] graph; // edge / 0: 연결된 정점 1: 가중치
    static long[][] distance; // 0: 수리 횟수 1: 정점 1에서부터 가중치 합
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        // 그래프 그리기
        graph = new ArrayList[N + 1];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            // 해당 정점을 표현하는 List가 없는 경우
            if (graph[from] == null) {
                graph[from] = new ArrayList<>();
            }
            if (graph[to] == null) {
                graph[to] = new ArrayList<>();
            }

            graph[from].add(new int[]{to, weight});
            graph[to].add(new int[]{from, weight});
        }

        // 우선순위 큐 제작
        // -> Comparator을 건네주는 식으로 정렬 기준을 정의함
        // -> 사용자 정의 클래스를 만든 뒤 Comparable을 구현하고 compareTo()를 Overriding하면
        //    우선순위 큐를 생성할 때 Comparator로 정렬 기준을 건네지 않아도
        //    정의한 정렬 기준에 따라 동작함
        pq = new PriorityQueue<>((a, b) ->
            Long.compare(a[1], b[1])
        );

        // distance 제작
        distance = new long[K + 1][N + 1];
        for (int i = 0; i <= K; i++) {
            Arrays.fill(distance[i], Long.MAX_VALUE);
        }

        // 다잌 시작
        pq.add(new long[]{1, 0, 0});
        distance[0][1] = 0;

        while (!pq.isEmpty()) {
            long[] cur = pq.poll();

            int node = (int) cur[0];
            long cost = cur[1];
            int fixC = (int) cur[2];

            // 최소값이 아니라 큐 뒤로 밀린 값 필터링
            if (cost > distance[fixC][node]) {
                continue;
            }

            for (int[] edge : graph[node]) {
                int Cnode = edge[0];
                int weight = edge[1];

                // 수리 사용
                if (fixC < K) {
                    if (distance[fixC + 1][Cnode] > distance[fixC][node] + 0) {
                        distance[fixC + 1][Cnode] = distance[fixC][node] + 0;
                        pq.add(new long[]{Cnode, distance[fixC][node], fixC + 1});
                    }
                }

                // 수리 미사용
                if (distance[fixC][Cnode] <= distance[fixC][node] + weight) {
                    continue;
                }

                distance[fixC][Cnode] = distance[fixC][node] + weight;
                pq.add(new long[]{Cnode, distance[fixC][node] + weight, fixC});
            }
        }

        // 출력 값 생성
        long min = Long.MAX_VALUE;
        for (int i = 0; i <= K; i++) {
            if (distance[i][N] < min)
                min = distance[i][N];
        }
        System.out.println(min);
    }
}

// 로직 (논리 + 코드 레벨)
/*
    1) dfs + 모든 경로 저장 후 간선 가중치 순으로 정렬 후 k개만큼 0 적용
    1부터 N번까지 갈 수 있는 모든 경로를 구한 뒤에
    구한 경로에 높은 가중치를 갖은 간선부터 k개만큼 가중치를 0으로 만든다.
    가중치가 변경된 경로들의 가중치를 비교하여 가장 가중치가 낮은 경로를 구한다.

    -> 해당 문제는 시간 초과가 난다고 하는데 처음엔 왜 시간 초과가 나는지 몰랐다.
    그러나 그림으로 그려보니 그래프를 dfs로 완전탐색하면
    갈림길이 발생할 때마다 전체 경우의 수가 갈림길 수만큼 곱해진다.
    즉, 2개의 선택지가 있는 갈림길이 30개만 연속해서 존재해도
    경우의 수는 2^30이 된다.
    그렇기에 해당 문제는 dfs로 모든 경우의 수를 구하는 방식으로는 풀 수 없다.

    2) Dijk + 가중치 조합 사용
    Dijk을 구현할 때 사용하는 distance를 2차원 배열로 만든 뒤에
    1차원을 수리 적용 횟수로 사용한다.
*/

// 배운 것
/*

*/

// input
/*

(output)

*/

// 다른 사람 코드
/*

*/