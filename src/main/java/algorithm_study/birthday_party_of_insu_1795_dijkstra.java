package algorithm_study;

import java.util.*;
import java.io.*;

// 다잌 활용
// swea 등산로, jungol fix_load도 풀어보기
public class birthday_party_of_insu_1795_dijkstra {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static List<Edge>[] graph;
	static List<Edge>[] reverseGraph;
	static int[] distance;
	static int[] ReverseDistance;
	static PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1])); // 0: 정점N, 1: weight
	static int N;
	static int M;
	static int X;
	
	public static void main(String[] args) throws IOException  {
		int TC = Integer.parseInt(br.readLine());
		
		for (int t=1;t<=TC;t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			X = Integer.parseInt(st.nextToken());
			
			// ArrayList 자체를 생성하는게 아니라 ArrayList type reference를 담을 수 있는 list를 만드는 코드
			graph = new ArrayList[N+1]; 
			reverseGraph = new ArrayList[N+1];
			distance = new int[N+1];
			ReverseDistance = new int[N+1];
			Arrays.fill(ReverseDistance, Integer.MAX_VALUE);
			Arrays.fill(distance, Integer.MAX_VALUE);
			
			for (int i =1; i <=N; i++) {
				graph[i] = new ArrayList<>();
				reverseGraph[i] = new ArrayList<>();
			}
			
			for (int i=0;i<M;i++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				int weight = Integer.parseInt(st.nextToken());
				
				graph[from].add(new Edge(from, to, weight));
				reverseGraph[to].add(new Edge(to, from, weight));
			}
			
			// 정방향 그래프 다잌
			pq.offer(new int[] {X, 0});
			distance[X] = 0;
			while (!pq.isEmpty()) {
				int[] cur = pq.poll();
				
				if (cur[1] > distance[cur[0]])
					continue;
				
				for (Edge edge : graph[cur[0]]) {
					int nextW = cur[1] + edge.weight;
					
					if (nextW >= distance[edge.to])
						continue;
					
					distance[edge.to] = nextW;
					pq.offer(new int[] {edge.to, nextW});
				}
			}
			
			// 역방향 그래프 다잌
			pq.clear();
			pq.offer(new int[] {X, 0});
			ReverseDistance[X] = 0;
			while (!pq.isEmpty()) {
				int[] cur = pq.poll();
				
				if (cur[1] > ReverseDistance[cur[0]])
					continue;
				
				for (Edge edge : reverseGraph[cur[0]]) {
					int nextW = cur[1] + edge.weight;
					
					if (nextW >= ReverseDistance[edge.to])
						continue;
					
					ReverseDistance[edge.to] = nextW;
					pq.offer(new int[] {edge.to, nextW});
				}
			}	
			
			// 왕복 거리 계산
			for (int i = 1; i <= N; i++) {
				distance[i] += ReverseDistance[i]; 
			}
			
			// 가장 큰 최단거리 출력
			int temp = 0;
			for (int i = 1; i <= N; i ++) {
				if (distance[i] > temp)
					temp = distance[i];
			}
			
			System.out.println("#"+t+" "+temp);
		}
	}
	
	static class Edge {
		int to;
		int from;
		int weight;
		public Edge(int from, int to, int weight) {
			super();
			this.to = to;
			this.from = from;
			this.weight = weight;
		}
	}
}

//로직 (논리 + 코드 레벨)
/*
단방향 graph의 모든 정점에서 x정점까지의 최단 왕복 거리 중 가장 값이 큰 최단 거리를 출력하는 문제다.
x를 기준으로 다잌을 사용하면 x에서 모든 정점에 대한 최단거리를 구할 수 있다. (= 왕복에서 되돌아가는 길)
다만 모든 정점에서 x까지 갈 때의 최단거리를 구하는 방법은 쉽게 떠오르지 않았다.
-> GPT에게 질문하니 간선의 방향을 바꾼 graph를 추가로 만든 뒤에 x에서 다잌을 사용하면
왕복에서 x까지 가지는 길의 최단거리를 구할 수 있다는 사실을 알려줬다.

가중치 그래프에서 x까지 가는 최단거리를 구하는 문제는 웬만하면 다잌을 사용해야 하는 것 같다.
*/

//배운 것
/*
	다잌풀 때 edge, node class 각각 만들어서 풀자
	int[]로 푸니까 헷갈린다.
*/

//input
/*

(output)

*/

//다른 사람 코드
/*

*/
