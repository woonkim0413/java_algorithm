package algorithm_study;

import java.util.*;
import java.io.*;

/*
NxN으로 표현되는 맵이 있어 여기서 2차원으로 표현되는 좌표는 각각 높 낮이 (0~9)로 표현될 수 있으며 
시작과 끝은 각각 0,0과 N-1, N-1이야 N은 4<=N<=30의 크기를 갖어 높이가 큰 곳에서 낮은 곳으로 
이동할 땐 에너지를 사용하지 않지만 높이가 낮은 곳에서 높은 곳으로 이동할 땐 높이x2의 에너지를 써서 (동일한 높이는 에너지 1)
주어진 맵에서 시작부터 끝까지 이동할 때 가장 에너지를 적게 드는 경로의 에너지를 출력하는 문제
*/
public class clim_mountain_dijkstra  {
    static Scanner sc = new Scanner(System.in);
    static int[][] dist;
    static int[][] map;
    static int N;

    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {-1, 0, 1, 0};

    public static void main(String args[]) throws Exception {
        int TC = sc.nextInt();

        for (int t = 1; t <= TC; t++) {
            N = sc.nextInt();

            map = new int[N][N];
            dist = new int[N][N];

            for (int i = 0; i < N; i++) {
                Arrays.fill(dist[i], Integer.MAX_VALUE);

                for (int j = 0; j < N; j++) {
                    map[i][j] = sc.nextInt();
                }
            }

            int answer = dijkstra();
            System.out.println("#" + t + " " + answer);
        }
    }

    static int dijkstra() {

        // {y, x, 누적 에너지}
        PriorityQueue<int[]> pq = new PriorityQueue<>(
            (a, b) -> Integer.compare(a[2], b[2])
        );

        dist[0][0] = 0;
        pq.offer(new int[]{0, 0, 0});

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();

            int y = cur[0];
            int x = cur[1];
            int fuer = cur[2];

            // 이미 더 적은 에너지로 방문한 좌표
            if (fuer > dist[y][x]) continue;

            // 목적지에 최소 에너지로 도착
            if (y == N - 1 && x == N - 1) {
                return fuer;
            }

            for (int i = 0; i < 4; i++) {
                int nextX = x + dx[i];
                int nextY = y + dy[i];

                if (nextX < 0 || nextX >= N ||
                    nextY < 0 || nextY >= N)
                    continue;

                int curFuer = 0;
                int diff = map[nextY][nextX] - map[y][x];

                if (diff > 0) {
                    curFuer = diff * 2;
                } else if (diff == 0) {
                    curFuer = 1;
                }

                int nextFuer = fuer + curFuer;

                // 기존보다 저렴한 경로만 저장
                if (nextFuer < dist[nextY][nextX]) {
                    dist[nextY][nextX] = nextFuer;

                    pq.offer(new int[]{
                        nextY, nextX, nextFuer
                    });
                }
            }
        }

        return -1;
    }
}
