package codetree;

import java.util.*;
import java.io.*;

public class glacier_seing_queue {
	static int[][] map;
	static Queue<int[]> bingQ = new ArrayDeque<>();
	static Queue<int[]> waterQ = new ArrayDeque<>();
	static Scanner sc = new Scanner(System.in);
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	static int row;
	static int col;
	static int[][] visited;
	
	public static void main(String[] args) {
		row = sc.nextInt();
		col = sc.nextInt();
		
		map = new int[row][col];
		for (int i = 0; i < row; i++) {
			for (int j =0; j < col; j ++) {
				map[i][j] = sc.nextInt();
			}
		}
		
		// 시뮬
		int time = 0;
		int sum = 0;
		visited = new int[row][col];
		waterQ.offer(new int[] {0, 0});
		
		searchBing();
		
		while (true) {
			int count = 0;
			
			while (!bingQ.isEmpty()) {
				int[] cur = bingQ.poll();
				
				count ++;
				waterQ.offer(new int[] {cur[0], cur[1]});
			}
			
			if (count == 0) {
				System.out.println(time + " " + sum);
				break;
			}
			time ++;
			sum = count;
			
			searchBing();
		}
	}
	
	static void searchBing() {
		while(!waterQ.isEmpty()) {
			int[] cur = waterQ.poll();
			
			for (int i = 0; i < 4; i++) {
				int nextR = cur[0] + dr[i];
				int nextC = cur[1] + dc[i];
				
				if (nextR < 0 || nextR >= row || nextC < 0 || nextC >= col)
					continue;
				
				if (visited[nextR][nextC] == 1)
					continue;
				
				if (map[nextR][nextC] == 1) {
					bingQ.offer(new int[] {nextR, nextC});
					visited[nextR][nextC] = 1;
				} else {
					waterQ.offer(new int[] {nextR, nextC});
					visited[nextR][nextC] = 1;
				}
			}
		}
	}
}

//로직 (논리 + 코드 레벨)
/*

*/

//배운 것
/*

*/

//input
/*

(output)

*/

//다른 사람 코드
/*
 * GPT가 제작
 * 
import java.util.*;
import java.io.*;

public class Main {

    static int N, M;
    static int[][] map;
    static boolean[][] visited;

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    // 바깥물 탐색 큐
    static Queue<int[]> waterQ = new ArrayDeque<>();
    // 다음 초에 녹일 빙하 큐
    static Queue<int[]> iceQ = new ArrayDeque<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
        );
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int time = 0;
        int lastCount = 0;

        // (0,0)은 항상 바깥물
        waterQ.offer(new int[]{0, 0});
        visited[0][0] = true;

        // 초기 바깥물 탐색 및 첫 번째 빙하 탐색
        searchWater();

        // 빙하가 전부 녹을 때까지 반복
        while (!iceQ.isEmpty()) {

            // 이번 초에 녹일 빙하의 개수
            int size = iceQ.size();
            lastCount = size;

            // 이번 초의 빙하를 동시에 녹임
            for (int i = 0; i < size; i++) {

                int[] cur = iceQ.poll();
                int x = cur[0];
                int y = cur[1];

                map[x][y] = 0;

                // 녹은 빙하는 바깥물이 됨
                waterQ.offer(new int[]{x, y});
            }

            time++;

            // 새롭게 확장된 바깥물에서만 BFS
            searchWater();
        }

        System.out.println(time + " " + lastCount);
    }

    static void searchWater() {

        while (!waterQ.isEmpty()) {

            int[] cur = waterQ.poll();
            int x = cur[0];
            int y = cur[1];

            for (int d = 0; d < 4; d++) {

                int nx = x + dx[d];
                int ny = y + dy[d];

                if (nx < 0 || nx >= N || ny < 0 || ny >= M)
                    continue;

                if (visited[nx][ny])
                    continue;

                visited[nx][ny] = true;

                if (map[nx][ny] == 0) {
                    // 연결된 바깥물
                    waterQ.offer(new int[]{nx, ny});
                } else {
                    // 다음 초에 녹일 빙하
                    iceQ.offer(new int[]{nx, ny});
                }
            }
        }
    }
}
*/
