package algorithm_study;

import java.util.*;
import java.io.*;

// 파라미터 서치 (이분 탐색)
public class carver_wood_7730 {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static char[][] map;
	static char[][] c_map;
	static int minChange;
	static char[] A;
	static char[] B;
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


			A = new char[W];
			B = new char[W];
			for (int i = 0; i < W; i ++) {
				A[i] = 'A';
				B[i] = 'B';
			}


			map = new char[D][W];
			c_map = new char[D][W];
			for (int i = 0; i < D; i ++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j ++) {
					char ch = st.nextToken().charAt(0);
					map[i][j] = ch;
					c_map[i][j] = ch;
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
			return;
		}
		
		changeRow(deepth, A);
		dfs(deepth + 1, change + 1);
		changeRow(deepth, map[deepth]);
		
		changeRow(deepth, B);
		dfs(deepth + 1, change + 1);
		changeRow(deepth, map[deepth]);
		
		dfs(deepth + 1, change);
	}
	
	
	static boolean check() {
		int pass = 0;
		
		// row가 고정이고 열을 움직임
		for (int i = 0; i < W; i ++) {
			int same = 1;
			char cur = c_map[0][i];
			
			for (int j = 1; j < D; j ++) {
				if (cur == c_map[j][i])
					same ++;
				else {
					same = 1;
					cur = c_map[j][i];
				}
				
				if (same == K)
					pass ++;
			}
		}

		return (pass == W) ? true : false;
	}
	
	static void changeRow(int deepth, char[] arr) {
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

*/

//input
/*

(output)

*/

//다른 사람 코드
/*

*/