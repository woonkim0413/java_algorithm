package algorithm_study;

import java.util.*;
import java.io.*;

public class glacier_codetree {

	static int time = 0;
	static int[] check = new int[2]; // 이전, 현재
	static Deque<int[]> queue = new ArrayDeque<>();
	static int[][] map;
	static int row;
	static int col;
	// 상 하 좌 우
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	// 출력
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		row = sc.nextInt();
		col = sc.nextInt();

		map = new int[row][col];
		for (int i = 0; i < row; i ++) {
			for (int j = 0; j < col; j ++) {
				map[i][j] = sc.nextInt();
			}
		}

		while(true) {
			time ++;

			warm();

			if (check[1] == 0) {
				System.out.println((time - 1) + " " + (check[0]));
				break ;
			}
			check[0] = check[1];
		}
	}

	static void warm() {
		int[][] temp = new int[row][col];

		for (int i = 0; i < row; i++) {
			temp[i] = Arrays.copyOf(map[i], col);
		}

		// 바깥물 탐색 + 빙하 녹이기
		check[1] = bfs(temp);

		map = temp;
	}

	static int bfs(int[][] temp) {
		queue.clear();

		boolean[][] visited = new boolean[row][col];

		int count = 0;

		queue.add(new int[]{0, 0}); // 0,0은 항상 바깥물임
		visited[0][0] = true;

		while (!queue.isEmpty()) {
			int[] cur = queue.poll();

			int r = cur[0];
			int c = cur[1];

			// 현재 물 기준 4방향 탐색
			for (int d = 0; d < 4; d++) {

				int nr = r + dr[d];
				int nc = c + dc[d];

				if (nr < 0 || nr >= row ||
						nc < 0 || nc >= col) {
					continue;
				}

				// 물이면 계속 BFS 탐색
				if (map[nr][nc] == 0) {

					if (!visited[nr][nc]) {
						visited[nr][nc] = true;
						queue.add(new int[]{nr, nc});
					}
				}

				// 빙하면 녹이고 카운트
				else {
					if (temp[nr][nc] == 1) {
						temp[nr][nc] = 0;
						count++;
					}
				}
			}
		}
		return count;
	}
}

//로직 (논리 + 코드 레벨)
/*
	바깥 물 안쪽에서 빙하를 둘러싸고 있는 물 형태는 없다고 가정하자 (문제 언급 없으니)

	완탐으로 맵 탐색, 물인 경우 좌우 이동으로 x,y가 가장자리까지 이동할 수 있는지 확인 <- 이동할 수 있으면 가장자리 물임
	해당 물이 가장자리인지 확인하는 방법은 bfs 사용

	가장자리 물인 경우 얼음을 녹임 (녹인 갯수 기록)
	만약 이 번 턴에 녹인 갯수가 0이라면 이전 녹인 갯수 출력

 */

//배운 것
/*
 1) Scanner 및 BufferedReader 사용 판단 기준
 입력 값이 10만개 이상이면 Scanner보다 BufferedReader + StringTokenizer 조합을 사용하는 것이 권장됨
 해당 문제도 입력이 최대 200 x 200으로 Scanner보다 BufferedReader를 쓰는 편이 좋음 그러나 필수까진 아니다.
 */

//input
/*

(output)

 */

//다른 사람 코드
/*

 */