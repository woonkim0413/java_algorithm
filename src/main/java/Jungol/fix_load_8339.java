package Jungol;

import java.util.*;
import java.io.*;

public class fix_load_8339 {
	
	static Scanner sc = new Scanner(System.in);
	static List<int[]>[] graph; // 연결된 정점 번호, 가중치
	static int K;
	static int N;
	static int E;
	
	public static void main(String[] args) {
		N = sc.nextInt();
		E = sc.nextInt();
		K = sc.nextInt();
		
		graph = new List[N + 1];
		for (int i = 1; i <= N; i ++) {
			graph[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < E; i ++) {
			int from = sc.nextInt();
			int to = sc.nextInt();
			int weight = sc.nextInt();
			
			graph[from].add(new int[] {to, weight});
		}
		
		// dfs로 1부터 N까지 가는 경로 모두 체크
		// 해당 경로들에 대해 k를 적용해서 가중치 확인
	}
}

//로직 (논리 + 코드 레벨)
/*
	1) bfs로 1번부터 N까지 갈 수 있는 모든 경로를 구한다.
	2) 하나의 경로를 구성하는 간선들에 대해 가중치가 가장 큰 간선부터 차례로 가중치를 K로 변경한 뒤 경로의 가중치 값을 구한다.
	3) 모든 경로에 대해 가중치 K를 적용한 뒤 가장 가중치 값이 적은 경로를 선택하여 출력한다.
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