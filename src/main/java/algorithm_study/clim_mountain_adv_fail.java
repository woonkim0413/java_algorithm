package algorithm_study;

import java.util.*;
import java.io.*;

public class clim_mountain_adv_fail {
	static Scanner sc = new Scanner(System.in);
	static int[][] visited;
	static int[][] map;
	static int min;
	static int fuer;
	static int N;
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {-1, 0, 1, 0};
	
	public static void main(String args[]) throws Exception {
		int TC = sc.nextInt();
		
		for (int t = 1; t <= TC; t ++) {
			N = sc.nextInt();
			
			visited = new int[N][N];
			map = new int[N][N];
			min = Integer.MAX_VALUE;
			fuer = 0;
			
			for (int i=0;i<N;i++) {
				for(int j=0;j<N; j++) {
					map[i][j] = sc.nextInt();
				}
			}
			
			visited[0][0] = 1;
			bfs(0,0);
			
			System.out.println("#"+t+" "+min);
		}
	}
	
	static void bfs(int y, int x) {
		if (fuer >= min)
			return;
		
		// 도착했으면 검증
		if (y == N-1 && x == N-1) {
			min = Math.min(fuer, min);
			return ;
		}
		
		List<Num> opPath = new ArrayList<>();
		
		// 4방향에서 내려가든 올라가든 절대값이 적은 것부터 향하게 해보자 (시간초과)
		
		// 북, 동, 남, 서 방향으로 일단 탐색
		for (int i = 0; i < 4; i ++) {
			int nextX = x + dx[i];
			int nextY = y + dy[i];
			
			if (nextX < 0 || nextX >= N || nextY < 0 || nextY >= N) 
				continue;
			
			if (visited[nextY][nextX] == 1)
				continue;
			
			opPath.add(new Num(i, Math.abs(map[nextY][nextX] - map[y][x]), nextY, nextX));
		}
		
		// 절대값이 작은 순으로 정렬
		opPath.sort((a,b) -> Integer.compare(a.abs, b.abs));
		
		for (int i = 0; i < opPath.size(); i ++) {	

			Num cur = opPath.get(i);
			
			int nextX = cur.x;
			int nextY = cur.y;
			
			int curFuer = 0;
			if (map[nextY][nextX] - map[y][x] > 0) {
				curFuer = map[nextY][nextX] - map[y][x];
				curFuer *= 2;
			} else if (map[nextY][nextX] - map[y][x] == 0) {
				curFuer = 1;
			}
				
			fuer += curFuer;
			visited[nextY][nextX] = 1;
			bfs(nextY, nextX);
			visited[nextY][nextX] = 0;
			fuer -= curFuer;
		}
	}
	
	static class Num {
		int i;
		int abs;
		int x;
		int y;
		
		public Num(int i, int abs, int y, int x) {
			super();
			this.i = i;
			this.abs = abs;
			this.y = y;
			this.x = x;
		}
	}
}

/*
visited 사용, bfs 사용, 가지치기 사용 (min값 사용), 이동할 때 연료 값 계산해서 계속 중첩
+ 타임아웃이 뜸 -> 메모리 제이션을 써야하나보다


입력 예
5
4
9 5 7 9
8 4 2 5
7 6 5 4
8 8 9 5
6
1 1 1 1 1 1
9 9 9 9 9 1
9 9 1 1 1 1
9 9 1 9 9 9
9 9 1 9 9 9
9 9 1 1 1 1
아웃풋: 2, 16
1
30
1 8 8 5 8 8 7 5 5 5 2 6 7 4 9 6 0 5 6 9 9 5 9 0 6 8 8 1 7 4
2 7 7 9 9 6 5 7 9 7 6 6 8 2 5 5 3 5 0 3 2 3 5 4 3 7 1 1 4 1
5 9 8 9 1 2 0 0 7 8 8 2 6 4 4 3 2 9 5 0 5 7 8 6 5 2 1 8 4 5
3 5 7 0 1 1 4 5 7 8 4 5 4 7 2 3 2 4 5 0 1 3 8 1 8 0 6 7 3 5
5 0 2 4 3 0 9 8 0 3 0 2 2 8 2 3 7 9 4 1 0 4 9 5 8 6 7 2 0 6
5 5 8 0 8 7 4 2 3 5 2 2 4 4 5 2 0 4 3 0 4 3 4 7 4 9 7 8 9 2
1 7 1 9 3 3 4 1 5 4 7 4 9 1 6 9 0 4 4 5 7 4 3 9 6 5 3 4 6 5
8 3 5 5 9 3 0 5 3 3 8 7 1 4 3 9 0 0 6 0 3 0 5 4 8 1 9 7 4 1
2 3 1 7 3 0 2 2 9 7 6 0 6 7 6 3 5 8 8 9 2 1 5 5 2 7 2 1 1 3
9 5 9 4 2 5 7 1 5 2 8 5 1 8 9 6 0 7 4 4 0 4 2 5 8 1 1 1 6 8
6 9 1 5 2 6 4 9 3 2 5 0 4 3 0 0 6 7 6 5 6 6 9 2 4 4 7 1 2 9
9 3 2 5 2 1 8 9 7 7 8 1 0 0 0 5 1 8 8 9 5 8 8 5 3 5 7 6 0 5
2 1 6 0 5 7 8 2 9 0 5 1 5 6 4 1 9 5 3 8 1 3 0 4 1 9 4 8 4 1
5 2 6 9 8 7 7 3 3 8 8 1 7 3 6 1 4 8 9 9 9 8 4 6 1 5 3 5 5 1
0 9 1 0 8 9 2 1 6 1 2 9 4 3 7 8 7 7 8 8 8 9 7 8 9 2 1 2 4 3
8 8 4 6 2 0 1 8 8 6 6 0 9 0 1 2 2 6 9 7 2 8 3 9 1 1 7 7 1 5
9 1 2 8 4 7 1 6 2 2 6 7 9 4 0 2 9 7 2 6 6 7 7 0 3 3 9 4 7 8
9 1 5 5 6 4 4 6 6 7 7 4 7 9 8 2 9 7 5 9 3 6 8 8 9 7 0 4 4 4
0 5 1 2 9 9 5 0 5 3 1 6 1 1 5 9 0 4 4 1 4 2 2 7 7 6 5 3 5 8
0 7 4 6 6 2 4 5 1 6 0 8 5 9 8 7 8 2 2 3 0 9 1 3 1 3 4 9 9 5
3 6 5 2 7 7 6 6 2 5 8 9 7 9 0 5 9 7 5 8 4 7 4 3 4 6 6 6 0 3
3 9 6 1 6 0 7 6 5 1 3 6 0 5 7 4 8 0 9 8 2 6 4 7 1 4 5 5 6 4
1 0 2 0 9 0 2 8 2 3 6 3 8 2 2 7 7 1 0 5 6 5 7 2 7 1 1 6 6 5
3 8 0 7 2 9 1 2 9 7 0 2 9 8 6 7 5 0 1 3 7 2 5 7 7 7 4 4 8 7
7 2 3 8 6 6 7 2 8 9 9 0 4 6 2 7 5 5 0 9 1 1 7 6 3 6 3 7 8 8
5 7 6 4 9 7 3 0 4 6 9 8 6 8 5 7 8 6 6 2 5 3 2 9 8 0 2 3 5 7
3 2 4 2 7 1 9 6 0 6 5 5 3 1 6 1 4 3 4 6 8 6 1 6 6 2 6 9 0 2
9 2 9 0 9 5 2 7 4 7 7 6 0 6 2 7 9 6 5 4 4 8 3 9 0 5 1 0 3 0
2 9 7 6 6 4 3 5 3 3 6 2 9 4 0 1 8 4 5 6 8 6 8 9 4 4 2 2 3 7
4 8 9 6 6 0 0 0 0 6 0 2 5 5 9 2 8 5 4 1 4 6 2 3 8 6 6 6 0 6
*/