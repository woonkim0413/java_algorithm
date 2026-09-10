package algorithm_study;

import java.util.*;
import java.io.*;

public class connect_processer_1767_MUSTRETRY {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static List<Core> list = new ArrayList<>();
    static int[][] map;

    static int minLine;
    static int maxCore;
    static int N;

    // 상 우 하 좌
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {-1, 0, 1, 0};

    public static void main(String[] args) throws Exception {
        int TC = Integer.parseInt(br.readLine());

        for (int t = 1; t <= TC; t++) {
            N = Integer.parseInt(br.readLine());

            // 초기화
            list.clear();
            minLine = Integer.MAX_VALUE;
            maxCore = 0;
            map = new int[N][N];

            // map 입력
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());

                    // Core 발견
                    if (map[i][j] == 1) {

                    	// 가장자리에 있는 Core인 경우 제외 (정확한 core수를 요구되지 않으므로 배제해도 됨)
                        if (i != 0 && i != N - 1 &&
                            j != 0 && j != N - 1) {
                            list.add(new Core(j, i));
                        }
                    }
                }
            }

            dfs(0, 0, 0);

            System.out.println("#" + t + " " + minLine);
        }
    }

    static void dfs(int index, int sumCore, int sumLine) {

    	// 가지치기
        int remainCore = list.size() - index; // 아직 현재 index는 사용 안 했기에 해당 코드 유효함
        if (sumCore + remainCore < maxCore) {
            return;
        }

        // 이거 안됨 현재까진 라인 수가 더 많을 수가 있으나 이후에 라인 수가 줄어들 수도 있음
        //        if (sumCore == maxCore && sumLine >= minLine) {
        //            return;
        //        }

        // 모든 Core를 처리한 경우
        if (index == list.size()) {

            // 더 많은 Core를 연결한 경우
            if (sumCore > maxCore) {

                maxCore = sumCore;
                minLine = sumLine;
            }

            // 연결 Core 수가 같다면 전선 길이가 짧은 것 선택
            else if (sumCore == maxCore) {

                minLine = Math.min(minLine, sumLine);
            }
            return;
        }

        Core core = list.get(index);

        // 현재 core에 대해 동서남북 방향으로 전선 연결할 수 있는지 체크
        for (int dir = 0; dir < 5; dir++) {
        	
        	// 해당 core을 연결하지 않았을 때 전체 map core 연결 수가 오히려 증가하는 경우도 있을 것이기에 넣음
        	if (dir == 4) {
        		dfs(index + 1, sumCore, sumLine);
        		break ; 
        	}
        		
            // 해당 방향으로 전선을 설치할 수 있는지 체크
            if (!canLine(core, dir)) {
                continue;
            }

            // 전선 설치
            int lineCount = setLine(core, dir, 2);

            dfs(index + 1, sumCore + 1, sumLine + lineCount);

            // 상태 복구
            setLine(core, dir, 0);
        }
    }

    // 해당 방향으로 Core에서 가장자리까지 전선을 설치할 수 있는지 확인
    static boolean canLine(Core core, int dir) {

        int nx = core.x + dx[dir];
        int ny = core.y + dy[dir];

        while (true) {
        	if (nx < 0 || nx >= N || ny < 0 || ny >= N)
        		break ;
        		
            // 0이 아니면 다른 Core(1) 또는 이미 설치된 전선(2)이 있다는 뜻
            if (map[ny][nx] != 0) {
                return false;
            }

            nx += dx[dir];
            ny += dy[dir];
        }
        return true;
    }


    // 전선 설치 또는 복구 (0은 복구, 2는 설치)
    static int setLine(Core core, int dir, int value) {
        int nx = core.x + dx[dir];
        int ny = core.y + dy[dir];

        int count = 0;

        while (true) {
        	if (nx < 0 || nx >= N || ny < 0 || ny >= N)
        		break ;

            map[ny][nx] = value;

            count++;

            nx += dx[dir];
            ny += dy[dir];
        }

        return count;
    }

    static class Core {

        int x;
        int y;

        public Core(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}

//로직 (논리 + 코드 레벨)
/*
NxN map을 주며 core은 1, 그 외 빈 공간은 0으로 표현한다.
1인 경우 전선을 깔아서 가장자리와 연결시켜야 한다.
최대한 많은 core가 연결되어야 하며 (엄... 모든 core가 연결되지 않을 수도 있나본데)
가장 많은 core을 연결한 상태에서 전선 사용이 최소가 되는 숫자를 출력한다.

코드 구현
1) map을 받을 때 각 core 좌표는 따로 Node로 만들어서 arrayList에 저장한다.
2) 각 core의 처리 수를 deepth로 사용하여 bfs + 백트레킹을 수행한다.
3) 백트레킹 가지치기 조건은 1) 전선 수가 이미 현재 최소 수를 넘어갔거나 2) 연결한 core 수 + 남은 core 수가 이미 최대 core수보다 작을 때
4) 전선은 core좌표를 저장한 Node에 direction char을 만들어서 값이 x면 연결 안 됨, 값에 N,E,S,W가 들어 있다면 해당 방향으로 전선이 있음
5) core이 벽에 있다면 전선 연결 필요 없으며 전선 연결 중 core가 있다면 전선 연결 못 함
*/

//배운 것
/*

*/

//input
/*
3
7
0 0 1 0 0 0 0
0 0 1 0 0 0 0
0 0 0 0 0 1 0
0 0 0 0 0 0 0
1 1 0 1 0 0 0
0 1 0 0 0 0 0
0 0 0 0 0 0 0
9
0 0 0 0 0 0 0 0 0
0 0 1 0 0 0 0 0 1
1 0 0 0 0 0 0 0 0
0 0 0 1 0 0 0 0 0
0 1 0 0 0 0 0 0 0
0 0 0 0 0 0 1 0 0
0 0 0 1 0 0 0 0 0
0 0 0 0 0 0 0 1 0
0 0 0 0 0 0 0 0 1
11
0 0 1 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0 1
0 0 0 1 0 0 0 0 1 0 0
0 1 0 1 1 0 0 0 1 0 0
0 0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 1 0 0 0
0 0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 1 0 0
0 0 0 0 0 0 1 0 0 0 0
0 0 0 0 0 0 0 0 0 0 0
(output)
#1 12
#2 10 
#3 24 
*/

//다른 사람 코드
/*

*/