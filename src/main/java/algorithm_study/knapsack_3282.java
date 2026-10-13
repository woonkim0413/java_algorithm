package algorithm_study;

import java.util.*;
import java.io.*;

public class knapsack_3282 {
	
	static Scanner sc = new Scanner(System.in);
	static int[][] items;
	static int[][] tabul;
	
	
	public static void main(String[] args) {
		int TC = sc.nextInt();
		
		for (int t = 1; t <= TC; t ++) {
			int N = sc.nextInt();
			int volume = sc.nextInt();
			
			items = new int[N+1][2];
			for (int i = 1; i <= N; i ++) {
				items[i][0] = sc.nextInt();
				items[i][1] = sc.nextInt();
			}
			
			// bottom-up DP
			tabul = new int[N+1][volume+1];
			for (int i = 1; i <= N; i++) {
				for (int w = 0; w <= volume; w++) {
					
					tabul[i][w] = tabul[i-1][w]; // 이전 조합 그대로 사용
					
					if (w >= items[i][0]) { // 물건 추가할 수 있으면 더 큰 값 선택
						tabul[i][w] = Math.max(tabul[i-1][w], 
								tabul[i-1][w - items[i][0]] + items[i][1]); // 물건을 선택했을 때와 선택하지 않았을 때 큰 값 선택
					}
				}
			}
			System.out.println("#"+t+" "+tabul[N][volume]);
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

*/
