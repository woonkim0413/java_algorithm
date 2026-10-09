package algorithm_study;

import java.util.*;
import java.io.*;

public class monthTest2_dfs {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static List<int[]> list;
	static int N;
	static int max = 0;
	static int count = 0;
	static boolean[] visited;
	
	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		
		list = new ArrayList<>();
		visited = new boolean[N];
		
		// list에 회의실 정보 넣기
		for (int i = 0; i < N; i ++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			list.add(new int[] {s, e});
		}
		
		dfs(0, 0);
		System.out.println(max);
	}
	
	static void dfs(int start, int sum) {
		int count = 0;
		
		if (sum > max)
			max = sum;
		
		for (int i = 0; i < N; i ++) {
			if (visited[i]) 
				continue;
			
			int s = list.get(i)[0];
			int e = list.get(i)[1];
			
			if (start <= s) {
				visited[i] = true;
				dfs(e, sum + 1);
				visited[i] = false;
			}
		}
	}
}

/*
 * 김대리는 각 개발팀으로부터 회의실 사용 신청 받아서 스케줄 편성함
 * 회의실은 한 번에 하나의 회의만 진행될 수 있음
 * 김대리는 최대한 많은 회의가 이루어질 수 있도록 할 예정
 * 
 * 회의 시간 리스트를 받음
 * 순열로 접근해서 가장 많은 회의시간을 선택할 수 있는 경우의 수를 출력하면 될 것 같다.
 * 
*/