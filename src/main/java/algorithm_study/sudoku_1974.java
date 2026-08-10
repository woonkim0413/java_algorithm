package algorithm_study;

import java.util.*;
import java.io.*;

public class sudoku_1974 {
	
	private static int[] inspect_arr = new int[10];
	private static int[][] arr = new int[9][9];
	private static int flag;
	
	private static void init_inspect_arr() {
		for (int i = 0; i < 10; i ++) {
			inspect_arr[i] = -1;
		}
	}
	
	private static int check_3x3(int i, int j) {		
		init_inspect_arr();
		
		for (int y = i; y < i + 3; y ++) {
			for (int x = j; x < j + 3; x ++) {
				int value = arr[y][x];
				if (inspect_arr[value] != -1) {
					return (0);
				}
				inspect_arr[value] = value;
			}
		}
		return (1);
	}
	
	private static int check_row(int y) {
		init_inspect_arr();
		
		for (int x = 0; x < 9; x ++) {
			int value = arr[y][x];
			if (inspect_arr[value] != -1) {
				return (0);
			}
			inspect_arr[value] = value;
		}	
		return 1;
	}
	
	private static int check_col(int x) {
		init_inspect_arr();
		
		for (int y = 0; y < 9; y ++) {
			int value = arr[y][x];
			if (inspect_arr[value] != -1) {
				return (0);
			}
			inspect_arr[value] = value;
		}
		return 1;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		
		int case_t = Integer.parseInt(br.readLine());
		
		// 2차원 배열에 값 넣기
		for (int t = 0; t < case_t; t ++) {
			flag = 1;
			
			for (int i = 0; i < 9; i ++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < 9; j ++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			// 3x3 체크
			for(int i = 0; i < 9; i += 3) {
				if (flag == 0)
					break;
				for(int j = 0; j < 9; j += 3) {
					if (flag == 0) 
						break;
					if (check_3x3(i, j) == 0) {
						flag = 0;
					}
					
				}
			}
			
			// 가로 체크
			for (int i = 0; i < 9; i ++) {
				if (flag == 0) 
					break;
				if (check_row(i) == 0) {
					flag = 0;
				}
			}
			
			// 세로 체크
			for (int i = 0; i < 9; i ++) {
				if (flag == 0) 
					break;
				if (check_col(i) == 0) {
					flag = 0;
				}
			}
			sb.append("#").append(t+1).append(" ").append(flag);
			if (t + 1 != case_t) {
				sb.append("\n");
			}
		}
		System.out.println(sb.toString());
	}
}


// 이해
/*
 * 9 x 9 사각형에서 3x3에 겹치는 숫자가 없으며 가로, 세로 한 줄에 겹치는 숫자가 모두 없는 경우
 * 해당 케이스에서 1을 출력하고 겹치는 숫자가 있다면 0을 반환한다.
*/

// 로직 (논리 + 코드 레벨)
/*
 * 9개의 3x3 검사, 가로 검사, 세로 검사 총 3개의 축으로 검사를 진행하면 될 것 같다.
 * 
 * 크기가 10인 arr을 만들어서 기본은 -1로 초기화 하고 탐색할 때 특정 숫자를 발견하면
 * 해당 숫자를 index로 사용하여 arr에 접근 후 해당 위치에 1을 넣으면 될 것 같다.
 * 만약 arr의 특정 위치에 접근할 때 값이 -1이 아니면 수도쿠가 완성되어 있지 않다고 판단하여 0을 넣는다.
 * 
 * 세로 줄은 2차원 배열의 y를 변화하며 검사하고 가로 줄은 2차원 배열의 x즐을 이동하여 검사하면 될 것 같다.
 * 3x3은 2중 for문으로 i는 y를 +3 증가, j는 x 값을 +3씩 증가시켜서 이동시키고
 * 수식은 해당 좌표를 기준으로 y ~ y+2, x ~ x+2를 탐색한다.
 * 
 * 변수:
 * case, arr[]
*/

// 배운 것
/*
 * 1) 부적절한 type 배열 사용
 * private static int[] inspect_arr = new int[10]; 대신
 * private static boolean[] inspect_arr = new boolean[10];
 * 처럼 boolean type arrary로 사용하는 것이 더 적절했다.
 * 내가 체크하고 싶었던 것은 배열에 접근이 있었는지에 대한 유무였기 때문이다.
*/

// input
/*
 * 6
7 3 6 4 2 9 5 8 1
5 8 9 1 6 7 3 2 4
2 1 4 5 8 3 6 9 7
8 4 7 9 3 6 1 5 2
1 5 3 8 4 2 9 7 6
9 6 2 7 5 1 8 4 3
4 2 1 3 9 8 7 6 5
3 9 5 6 7 4 2 1 8
6 7 8 2 1 5 4 3 9
1 2 3 4 5 6 7 8 9
4 5 6 7 8 9 1 2 3
7 8 9 1 2 3 4 5 6
2 3 4 5 6 7 8 9 1
5 6 7 8 9 1 2 3 4
8 9 1 2 3 4 5 6 7
3 4 5 6 7 8 9 1 2
6 7 8 9 1 2 3 4 5
9 1 2 3 4 5 6 7 8
7 3 6 4 2 9 5 8 7
5 8 9 1 6 7 3 2 4
2 1 4 5 8 3 6 9 7
8 4 7 9 3 6 1 5 2
1 5 3 8 4 2 9 7 6
9 6 2 7 5 1 8 4 3
4 2 1 3 9 8 7 6 5
3 9 5 6 7 4 2 1 8
6 7 8 2 1 5 4 3 9
7 3 6 4 2 9 5 8 1
7 8 9 1 6 3 4 2 5
2 1 4 5 8 7 6 9 3
8 4 7 9 3 6 1 5 2
1 5 3 8 4 2 9 7 6
9 6 2 7 5 1 8 4 3
4 2 1 3 9 8 7 6 5
3 9 5 6 7 4 2 1 8
6 7 8 2 1 5 3 4 9
7 3 6 4 2 9 5 8 1
5 7 9 1 6 3 4 2 8
2 1 4 5 8 7 6 9 3
8 4 7 9 3 6 1 5 2
1 5 3 8 4 2 9 7 6
9 6 2 7 5 1 8 4 3
4 2 1 3 9 8 7 6 5
3 9 5 6 7 4 2 1 8
6 8 7 2 1 5 3 4 9
1 1 3 4 5 6 7 8 9
4 5 6 7 8 9 1 2 3
7 8 9 1 2 3 4 5 6
2 3 4 5 6 7 8 9 1
5 6 7 8 9 1 2 3 4
8 9 1 2 3 4 5 6 7
3 4 5 6 7 8 9 1 2
6 7 8 9 1 2 3 4 5
9 1 2 3 4 5 6 7 8

#1 1
#2 1
#3 0
#4 0
#5 0
#6 0
*/



