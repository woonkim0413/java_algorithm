package Jungol;

import java.util.*;
import java.io.*;

public class revolving_sushi_slidingWindow_2577 {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static List<Integer> sushiBelt = new ArrayList<>();
	static StringTokenizer st;
	static int[] sliding;
	
	public static void main(String[] args) throws Exception {
		
		st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		
		sliding = new int[d + 1];
		int maxKind = Integer.MIN_VALUE;
		int kind = 0;
		
		// 초밥 채우기
		for (int i = 0; i < N; i ++) {
			sushiBelt.add(Integer.parseInt(br.readLine().trim()));
		}
		for (int i = 0; i < k; i ++) {
			sushiBelt.add(sushiBelt.get(i));
		}
		
		// 0번째 조합 채우기
		for (int i = 0; i < k; i ++) {
			int cur = sushiBelt.get(i);
			sliding[cur]++;
			if (sliding[cur] == 1)
				kind ++;		
		}
		// 쿠폰 처리
		sliding[c]++;
		if (sliding[c] == 1)
			kind++;
		maxKind = kind;
		
		// 초밥 조합 최대값 구하기
		for (int i = 1; i < N; i ++) {
			// 이전 조합 초밥 제거
			int cur = sushiBelt.get(i - 1);
			sliding[cur]--; // 이전에 포함된 값이니 최소 > 0임
			// 0이 된 경우 초밥 가짓수 제거
			if (sliding[cur] == 0)
				kind--;
			
			// 지금 조합 초밥 추가
			cur = sushiBelt.get(i + k - 1);
			sliding[cur]++;
			if (sliding[cur] == 1)
				kind ++;
			
			// kind 갱신
			maxKind = maxKind < kind ? kind : maxKind;
		}
		System.out.println(maxKind);
	}
}

	//로직 (논리 + 코드 레벨)
	/*
	 
	*/

	//배운 것
	/*
		1) sliding windows
		슬라이딩 윈도우는 이전 구간의 계산 결과를 재사용해서 다음 구간을 빠르게 계산하는 방식이다.
		고정된 크기의 연속 구간을 이동시키면서, 빠지는 값과 들어오는 값만 갱신하는 방식이다.
		
		해당 문제를 예로 들자면 첫 번째 초밥부터 N번째 초밥까지 순회하며 각 초밥 위치를 기준으로
		조합을 만든다고 할 때,
		t번 초밥을 기준으로 한 조합을 만든 후 t + 1번 초밥을 기준으로 다시 조합을 만들 때
		t 초밥을 조합에서 빼고 (t + 1) + k(조합 갯수)번 초밥을 조합에서 추가하면 된다.
	*/

	//input
	/*

	(output)

	*/

	// 다른 사람 코드
	/*

	*/