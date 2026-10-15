package algorithm_study;

import java.util.*;
import java.io.*;

public class protected_film_2112_retry {
	static int min;
	static char[][] map;
	static int H;
	static int W;
	static int K;
	static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		int TC = sc.nextInt();
		
		for (int t = 1; t <= TC; t ++) {
			H = sc.nextInt();
			W = sc.nextInt();
			K = sc.nextInt();
			
			map = new char[H][W];
			
			for (int i = 0; i < H; i ++) {
				for (int j = 0; j < W; j ++) {
					map[i][j] = (char) (sc.nextInt() + '0'); // 숫자 문자열을 받아야 하는 경우에 '0'를 더해야 함
				}
			}
			
			min = Integer.MAX_VALUE;
			bfs(0, '1', 0);
			bfs(0, '0', 0);
			bfs(0, 'X', 0);
			System.out.println("#"+t+" "+min);
		}
	}
	
	// count는 이전까지의 약품 처리 숫자; 현재 bfs에서 cur에 따라 증가시켜서 재귀 호출 시점에 전달
	static void bfs(int index, char cur, int count) {
		if (count >= min) { // 원래 if (index == H)도 ||로 검사함 근데 여기서 검사하면 H-1 행에 대해 check 검사를 안 하게 됨 (gpt)
			return ;
		}
		
		if (check()) {
			min = Math.min(count, min);
			return ;
		}
		
		if (index == H)
			return;
		
		char[] temp = map[index].clone();
		
		count = treatment(index, cur, count);
		bfs(index + 1, '1', count);
		bfs(index + 1, '0', count);
		backup(index, temp); // 이전 작업 복구
		
		bfs(index + 1, 'X', count);
	}
	
	static boolean check() {
		out:for (int w = 0; w < W; w++) {
			char cur_char = '1';
			int sameCount = 0;
			
			for (int c = 0; c < H; c++) {
				if (map[c][w] == cur_char)
					sameCount ++;
				else {
					cur_char = map[c][w];
					sameCount = 1;
				}
				
				if (sameCount == K) {
					continue out;
				}
			}
			
			// for문 안의 break를 타지 못했다는 뜻은 해당 열은 조건을 충족하지 못했다는 의미임
			return false;
		}
		
		// 모든 for문을 break로 통과했기에 모든 열이 조건을 만족함
		return true;
	}
	
	static int treatment(int index, char cur, int count) {
		if (cur == 'X')
			return count;
		
		for (int i = 0; i < W; i ++) {
			map[index][i] = cur;
		}
		return count + 1;
	}
	
	static void backup(int index, char[] arr) {
		map[index] = arr;
	}
}

//로직 (논리 + 코드 레벨)
/*
두께가 1~13임 그렇기에 3^13의 완탐을 하면 모든 경우의 수를 확인할 수 있다 (150만 경우의 수)
제한 시간이 5초니 쌉가능임
- 각 층마다 유지, A처리, B처리로 분기함
- A,B 재귀 호출 전에 count를 늘리고 호출이 끝다면 다시 상태를 복원하는 백트레킹 사용
- A,B로 한 줄을 변경하는 method 필요
- 약품 처리마다 완료 조건 검사
- 최소 약품 처리 숫자 이상으로 count가 증가하면 가지치기 사용

*/

//배운 것
/*

*/

//input
/*
2
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
(output)

*/

//다른 사람 코드
/*

*/