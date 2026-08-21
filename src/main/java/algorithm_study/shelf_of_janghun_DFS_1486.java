package algorithm_study;

import java.util.*;
import java.io.*;

public class shelf_of_janghun_DFS_1486 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N;
	static int B;
	static int hightSum;
	
	// 현재 deepth의 staff값은 이전 deepth에서 계산됨 (마지막 deep의 methodStack을 안 쌓기 위해 설계)
	static void dfs(int index, int sum, int[] staffHights) {
		if (sum >= B) {
			hightSum = Math.min(hightSum, sum);
			return ;
		}
		if (index == N - 1 || hightSum == B)
			return ;

		// 다음 staff hight 미포함
		dfs(index + 1, sum, staffHights);
		
		// 다음 staff hight 포함
		dfs(index + 1, sum + staffHights[index + 1], staffHights);
	}
	
	public static void main(String[] args) throws Exception {
		int caseT = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= caseT; t ++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			hightSum = Integer.MAX_VALUE;
			
			st = new StringTokenizer(br.readLine());
			int[] staffHights = new int[N];			
			for (int i = 0; i < N; i ++) {
				staffHights[i] = Integer.parseInt(st.nextToken());
			}
			
			dfs(0, staffHights[0], staffHights);
			
			System.out.println("#" + t + " " + (hightSum - B));
		}
	}
}
//이해
/*
점원들의 조합을 통해 만들 수 있는 탑의 높이를 순회하며
B 이상의 탑 중 가장 높이가 작은 탑 높이를 찾은 뒤 그 높이 - B를 출력한다.
만약 B 높이를 쌓을 수 있다면 0을 출력하면 된다.
*/

//로직 (논리 + 코드 레벨)
/*

*/

//배운 것
/*

*/

//input
/*
3
4 16
3 3 5 6
5 10
1 2 3 4 8
3 12
4 7 10
(output)
#1 1
#2 0
#3 2
*/

//다른 사람 코드
/*

*/