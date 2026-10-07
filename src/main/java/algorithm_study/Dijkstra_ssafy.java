package algorithm_study;

import java.util.*;
import java.io.*;


public class Dijkstra_ssafy {
	
	static int[] dist;
	static List<int[]>[] graph; // 연결된 정점 번호, 가중치
	static PriorityQueue<int[]> pq; // 정점 후보 번호, 후보 가중치
	static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		int N = sc.nextInt();
		int E = sc.nextInt();
		int start = sc.nextInt();
		
		pq = new PriorityQueue<>((a,b) -> Integer.compare(a[1], b[1]));
		dist = new int[N + 1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		
		graph = new ArrayList[N + 1]; // ArrayList를 담을 배열을 만드는 것이기에 generic을 위한 꺽쇄 사용 x
		for (int i = 1; i <= N; i ++) {
			graph[i] = new ArrayList<>();
		}
		
		for (int i = 1; i <= E; i ++) {
			int from = sc.nextInt();
			int to = sc.nextInt();
			int weight = sc.nextInt();
			
			graph[from].add(new int[] {to, weight});
		}
		
		dist[start] = 0;
		pq.offer(new int[]{start, 0});
		
		while (!pq.isEmpty()) {
			int[] cur = pq.poll();
			
			if (cur[1] != dist[cur[0]]) { // 가종치가 최소가 아닌 후보 필터링
				continue ; 
			}
			
			for (int[] edge : graph[cur[0]]) {
				int[] candi = {edge[0], edge[1] + dist[cur[0]]}; // 간선 가중치 + queue에서 뽑은 가중치
				
				if (candi[1] < dist[candi[0]]) { // 생성한 후보가 이전 후보보다 가중치가 큰 경우 필터
					dist[candi[0]] = candi[1];
					pq.offer(candi);
				}
			}
		}
		for (int i = 1; i < dist.length; i++) {
			System.out.print(dist[i] + " ");
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
(두 번째 줄은 시작 정점 의미)
10 17
1
1 2 4
1 3 6
3 2 3
2 4 9
2 5 8
3 5 2
3 6 3
5 4 2
4 7 6
5 7 3
5 8 7
5 6 1
6 8 4
6 9 8
7 10 13
8 10 9
9 10 4
(output)
dist 출력: 0, 4, 6, 10, 8, 9, 11, 13, 17, 21
*/

//다른 사람 코드
/*

*/


		
