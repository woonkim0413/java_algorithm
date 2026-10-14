package algorithm_study;

import java.util.*;
import java.io.*;
import java.lang.reflect.Array;

public class break_block {

	static int[][] map;
	static int[][] tempMap;
	static int N;
	static int W;
	static int H;
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {-1, 0, 1, 0}; 
	static Scanner sc = new Scanner(System.in);
	static int min_block;
	static int[] path_permut;
	
	public static void main(String[] args) {
		int TC = sc.nextInt();
		
		for (int t = 1; t <= TC; t ++) {
			N = sc.nextInt();
			W = sc.nextInt();
			H = sc.nextInt();
			
			map = new int[H][W];
			tempMap = new int[H][W];
			for (int i = 0; i < H; i++) {
				for (int j = 0; j < W; j++) {
					map[i][j] = sc.nextInt();
				}
			}
			
			// 시뮬
			path_permut = new int[N];
			min_block = Integer.MAX_VALUE;
			bfs(0);
			
			System.out.println("#"+t+" "+min_block);
		}
	}
	
	// 순열
	static void bfs(int index) {
		// 모든 경로 완성, 벽돌 깨기 시작
		if (index == N) {
			for (int i = 0; i < H; i++) {
				for (int j = 0; j < W; j++)
					tempMap[i][j] = map[i][j];
			}
			
			// N번 공 drop
			for (int i = 0; i < path_permut.length; i++) {
				drop_ball(i);
				gravitiy();
			}
			
			// 남은 벽돌 세기
			int count = 0;
			for (int i = 0; i < H; i ++) {
				for (int j = 0; j < W; j ++) {
					if (tempMap[i][j] != 0)
						count++;
				}
			}
			min_block = Math.min(count, min_block);
			return ;
		}
		
		// 순열 완탐
		for (int i = 0; i < W; i ++) {
			path_permut[index] = i;
			bfs(index + 1);
			// path_permut[index] = -1; // 이거 없어도 상관 x
		}
	}
	
	static void drop_ball(int ball_num) {
		int cur_col = path_permut[ball_num];
		
		int nextX = cur_col;
		int nextY = 0;
		
		while (true) {
			if (nextY >= H) 
				return;
			
			if (tempMap[nextY][nextX] > 0) {
					get_ball(nextY, nextX);
					return;
			}
			nextY += 1;
		}
	}

	// 공 만남 -> 길이만큼 지움 ; bfs 구조
	static void get_ball(int Y, int X) {
		int lenth = tempMap[Y][X];
		tempMap[Y][X] = 0; // 디버깅 시점에 추가
		
		for (int i = 0; i < 4; i ++) {
			int nextY = Y;
			int nextX = X;
			
			for (int j = 0; j < lenth-1; j ++) { // ex) 길이가 2면 양 방향으로 한 칸씩
				nextY += dy[i];
				nextX += dx[i];
				
				if (nextY < 0 || nextY >= H || nextX < 0 || nextX >= W)
					break ;
				
				if (tempMap[nextY][nextX] == 1) {
					tempMap[nextY][nextX] = 0;
				} else if (tempMap[nextY][nextX] > 1) {
					get_ball(nextY, nextX); // 중간에 1이상 벽돌 만나면 bfs
				}
			}
		}
	}
	
	static void gravitiy() {
		for (int i=0; i < W; i ++) {
			end:for (int j = H-1; j >= 0; j --) {
				
				// 처음 0을 만다는 지점
				if (tempMap[j][i] == 0) {
					for (int searchI = j; searchI >=0; searchI--) {
						
						// 0 다음에 오는 블럭 (swap)
						if (tempMap[searchI][i] != 0) {
							tempMap[j][i] = tempMap[searchI][i];
							tempMap[searchI][i] = 0;
							break ; // 디버깅 시점에 찾음 (gpt가 알려줌)
						}
					}
				}
			}
		}
	}
}

//로직 (논리 + 코드 레벨)
/*
 *  시간 복잡도 3초
	구슬 쏘는 횟수가 4, width가 12이니 완탐 돌려도 경우의 수가 1만 언저리 / 하나의 경우의 수마다 맵을 다시 그려도
	맵의 크기가 최대 100 언저리이니 상관 없음

	블록 지우는 건 재귀 / 방향 및 길이 arg로 받아서 블록 지우는 method 사용
*/

//배운 것
/*

*/

//input
/*
2
3 10 10
0 0 0 0 0 0 0 0 0 0
1 0 1 0 1 0 0 0 0 0
1 0 3 0 1 1 0 0 0 1
1 1 1 0 1 2 0 0 0 9
1 1 4 0 1 1 0 0 1 1
1 1 4 1 1 1 2 1 1 1
1 1 5 1 1 1 1 2 1 1
1 1 6 1 1 1 1 1 2 1
1 1 1 1 1 1 1 1 1 5
1 1 7 1 1 1 1 1 1 1
2 9 10
0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0
0 1 0 0 0 0 0 0 0
0 1 0 0 0 0 0 0 0
1 1 0 0 1 0 0 0 0
1 1 0 1 1 1 0 1 0
1 1 0 1 1 1 0 1 0
1 1 1 1 1 1 1 1 0
1 1 3 1 6 1 1 1 1
1 1 1 1 1 1 1 1 1
(output)
#1 12
#2 27
*/

//다른 사람 코드
/*

*/