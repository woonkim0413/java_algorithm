package algorithm_study;

import java.util.*;
import java.io.*;

public class protected_film_2112 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int[][] map;
	static int[][] c_map;
	static int minChange;
	static int[] A;
	static int[] B;
	static int K;
	static int W;
	static int D;

	public static void main(String[] args) throws IOException {

		int TC = Integer.parseInt(br.readLine());

		for (int t = 1; t <= TC; t++) {

			st = new StringTokenizer(br.readLine());

			D = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			minChange = Integer.MAX_VALUE;

			A = new int[W];
			B = new int[W];

			for (int i = 0; i < W; i ++) {
				A[i] = 0;
				B[i] = 1;
			}

			map = new int[D][W];
			c_map = new int[D][W];

			for (int i = 0; i < D; i ++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j ++) {
					int num = Integer.parseInt(st.nextToken());
					map[i][j] = num;
					c_map[i][j] = num;
				}
			}
			
			dfs(0, 0);

			System.out.println("#"+t+" "+minChange);
		}
	}

	static void dfs(int deepth, int change) {

		if (change >= minChange) 
			return ;

		if (check()) {
			minChange = change;
			return ;
		}
		
		// 이 조건을 빼먹어서 30분간 고생함 -> 논리적으로 추적하는 연습 하기
		// changeRow의 c_map[deepth][i]에서 OutofIndex가 발생한 것이니
		// deepth 범위가 언제 넘어가는지는 생각하면 해당 조건이 빠졌다는 것을 시간을 들이면 찾을 수 있음
		if (deepth == D)
			return ;

		dfs(deepth + 1, change);
		
		changeRow(deepth, A);
		dfs(deepth + 1, change + 1);
		changeRow(deepth, map[deepth]);

		changeRow(deepth, B);
		dfs(deepth + 1, change + 1);
		changeRow(deepth, map[deepth]);

	}

	// 실패 줄을 하나 발견하는 시점에 return해야 시간 복잡도가 좋아진다.
	static boolean check() {
		// row가 고정이고 열을 움직임
		for (int i = 0; i < W; i ++) {
			int same = 0;
			int cur = c_map[0][i];
			boolean pass = false;

			for (int j = 0; j < D; j ++) {

				if (cur == c_map[j][i])
					same ++;
				else {
					same = 1;
					cur = c_map[j][i];
				}

				if (same == K) {
					pass = true;
					break ;
				} 
			}
			
			if (!pass) {
				return false;
			}
		}
		return true;
	}

	static void changeRow(int deepth, int[] arr) {

		for (int i = 0; i < W; i ++) {
			c_map[deepth][i] = arr[i]; 
		}
	}
}

//로직 (논리 + 코드 레벨)
/*
1번 row부터 마지막 D까지를 dfs로 탐색하면 될 것 같다.
변화를 주어서 조건이 만족되면 조건이 만족되기 전까지 준 변화 횟수를 저장한다.
-> A로 바꿀지 B로 바꿀지는 어떻게 판단하지? 전부 무지성으로 탐색하는게 맞나?
-> 무지성 탐색 하면 될 것 같다 어차피 한 줄에 3개의 경우의 수가 나온다 (A, B, X)
-> 시간 복잡도 개념이나 이 번 기회에 공부해보면 좋을 것 같다.

이후 탐색에서는 변화 횟수를 넘어가면 가지치기한다.
이전 상태 복구는 어떻게 구현하지?
-> 처음 시작 map을 저장해둔 뒤에 복구 시점엔 해당 map 정보를 사용하면 될 것 같다.
*/

//배운 것

/*
인풋
10
6 8 3
0 0 1 0 1 0 0 1
0 1 0 0 0 1 1 1
0 1 1 1 0 0 0 0
1 1 1 1 0 0 0 1
0 1 1 0 1 0 0 1
1 0 1 0 1 1 0 1
6 8 3
1 1 1 1 0 0 1 0
0 0 1 1 0 1 0 1
1 1 1 1 0 0 1 0
1 1 1 0 0 1 1 0
1 1 0 1 1 1 1 0
1 1 1 0 0 1 1 0
6 8 4
1 1 0 0 0 1 1 0
1 0 1 0 0 1 1 1
0 1 0 0 1 1 0 0
1 0 1 0 0 0 0 0
1 1 0 0 0 0 0 0
1 0 0 0 1 1 1 1
6 4 4
1 1 0 0
0 1 0 1
0 0 0 1
1 1 1 1
1 1 0 1
1 0 1 0
6 10 3
0 1 0 0 0 1 0 0 1 1
0 1 1 0 0 1 0 0 1 0
0 1 0 0 1 0 1 1 1 1
0 0 0 0 0 1 1 1 1 0
0 1 0 0 1 1 1 1 1 1
1 0 0 0 1 1 0 0 1 1
6 6 5
0 0 0 0 0 0
0 0 0 0 0 0
0 0 0 0 0 0
0 0 0 0 0 0
0 0 0 0 0 0
0 0 0 0 0 0
6 6 4
1 1 1 1 1 1
0 0 0 0 0 1
0 1 1 1 0 1
0 1 0 1 0 1
0 1 0 0 0 1
0 1 1 1 1 1
8 15 3
0 1 1 0 0 1 1 0 1 1 0 0 0 0 0
1 0 0 0 1 1 0 0 0 0 0 1 0 1 1
1 1 0 1 0 1 0 1 0 1 0 1 0 0 0
0 1 1 1 0 0 1 0 0 0 0 1 0 0 0
0 0 0 0 0 0 1 0 0 0 1 1 0 0 1
1 0 1 0 0 1 0 1 1 1 1 0 1 1 1
0 0 0 0 0 1 1 1 0 0 0 0 0 1 0
0 0 1 0 1 1 0 1 1 0 0 0 1 0 0
10 20 4
1 0 1 1 1 1 1 1 1 1 0 0 1 1 1 0 1 1 0 1
1 1 0 1 1 1 0 0 1 0 0 0 1 1 1 1 0 0 1 0
1 1 0 1 1 0 0 0 1 1 1 1 1 0 0 1 1 0 1 0
0 0 0 1 1 0 0 0 0 1 0 0 1 0 1 1 1 0 1 0
0 1 1 0 1 0 1 0 1 0 0 1 0 0 0 0 1 1 1 1
1 0 1 0 1 0 1 1 0 0 0 0 1 1 1 0 0 0 0 0
0 1 0 0 1 1 0 0 0 0 0 1 1 0 0 1 1 0 1 1
1 0 0 0 0 1 0 1 1 0 1 1 0 1 0 0 1 1 1 0
0 1 1 0 0 1 0 1 0 0 0 0 0 0 0 1 1 1 0 1
0 0 0 0 0 0 1 1 0 0 1 1 0 0 0 0 0 0 1 0
13 20 5
1 1 0 1 0 0 0 1 1 1 1 0 0 0 1 1 1 0 0 0
1 1 1 1 0 1 0 1 0 0 0 0 1 0 0 0 0 1 0 0
1 0 1 0 1 1 0 1 0 1 1 0 0 0 0 1 1 0 1 0
0 0 1 1 0 1 1 0 1 0 0 1 1 0 0 0 1 1 1 1
0 0 1 0 0 1 0 0 1 0 0 0 0 1 0 0 0 0 1 1
0 0 1 0 0 0 0 0 0 0 0 0 1 1 1 0 0 1 0 1
0 0 0 1 0 0 0 0 0 0 1 1 0 0 0 1 0 0 1 0
1 1 1 0 0 0 1 0 0 1 1 1 0 1 0 1 0 0 1 1
0 1 1 1 1 0 0 0 1 1 0 1 0 0 0 0 1 0 0 1
0 0 0 0 1 0 1 0 0 0 1 0 0 0 0 1 1 1 1 1
0 1 0 0 1 1 0 0 1 0 0 0 0 1 0 1 0 0 1 0
0 0 1 1 0 0 1 0 0 0 1 0 1 1 0 1 1 1 0 0
0 0 0 1 0 0 1 0 0 0 1 0 1 1 0 0 1 0 1 0
아웃풋
#1 2
#2 0
#3 4
#4 2
#5 2
#6 0
#7 3
#8 2
#9 3
#10 4
*/