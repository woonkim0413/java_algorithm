package algorithm_study;

import java.util.*;
import java.io.*;

public class monthTest4 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static Deque<XYcor> queue = new ArrayDeque<>();
	static int[][] map;
	static int[][][][] visited;

	// 12부터 시계방향
	static int[] dx = {0, 1, 1, 1, 0, -1, -1, -1, 0};
	static int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1, 0};

	public static void main(String[] args) throws NumberFormatException, IOException {
		int N = Integer.parseInt(br.readLine());

		// 0 sx 1 sy 2 gx 3 gy
		int[] X = new int[4];
		int[] Y = new int[4];

		// X 채우기
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 4; i ++) {
			X[i] = Integer.parseInt(st.nextToken()) - 1;
		}

		// Y 채우기
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 4; i ++) {
			Y[i] = Integer.parseInt(st.nextToken()) - 1;
		}

		// map, visited 생성
		map = new int[N][N];
		visited = new int[N][N][N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j ++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 시뮬 시작
		XYcor goalCor = new XYcor(X[2], X[3], Y[2], Y[3]);
		XYcor startCor = new XYcor(X[0], X[1], Y[0], Y[1]);
		int[] Xc = new int[2];
		int[] Yc = new int[2];
		int time = 0;

		queue.offer(startCor);
		visited[startCor.Xr][startCor.Xc][startCor.Yr][startCor.Yc] = 1;

		end : while(!queue.isEmpty()) {

			int size = queue.size();

			// BFS 한 level의 움직임 표현
			for (int i = 0; i < size; i ++) {
				XYcor temp = queue.poll();

				if (temp.Xr == goalCor.Xr && temp.Xc == goalCor.Xc &&
					temp.Yr == goalCor.Yr && temp.Yc == goalCor.Yc) {
					break end;
				}

				Xc[0] = temp.Xr;
				Xc[1] = temp.Xc;
				Yc[0] = temp.Yr;
				Yc[1] = temp.Yc;

				for (int xdir = 0; xdir < 9; xdir ++) {
					int xNextx = Xc[0] + dx[xdir];
					int xNexty = Xc[1] + dy[xdir];

					// X요원 유효성 검사
					if (xNextx < 0 || xNextx >= N || xNexty < 0 || xNexty >= N) {
						continue ;
					}

					if (map[xNextx][xNexty] == 1) {
						continue;
					}

					for (int ydir = 0; ydir < 9; ydir ++) {
						int yNextx = Yc[0] + dx[ydir];
						int yNexty = Yc[1] + dy[ydir];

						// Y요원 유효성 검사
						if (yNextx < 0 || yNextx >= N || yNexty < 0 || yNexty >= N) {
							continue ;
						}

						if (map[yNextx][yNexty] == 1) {
							continue;
						}
						
						// 두 요원이 겹치는지 확인
						if (Math.abs(xNextx - yNextx) < 2 &&
							Math.abs(xNexty - yNexty) < 2) {
							continue ;
						}

						// 두 요원의 좌표 조합 검증
						if (visited[xNextx][xNexty][yNextx][yNexty] == 0) {

							visited[xNextx][xNexty][yNextx][yNexty] = 1;

							queue.add(new XYcor(xNextx, xNexty, yNextx, yNexty));
						}
					}
				}
			}
			time ++;
		}
		System.out.println(time);
	}

	static class XYcor {
		int Xr;
		int Xc;
		int Yr;
		int Yc;

		public XYcor(int xr, int xc, int yr, int yc) {
			Xr = xr;
			Xc = xc;
			Yr = yr;
			Yc = yc;
		}
	}
}

//로직 (논리 + 코드 레벨)
/*
	접근 (두 요원의 움직임을 묶어서 queue를 사용한 bfs)
	각 요원의 움직임을 묶어서 저장하는 사용자 정의 class를 큐에 넣어서 bfs로 풀려고 했음
    그런데 그렇게 되면 한 번 움직일 때마다 100^n으로 queue에 element가 생성될 것이고
    그러면 10번만 움직여도 100^10의 element가 쌓여 이러면 memory 제한을 초과할 것이라고 판단
    
    -> 접근 방식 맞음.
    여기서 visited를 도입하면 공간 복잡도가 10^N만큼 커지지 않고 n^2 x n^2 만큼만 커진다.
    
    + 생각하지 못했던 접근 방식
    1) 두 요원의 좌표를 4차원 배열 visited를 사용해서 표현할 생각을 하지 못 함 (애초에 visited를 써야겠다는 생각도 제대로 못 함)
    2) 지문에 명시적으로 제자리 이동이 가능하다는 언급이 없이 최대 1만큼 이동 가능하다, 라고 써놔서 제자리 이동 구현 못 함
    3) row, col로 제공한 2차원 배열 좌표를 x,y로 변환할 때 row를 x로 잘 못 전환함 row를 y로 전환하거나 그대로 row 표현법을 사용해야 했음 
*/

//배운 것
/*

*/

//input
/*
5
2 2 4 4
3 4 4 2
1 0 1 1 1
0 0 0 0 0
1 1 0 0 1
1 0 0 0 1
1 0 0 1 1
출력
3

6
6 1 4 6
5 3 6 2
0 0 0 0 0 0
0 1 1 1 1 1
0 0 0 1 0 0
0 1 1 1 1 0
0 0 0 0 0 1
0 0 0 0 0 0
출력
13
*/

//다른 사람 코드
/*

*/

