package Jungol;

import java.util.*;
import java.io.*;

public class kanpsack2_1278 {
	static int[][] items; // 무게, 가치
	static int[][] tabul;
	static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		int N = sc.nextInt();
		int weight = sc.nextInt();
		
		// 물건 저장
		items = new int[N+1][2]; // 맨 처음 물건은 이전에 고려한 물건이 없기에 for문에서 정상적으로 돌리려면 N+1로 생성한 뒤 맨 처음 칸을 비어둠
		int w =0;
		int value =0;
		for(int i=1;i<=N;i++) {
			w = sc.nextInt();
			value = sc.nextInt();
			items[i][0] = w;
			items[i][1] = value;
		}

		// 시뮬 시작
		tabul = new int[N+1][weight+1];
		
		for(int i=1;i<=N;i++) {
			for(w=0; w<=weight; w++) {
				
				if (w >= items[i][0]) {
					tabul[i][w] = Math.max(tabul[i-1][w], tabul[i-1][w-items[i][0]] + items[i][1]);
				} else {
					tabul[i][w] = tabul[i-1][w];
				}
			}
		}
		System.out.println(tabul[N][weight]);
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
