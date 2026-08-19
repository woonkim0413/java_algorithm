package algorithm_study;

import java.io.*;
import java.util.*;

public class snail_1954 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = new StringTokenizer("");
	static int[] dx = {1, 0, -1, 0};
	static int[] dy = {0, 1, 0, -1};
	
	public static void main(String[] args) throws Exception {
		int case_t = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= case_t; t ++) {
			int side = Integer.parseInt(br.readLine());
			int[][] map = new int[side][side];
			
			int direction = 0;
			int cur_x = -1;
			int cur_y = 0;
			int next_x = 0;
			int next_y = 0;
			for (int i = 1; i <= side * side; i ++) {
				next_x = cur_x + dx[direction];
				next_y = cur_y + dy[direction];
				
				if (next_x < 0 || next_y < 0 || next_x >= side || next_y >= side
						|| map[next_y][next_x] != 0) {
					direction = (direction + 1) % 4;
					next_x = cur_x + dx[direction];
					next_y = cur_y + dy[direction];
				}
				map[next_y][next_x] = i;
				cur_x += dx[direction];
				cur_y += dy[direction];
			}
			System.out.println("#" + t);
			for (int i = 0; i < side; i ++) { 
				for (int j = 0; j < side; j ++) {
					System.out.print(map[i][j] + " ");
				}
				System.out.println();
			}
		}
	}
}
//이해
/*
 변의 size를 받으면 변 x 변의 사각형을 그린다.
*/

//로직 (논리 + 코드 레벨)
/*
 for문을 변x변만큼 돌린다.
 dx, dy 배열을 만든 다음 오른쪽, 아래, 왼쪽, 위로 이동하게 한다.
 조건문을 통해 변x변 범위를 벗어나거나 사각형 내 0이 아닌 숫자를 만나면 방향을 튼다.
 방향 검사는 먼저 next_x, next_y를 계산한 다음에 이를 통해서 구한다.
*/

//배운 것
/*

*/

//input
/*
2
3
4
(output)

*/

//다른 사람 코드
/*

*/