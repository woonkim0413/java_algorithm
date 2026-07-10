package SSAFY.완전탐색;

import java.util.*;
import java.io.*;

public class kill_fly3 {
    static int[][] map;
    static int M;
    static int N;
    static int[] plus_dy = {0, 1, 0, -1};
    static int[] plus_dx = {1, 0, -1, 0};
    static int[] aux_dy = {-1, 1, 1, -1};
    static int[] aux_dx = {1, 1, -1, -1};

    private static int cal_one_direction_kills(int cur_y, int cur_x, int dy, int dx, int num, int kills) {
        // 2차원 배열은 static으로 선언
        if ((cur_y < 0 || cur_y >= N) || (cur_x < 0 || cur_x >= N) || num >= M)
            return kills;
        num++;
        return cal_one_direction_kills(cur_y + dy, cur_x + dx, dy, dx, num, kills + map[cur_y][cur_x]);
    }

    public static void main(String args[]) throws Exception {
        // 접근 풀이
        // 순회를 사용해야 할 것 같다.
        // 2차원 배열의 좌측 상단부터 시작해서 끝까지 하나 하나 검사해야 할 것 같다
        // 받은 N을 기준으로 연산에 사용할 값을 구한다
        // 하나의 좌표는 + 또는 x 형태로 계산될 수 있기에` 두 개의 방향 좌표를 가지고 있어야 한다.
        // 시계 방향을 기준으로 +인 경우엔 dy(0,1,0,-1) dx(1,0,-1,0)을 갖는다 x인 경우엔 dy(-1,1,1,-1) dx(1,1-1,-1)을 갖는다.
        // 계산 범위는 0 < x < N, 0 < y < N이다.
        // M의 세기까지 각 방향을 확장하기 위해선 for문을 돌려도 될 것 같고 recursive method(현재 좌표, dx, dy, 해당 방향까지 계산 값)를 만들어서 사용해도 될 것 같다.

        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            map = new int[N][N];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            int max_kills = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    int kills = 0;
                    for (int k = 0; k < 4; k++) {
                        kills += cal_one_direction_kills(i, j, plus_dy[k], plus_dx[k], 0, 0);
                    }
                    kills -= 3 * map[i][j];
                    if (max_kills < kills)
                        max_kills = kills;
                }
                for (int j = 0; j < N; j++) {
                    int kills = 0;
                    for (int k = 0; k < 4; k++) {
                        kills += cal_one_direction_kills(i, j, aux_dy[k], aux_dx[k], 0, 0);
                    }
                    kills -= 3 * map[i][j];
                    if (max_kills < kills)
                        max_kills = kills;
                }
            }
            sb.append('#')
                    .append(t + 1)
                    .append(' ')
                    .append(max_kills);
            if (t + 1 < T)
                sb.append('\n');
        }
        System.out.println(sb.toString());
    }
}
